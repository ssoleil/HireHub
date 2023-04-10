package com.example.hirehub.model

import androidx.annotation.WorkerThread
import com.example.hirehub.model.dao.OfferCategoryDao
import com.example.hirehub.model.dao.OfferDao
import com.example.hirehub.model.dao.SkillDao
import com.example.hirehub.model.entities.Offer
import com.example.hirehub.model.entities.OfferCategory
import com.example.hirehub.model.entities.Skill
import kotlinx.coroutines.flow.Flow

class SkillRepository(private val skillDao: SkillDao) {

    val allSkill: Flow<List<Skill>> = skillDao.getAllSkills()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(skill: Skill) {
        skillDao.insert(skill)
    }
}