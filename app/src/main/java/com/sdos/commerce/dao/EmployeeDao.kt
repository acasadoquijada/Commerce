package com.sdos.commerce.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sdos.commerce.entities.Employee
import com.sdos.commerce.entities.Task

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(employees: List<Employee>)

    @Query("SELECT * FROM employees ")
    fun getAllEmployees(): LiveData<List<Employee>>

    /*
    @Query("SELECT * FROM employees WHERE id = :mId")
    fun getTasksFromEmploye(mId: Int): LiveData<List<Int>>

    @Query("UPDATE employees SET listTask=:listNewTask WHERE id = :idEmployee")
    fun assignTaskToEmploye(idEmployee: Int, listNewTask: List<Int>)



    @Update
    suspend fun updateUsers(employee: Employee)

    @Delete
    suspend fun deleteUsers(employee: Employee)

     */
}