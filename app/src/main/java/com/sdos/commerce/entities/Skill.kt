package com.sdos.commerce.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "skill")
data class Skill(

    var skill: String
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}