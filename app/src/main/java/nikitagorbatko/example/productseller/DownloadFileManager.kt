package nikitagorbatko.example.productseller

import android.app.DownloadManager
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.webkit.MimeTypeMap

data class File(val url: String, val fileName: String, val fileExtension: String, val fileKey: String)

class DownloadFileManager {
    private var downloadedFileId: Long? = null
    private lateinit var listener: DownloadFileListener
    private lateinit var downloadManager: DownloadManager

    fun start(file: File, context: Context) {
        downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        //try {
            val request = DownloadManager.Request(Uri.parse(file.url)).apply {
                setTitle(file.fileName)
                setMimeType(MimeTypeMap.getSingleton().getMimeTypeFromExtension(file.fileExtension))
                allowScanningByMediaScanner()
                //setVisibleInDownloadsUi(true)
                setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, file.fileName)
            }

            downloadedFileId = downloadManager.enqueue(request)
            listener.onNext(fileKey = file.fileKey, downloadedFileId = downloadedFileId)

            val query = DownloadManager.Query().apply { setFilterById(downloadedFileId!!) }
            val cursor = downloadManager.query(query)//.apply { moveToFirst() }

            while (cursor.moveToNext()) {
                when(cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))) {
                    DownloadManager.STATUS_SUCCESSFUL -> {
                        listener.onComplete(downloadedFileId!!, file.fileKey, isSuccess = true)
                    }
                    DownloadManager.PAUSED_UNKNOWN -> {
                        listener.onComplete(file = file, isSuccess = false)
                    }
                    else -> {
                        val bytesDownloaded = cursor.getInt(cursor.getColumnIndex(DownloadManager
                            .COLUMN_BYTES_DOWNLOADED_SO_FAR))
                        listener.onNext(bytesDownloaded, file.fileKey, downloadedFileId)
                    }
                }
            }
            cursor.close()
//        } catch (t: Throwable) {
//            listener.onComplete(file = file, error = t, isSuccess = false)
//            downloading = false
//        }
    }

    fun addListener(listener: DownloadFileListener) {this.listener = listener}

    interface DownloadFileListener {
        fun onNext(bytesDownloaded: Int = 0, fileKey: String, downloadedFileId: Long?)
        fun onComplete(downloadedFileId: Long = 0, fileKey: String = "", file: File? = null, error: Throwable? = null, isSuccess: Boolean = false)
    }
}
