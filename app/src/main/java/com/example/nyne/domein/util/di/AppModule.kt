package com.example.nyne.domein.util.di

import android.content.Context
import com.example.nyne.data.NyneDataStore
import com.example.nyne.data.database.NyneDatabase
import com.example.nyne.domein.repo.BookRepository
import com.example.nyne.domein.util.others.BookDownloader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun provideAppContext(@ApplicationContext context: Context) = context

    @Singleton
    @Provides
    fun provideMyneDatabase(@ApplicationContext context: Context) =
        NyneDatabase.getInstance(context)

    @Provides
    fun provideLibraryDao(myneDatabase: NyneDatabase) = myneDatabase.getLibraryDao()

    @Provides
    fun provideReaderDao(myneDatabase: NyneDatabase) = myneDatabase.getReaderDao()

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ) = NyneDataStore(context = context)

    @Singleton
    @Provides
    fun provideBookDownloader(@ApplicationContext context: Context) = BookDownloader(context)

    @Singleton
    @Provides
    fun provideBooksApi() = BookRepository()
}