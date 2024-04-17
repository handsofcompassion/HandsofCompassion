package com.example.handsofcompassion.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.handsofcompassion.Adapter.EmpplyeesAdapter
import com.example.handsofcompassion.Data.Employees
import com.example.handsofcompassion.Repository.RepositoryEmployees
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelEmployees @Inject constructor(private val repositoryEmployees: RepositoryEmployees) :
    ViewModel() {

    // TODO: OUTROS MÉTODOS ESTÃO JUNTO COM A ESTRTURUA DE LOGIN DE USUÁRIO, CRIAR UM NOVO USUÁRIO SALVAM OS DADOS.

    fun getEmployees(
        employeesList: MutableList<Employees>,
        adapter: EmpplyeesAdapter
    ) {
        viewModelScope.launch {

            repositoryEmployees.getEmployees(employeesList, adapter)
        }
    }

    fun updateEMployees(
        name: String,
        email: String,
        adapter: EmpplyeesAdapter
    ){
        viewModelScope.launch {

            repositoryEmployees.updateEmployees(name, email, adapter)

        }
    }
}