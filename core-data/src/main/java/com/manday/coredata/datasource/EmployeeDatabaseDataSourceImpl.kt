package com.manday.coredata.datasource

import androidx.lifecycle.LiveData
import com.manday.coredata.entities.EmployeeEntity
import com.sdos.commerce.dao.EmployeeDao
import com.sdos.commerce.data.room.RoomController

class EmployeeDatabaseDataSourceImpl: EmployeeDatabaseDataSource {

    private var employeeDao: EmployeeDao? = null

    init {
        employeeDao = RoomController.getEmployeeDao()
    }

    override fun addEmployee(employee: EmployeeEntity) {
        employeeDao?.addEmployee(employee)
    }

    override fun getEmployees(): LiveData<List<EmployeeEntity>>? {
        return employeeDao?.getAllEmployees()
    }

    override fun updateEmployee(employee: EmployeeEntity?) {
        employee?.let {
            employeeDao?.addEmployee(it)
        }
    }
}