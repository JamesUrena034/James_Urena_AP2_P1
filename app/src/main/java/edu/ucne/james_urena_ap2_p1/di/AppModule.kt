package edu.ucne.james_urena_ap2_p1.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.james_urena_ap2_p1.data.local.dao.AmonestacionDao
import edu.ucne.james_urena_ap2_p1.data.local.database.AmonestacionDb
import edu.ucne.james_urena_ap2_p1.data.repository.AmonestacionRepositoryImpl
import edu.ucne.james_urena_ap2_p1.domain.repository.AmonestacionRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AmonestacionDb {
        return Room.databaseBuilder(
            context,
            AmonestacionDb::class.java,
            "Amonestacion.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideAmonestacionDao(db: AmonestacionDb): AmonestacionDao = db.amonestacionDao()

    @Provides
    @Singleton
    fun provideAmonestacionRepository(dao: AmonestacionDao): AmonestacionRepository {
        return AmonestacionRepositoryImpl(dao)
    }
}