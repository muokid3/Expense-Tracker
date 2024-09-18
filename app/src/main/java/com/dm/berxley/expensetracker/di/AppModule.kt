package com.dm.berxley.expensetracker.di

import android.app.Application
import androidx.room.Room
import com.dm.berxley.expensetracker.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRoomDb(application: Application): AppDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = AppDatabase::class.java,
            name = AppDatabase.ROOM_DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    }
}