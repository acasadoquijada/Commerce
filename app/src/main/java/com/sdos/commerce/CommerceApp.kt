package com.sdos.commerce

import android.app.Application
import com.sdos.commerce.data.EmployeeRepositoryImpl
import com.sdos.commerce.data.FruitRepositoryImpl
import com.sdos.commerce.data.SkillRepositoryImpl
import com.sdos.commerce.data.TaskRepositoryImpl
import com.sdos.commerce.data.datasource.EmployeeDataSource
import com.sdos.commerce.data.datasource.FruitDataSource
import com.sdos.commerce.data.datasource.SkillDataSource
import com.sdos.commerce.data.datasource.TaskDataSource
import com.sdos.commerce.data.datasource.database.RoomController
import com.sdos.commerce.data.datasource.net.NetController
import com.sdos.commerce.data.injector.DataInjector
import com.sdos.commerce.data.room.CommerceDatabase
import com.sdos.commerce.domain.FruitRepository
import com.sdos.commerce.domain.SkillRepository
import com.sdos.commerce.domain.injector.DomainInjector
import com.sdos.commerce.domain.interactors.*
import com.sdos.login.domain.EmployeeRepository
import com.sdos.login.domain.LoginEmployeeInteractor
import com.sdos.login.domain.TaskRepository

class CommerceApp: Application(), DomainInjector, DataInjector {

    // BEGIN - injections layer domain
    override fun provideLoginInteractor(): LoginEmployeeInteractor {
        return LoginEmployeeInteractor(provideEmployeeRepository())
    }

    override fun provideGetEmployeesInteractor(): GetEmployeesInteractor {
        return GetEmployeesInteractor(provideEmployeeRepository())
    }

    override fun provideGetTasksInteractor(): GetTasksInteractor {
        return GetTasksInteractor(provideTaskRepository())
    }

    override fun provideGetListSkillInteractor(): GetListSkillInteractor {
        return GetListSkillInteractor(provideSkillRepository())
    }

    override fun provideAddEmployeeInteractor(): AddEmployeeInteractor {
        return AddEmployeeInteractor(provideEmployeeRepository())
    }

    override fun provideGetTypeTaskInteractor(): GetTypeTaskInteractor {
        return GetTypeTaskInteractor(provideTaskRepository())
    }

    override fun provideAddTaskInteractor(): AddTaskInteractor {
        return AddTaskInteractor(provideTaskRepository())
    }

    override fun provideGetAllFruitsInteractor(): GetAllFruitsInteractor {
        return GetAllFruitsInteractor(provideFruitRepository())
    }
    // END - injections layer domain

    // BEGIN - injections layer data
    override fun provideEmployeeRepository(): EmployeeRepository {
        return EmployeeRepositoryImpl(provideEmployeeDataSource())
    }

    override fun provideEmployeeDataSource(): EmployeeDataSource {
        return RoomController()
    }

    override fun provideTaskDataSource(): TaskDataSource {
        return RoomController()
    }

    override fun provideTaskRepository(): TaskRepository {
        return TaskRepositoryImpl(provideTaskDataSource())
    }

    override fun provideSkillRepository(): SkillRepository {
        return SkillRepositoryImpl(provideSkillDataSource())
    }

    override fun provideSkillDataSource(): SkillDataSource {
        return RoomController()
    }

    override fun provideFruitDataSource(): FruitDataSource {
        return NetController()
    }

    override fun provideFruitRepository(): FruitRepository {
        return FruitRepositoryImpl(provideFruitDataSource())
    }
    // END - injections layer data

    companion object {
        private var instance: CommerceApp? = null

        fun getInstance(): CommerceApp? {
            if (instance == null) {
                synchronized(CommerceApp::class) {
                    instance = CommerceApp()
                }
            }

            return instance
        }
    }
}