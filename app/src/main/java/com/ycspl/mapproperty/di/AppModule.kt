package com.ycspl.mapproperty.di

import android.content.Context
import androidx.room.Room
import com.ycspl.mapproperty.DATABASE_NAME
import com.ycspl.mapproperty.database.AppDatabase
import com.ycspl.mapproperty.database.LocationDao
import com.ycspl.mapproperty.database.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .build()

    @Provides
    @Singleton
    fun provideDao(appDB: AppDatabase): LocationDao = appDB.locationDao()


    @Provides
    @Singleton
    fun provideLocationRepository(dao: LocationDao): LocationRepository = LocationRepository(dao)
}