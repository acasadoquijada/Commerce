package com.sdos.commerce.domain

import androidx.lifecycle.LiveData
import com.manday.coredata.entities.SkillEntity

interface SkillRepository {

    fun getListSkill(): LiveData<List<SkillEntity>>?
}