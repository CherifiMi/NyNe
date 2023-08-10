package com.example.nyne.util.di

import android.content.Context
import com.example.nyne.data.database.NyneDatabase
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


}