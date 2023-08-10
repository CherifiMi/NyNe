package com.example.nyne.util.others

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.view.translation.Translator
import androidx.annotation.Keep
import coil.compose.AsyncImagePainter
import com.google.gson.annotations.SerializedName
import com.starry.myne.repo.models.Author
import com.starry.myne.repo.models.Book
import com.starry.myne.repo.models.Formats
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow

class BookDownloader (context: Context){
    companion object{

    }
    private val downloadJob = Job()
    private val downloadScope = CoroutineScope(Dispatchers.IO + downloadJob)
    private val downloadManager by lazy { context.getSystemService(Context.DOWNLOAD_SERVICE) as  DownloadManager}

    data class DownloadInfo(
        val downloadId: Long,
        val status: Int = DownloadManager.STATUS_RUNNING,
        val progress: MutableStateFlow<Float> = MutableStateFlow(0f)
    )

    private val runningDownloads = HashMap<Int, DownloadInfo>()

    fun downloadBook(
        book: Book, downloadProgressListener: (Float, Int) -> Unit, onDownloadSuccess: () -> Unit
    ){
        val filename = getFilenameForBook(book)
        val uri = Uri.parse(book.formats.applicationepubzip) // !! fix
    }
    fun isBookCurrentlyDownloading(bookId: Int) = runningDownloads.containsKey(bookId)
    fun getRunnignDownload(bookId: Int) = runningDownloads[bookId]
    fun canselDownload(downloadId: Long?) = downloadId?.let { downloadManager.remove(it) }
    fun getFilenameForBook(book: Book) = book.title.replace(":", ";").replace("\"", "").split(" ").joinToString(separator = "+") + ".epub"
}