package com.test.smartpost.di

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.test.smartpost.data.MainRepositoryImpl
import com.test.smartpost.domain.main.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    //Провайд фаерстора
    @Singleton
    @Provides
    fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()

    //Провайд квери запроса для провайда репозитории
    @Singleton
    @Provides
    fun provideCourseQuery(courseDB: CollectionReference) = courseDB.orderBy("nameOfCourse")

    //Провайд дата бейса и получение колекции
    @Singleton
    @Provides
    fun provideCourseDB(db: FirebaseFirestore) = db.collection("course")

    //Провайд репозитория
    @Provides
    @Singleton
    fun provideRepository(courseQuery: Query): MainRepository = MainRepositoryImpl(courseQuery)
}