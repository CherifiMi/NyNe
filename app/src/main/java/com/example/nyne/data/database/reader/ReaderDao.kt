package com.example.nyne.data.database.reader

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ReaderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(readerItem: ReaderItem)

    @Query("DELETE FROM reader_table WHERE book_id = :bookId")
    fun delete(bookId: Int)

    @Query("SELECT * FROM reader_table WHERE book_id = :bookId")
    fun getReaderItem(bookId: Int): ReaderItem?

    @Query(
        "UPDATE reader_table SET "
                + "last_chapter_index = :lastChapterIndex,"
                + "last_chapter_offset = :lastChapterOffset"
                + " WHERE  book_id = :bookId"
    )
    fun update(bookId: Int, lastChapterIndex: Int, lastChapterOffset: Int)
}