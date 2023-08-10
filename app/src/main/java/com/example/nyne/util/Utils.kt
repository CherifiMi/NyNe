package com.example.nyne.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import com.example.nyne.BuildConfig
import com.example.nyne.R
import com.example.nyne.data.database.library.LibraryItem
import com.starry.myne.ui.navigation.Screens
import com.starry.myne.utils.toToast
import java.io.File
import java.text.DecimalFormat
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

object Utils {
    fun prettyCount(number: Number): String {
        val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
        val numValue = number.toLong()
        val value = floor(log10(numValue.toDouble())).toInt()
        val base = value / 3
        return if (value >= 3 && base < suffix.size) {
            DecimalFormat("#0.0").format(
                numValue / 10.0.pow((base * 3).toDouble())
            ) + suffix[base]
        } else {
            DecimalFormat("#,##0").format(numValue)
        }
    }

    fun openBookFile(context: Context, item: LibraryItem, navController: NavController) {

        if (PreferenceUtil.getBoolean(PreferenceUtil.INTERNAL_READER_BOOL, true)) {
            navController.navigate(
                Screens.ReaderDetailScreen.withBookId(
                    item.bookId.toString()
                )
            )
        } else {
            val uri = FileProvider.getUriForFile(
                context, BuildConfig.APPLICATION_ID + ".provider", File(item.filePath)
            )
            val intent = Intent(Intent.ACTION_VIEW)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.setDataAndType(uri, context.contentResolver.getType(uri))
            val chooser = Intent.createChooser(
                intent, context.getString(R.string.open_app_chooser)
            )
            try {
                context.startActivity(chooser)
            } catch (exc: ActivityNotFoundException) {
                context.getString(R.string.no_app_to_handle_epub).toToast(context)
            }
        }
    }
}