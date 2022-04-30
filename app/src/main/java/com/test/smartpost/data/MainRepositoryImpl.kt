package com.test.smartpost.data

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import com.test.smartpost.domain.Response.*
import com.test.smartpost.domain.main.model.CourseModel
import com.test.smartpost.domain.main.repository.MainRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val courseQuery: Query
) : MainRepository {
    //Стягивает данные с стора
    override fun getListModel() = callbackFlow {
        val snapshotListener = courseQuery.addSnapshotListener { snapshot, e ->
            val response = if (snapshot != null) {
                val course = snapshot.toObjects(CourseModel::class.java)
                Success(course)
            } else {
                Error(e?.localizedMessage ?: "Error")
            }
            trySend(response).isSuccess
        }
        awaitClose {
            snapshotListener.remove()
        }
    }
}