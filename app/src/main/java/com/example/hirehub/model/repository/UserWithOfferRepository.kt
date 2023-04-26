package com.example.hirehub.model.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.hirehub.model.dao.UserDao
import com.example.hirehub.model.dao.UserWithOfferDao
import com.example.hirehub.model.entities.User
import com.example.hirehub.model.entities.relations.UserOfferPair
import com.example.hirehub.model.entities.relations.UserWithOffer
import kotlinx.coroutines.flow.Flow

class UserWithOfferRepository(private val userOfferDao: UserWithOfferDao) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(join: UserWithOffer) {
        userOfferDao.insert(join)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll() {
        userOfferDao.deleteAll()
    }

    fun findOffersByUser(user_id: Int) : LiveData<UserOfferPair?> =
        userOfferDao.findOffersByUser(user_id)

//    fun findUserOfferPairsByUser(hr_id : Int, hr_offer_ids: List<Int>?): LiveData<List<UserOfferPair>?> =
//        userOfferDao.findUserOfferPairsByUser(hr_id, hr_offer_ids)

}