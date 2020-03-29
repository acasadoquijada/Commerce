package com.sdos.commerce.data.datasource

import androidx.lifecycle.LiveData
import com.sdos.commerce.entities.Fruit

interface FruitNetDataSource {

    fun getFruits(category: String, item: String): LiveData<List<Fruit>>
}