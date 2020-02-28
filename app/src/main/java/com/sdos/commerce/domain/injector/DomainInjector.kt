package com.sdos.commerce.domain.injector

import com.sdos.commerce.domain.interactors.AddEmployeeInteractor
import com.sdos.commerce.domain.interactors.GetEmployeesInteractor
import com.sdos.commerce.domain.interactors.GetListSkillInteractor
import com.sdos.commerce.domain.interactors.GetTasksInteractor
import com.sdos.login.domain.LoginEmployeeInteractor

interface DomainInjector {

    fun provideLoginInteractor(): LoginEmployeeInteractor

    fun provideGetEmployeesInteractor(): GetEmployeesInteractor

    fun provideGetTasksInteractor(): GetTasksInteractor

    fun provideGetListSkillInteractor(): GetListSkillInteractor

    fun provideAddEmployeeInteractor(): AddEmployeeInteractor
}