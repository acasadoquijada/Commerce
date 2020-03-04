package com.sdos.commerce.ui.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.sdos.commerce.CommerceApp
import com.sdos.commerce.domain.injector.DomainInjector
import com.sdos.commerce.entities.Employee
import com.sdos.commerce.entities.Task
import com.sdos.commerce.entities.TypeTask
import com.sdos.commerce.listeners.ViewModelListener
import com.sdos.commerce.util.ExecutorViewModel

class DetailTaskViewModel: ExecutorViewModel() {

    private lateinit var baseView: ViewModelListener
    private var listEmployeesSaved = listOf<Employee>()
    private var listTypeTask = listOf<TypeTask>()
    private val getListEmployees = MediatorLiveData<Boolean>()
    private val typeTask = MediatorLiveData<List<TypeTask>>()

    private val getEmployeesInteractor = (CommerceApp.getInstance() as DomainInjector).provideGetEmployeesInteractor()
    private val getTypeTaskInteractor = (CommerceApp.getInstance() as DomainInjector).provideGetTypeTaskInteractor()
    private val addTaskInteractor = (CommerceApp.getInstance() as DomainInjector).provideAddTaskInteractor()
    private val updateEmployeeInteractor = (CommerceApp.getInstance() as DomainInjector).provideAddEmployeeInteractor()

    init {
        getEmployeesInteractor.invoke()?.let {source ->
            getListEmployees.addSource(source, Observer {
                getListEmployees.removeSource(source)
                getListEmployees.value = true
                listEmployeesSaved = it
            })
        }

        getTypeTaskInteractor.invoke()?.let {source ->
            typeTask.addSource(source, Observer {
                typeTask.removeSource(source)
                typeTask.value = it
                listTypeTask = it
            })
        }
    }

    fun setListener(baseView: ViewModelListener) {
        this.baseView = baseView
    }

    fun getListEmployees() = getListEmployees

    fun getTypeTasks() = typeTask

    fun getEmployeeBySkil(skills: List<Int>) = listEmployeesSaved.filter { skills.contains(it.skill) }

    fun getSkillFromTask(idTypeTask: Int) = listTypeTask.get(idTypeTask).skillNeeded

    fun addTask(task: Task) {
        doInParallel (
            {
                addTaskInteractor.invoke(task)
            },
            {
                updateEmployeeInteractor(listEmployeesSaved.find { it.id == task.idEmployee }!!)
            },
            {
                baseView.showMessage("La tarea ha sido añadida correctamente")
            })
    }

    fun getTypeTask(position: Int) = listTypeTask.get(position)
}