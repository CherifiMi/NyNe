package com.example.nyne.data.database.library

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

class LibraryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(libraryItem: LibraryItem)

    @Delete
    fun delete(libraryItem: LibraryItem)

    @Query("SELECT * FROM book_library ORDER BY id ASC")
    fun getAllItems(): LiveData<List<LibraryItem>>

    @Query("SELECT * FROM book_library WHERE book_id = :bookId")
    fun getItemById(bookId: Int): LibraryItem?

}