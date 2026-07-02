package com.example.final_exam_project.data.repository

import com.example.final_exam_project.data.local.WorkoutDao
import com.example.final_exam_project.data.local.WorkoutSession
import kotlinx.coroutines.flow.Flow

// Sits between the ViewModels and Room; keeps DAO details out of the UI layer.
class WorkoutRepository(private val dao: WorkoutDao) {

    val allSessions: Flow<List<WorkoutSession>> = dao.getAll()

    suspend fun addSession(session: WorkoutSession): Long = dao.insert(session)

    suspend fun updateSession(session: WorkoutSession) = dao.update(session)

    suspend fun deleteSession(session: WorkoutSession) = dao.delete(session)

    suspend fun deleteSessionById(id: Long) = dao.deleteById(id)

    suspend fun getSessionById(id: Long): WorkoutSession? = dao.getById(id)

    fun countSince(epochMillis: Long): Flow<Int> = dao.getCountSince(epochMillis)
}
