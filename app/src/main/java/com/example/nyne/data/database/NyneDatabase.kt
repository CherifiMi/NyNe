package com.example.nyne.data.database

import android.content.Context
import android.provider.SyncStateContract
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nyne.data.database.library.LibraryDao
import com.example.nyne.data.database.library.LibraryItem
import com.example.nyne.data.database.reader.ReaderDao
import com.example.nyne.data.database.reader.ReaderItem
import com.example.nyne.util.others.Constants

@Database(
    entities = [LibraryItem::class, ReaderItem::class],
    version = 1,
    exportSchema = true
)
abstract class NyneDatabase: RoomDatabase() {

    abstract fun getLibraryDao(): LibraryDao
    abstract fun getReaderDao(): ReaderDao

    companion object{ // just use a singelton??
        @Volatile
        private var INSTANCE: NyneDatabase? = null

        fun getInstance(context: Context): NyneDatabase {
            /*
            if the INSTANCE is not null, then return it,
            if it is, then create the database and save
            in instance variable then return it.
            */
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NyneDatabase::class.java,
                    Constants.DATABASE_NAME
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}