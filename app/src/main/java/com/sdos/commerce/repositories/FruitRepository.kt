package com.sdos.commerce.repositories

import androidx.lifecycle.LiveData
import com.manday.coredata.entities.FruitEntity

interface FruitRepository {

    fun getAllFruits(category: String, item: String): LiveData<List<FruitEntity>>
}