package com.example.hirehub.model.repository

import androidx.annotation.WorkerThread
import com.example.hirehub.model.dao.OfferDao
import com.example.hirehub.model.dao.PositionDao
import com.example.hirehub.model.entities.Offer
import com.example.hirehub.model.entities.Position
import kotlinx.coroutines.flow.Flow

class PositionRepository(private val positionDao: PositionDao) {

    val allPositions: Flow<List<Position>> = positionDao.getAllPositions()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(position: Position) {
        positionDao.insert(position)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll() {
        positionDao.deleteAll()
    }
}