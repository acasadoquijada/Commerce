package com.manday.coredata.datasource

import com.manday.coredata.entities.EmployeeEntity

interface LoginDatabaseDataSource {

    fun login(param1: String, param2: String): EmployeeEntity?
}