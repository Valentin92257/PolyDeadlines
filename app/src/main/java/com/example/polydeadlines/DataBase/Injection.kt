package com.example.polydeadlines.DataBase
import android.app.Application
import android.content.Context
import androidx.room.Room
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
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "Deadlines_database"
        ).build()
    }
    @Provides
    @Singleton
    fun provideDao(appDatabase: AppDatabase) : PanelDao{
        return appDatabase.userDao()
    }

}

