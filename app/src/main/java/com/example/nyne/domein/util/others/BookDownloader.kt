package com.example.nyne.domein.util.others

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.view.translation.Translator
import androidx.annotation.Keep
import coil.compose.AsyncImagePainter
import com.example.nyne.domein.util.BookUtils
import com.google.gson.annotations.SerializedName
import com.starry.myne.repo.models.Author
import com.starry.myne.repo.models.Book
import com.starry.myne.repo.models.Formats
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

class BookDownloader (context: Context){
    companion object{
        val FILE_FOLDER_PATH = "/storage/emulated/0/${Environment.DIRECTORY_DOWNLOADS}/${Constants.DOWNLOAD_DIR}"
    }
    private val downloadJob = Job()
    private val downloadScope = CoroutineScope(Dispatchers.IO + downloadJob)
    private val downloadManager by lazy { context.getSystemService(Context.DOWNLOAD_SERVICE) as  DownloadManager}

    data class DownloadInfo(
        val downloadId: Long,
        var status: Int = DownloadManager.STATUS_RUNNING,
        val progress: MutableStateFlow<Float> = MutableStateFlow(0f)
    )

    private val runningDownloads = HashMap<Int, DownloadInfo>()

    @SuppressLint("Range")
    fun downloadBook(
        book: Book, downloadProgressListener: (Float, Int) -> Unit, onDownloadSuccess: () -> Unit
    ){
        val filename = getFilenameForBook(book)
        val uri = Uri.parse(book.formats.applicationepubzip)
        val request = DownloadManager.Request(uri)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setAllowedOverRoaming(true).setAllowedOverMetered(true).setTitle(book.title)
            .setDescription(BookUtils.getAutherAsString(book.authors))
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, Constants.DOWNLOAD_DIR + "/" + filename)
        val downloadId = downloadManager.enqueue(request)

        downloadScope.launch {
            var isDownloadFinished = false
            var progress = 0f
            var status: Int
            runningDownloads[book.id] = DownloadInfo(downloadId)

            while (!isDownloadFinished){
                val cursor: Cursor =
                    downloadManager.query(DownloadManager.Query().setFilterById(downloadId))
                if (cursor.moveToFirst()){
                    status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
                    when(status){
                        DownloadManager.STATUS_RUNNING -> {
                            val totalBytes: Long =
                                cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))
                            if (totalBytes > 0) {
                                val downloadedBytes: Long =
                                    cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
                                progress = (downloadedBytes * 100 / totalBytes).toFloat() / 100
                            }
                        }
                        DownloadManager.STATUS_SUCCESSFUL -> {
                            isDownloadFinished = true
                            progress = 1f
                            onDownloadSuccess()
                        }
                        DownloadManager.STATUS_PAUSED, DownloadManager.STATUS_PENDING -> {}
                        DownloadManager.STATUS_FAILED -> {
                            isDownloadFinished = true
                            progress = 0f
                        }
                    }
                }else{
                    isDownloadFinished = true
                    progress = 0f
                    status = DownloadManager.STATUS_FAILED
                }
                runningDownloads[book.id]?.status = status
                downloadProgressListener(progress, status)
                runningDownloads[book.id]?.progress?.value = progress
                cursor.close()
            }
            delay(500L)
            runningDownloads.remove(book.id)
        }
    }
    fun isBookCurrentlyDownloading(bookId: Int) = runningDownloads.containsKey(bookId)
    fun getRunnignDownload(bookId: Int) = runningDownloads[bookId]
    fun canselDownload(downloadId: Long?) = downloadId?.let { downloadManager.remove(it) }
    fun getFilenameForBook(book: Book) = book.title.replace(":", ";").replace("\"", "").split(" ").joinToString(separator = "+") + ".epub"
}