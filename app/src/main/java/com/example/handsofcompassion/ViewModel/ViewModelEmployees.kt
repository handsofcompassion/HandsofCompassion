package com.example.handsofcompassion.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.handsofcompassion.Adapter.EmpplyeesAdapter
import com.example.handsofcompassion.Data.Employees
import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.Repository.RepositoryEmployees
import com.example.handsofcompassion.UI.ForgotPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelEmployees @Inject constructor(private val repositoryEmployees: RepositoryEmployees) :
    ViewModel() {

    // TODO: OUTROS MÉTODOS ESTÃO JUNTO COM A ESTRTURUA DE LOGIN DE USUÁRIO, CRIAR UM NOVO USUÁRIO SALVAM OS DADOS.
    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_EMAIL = "extra_email"
    }
    fun getEmployees(
        employeesList: MutableList<Employees>,
        adapter: EmpplyeesAdapter
    ) {
        viewModelScope.launch {

            repositoryEmployees.getEmployees(employeesList, adapter)
        }
    }

    fun searchEmployees(
        typedText: String,
        employeesList: MutableList<Employees>,
        adapter: EmpplyeesAdapter
    ) {
        viewModelScope.launch {

            repositoryEmployees.searchEmployees(typedText, employeesList, adapter)

        }
    }

    fun updateEMployees(
        name: String,
        email: String,
        password: String,
        id: String,
        adapter: EmpplyeesAdapter,
        listneers: AuthListneers
    ) {
        viewModelScope.launch {

            repositoryEmployees.updateEmployees(name, email, password, id, adapter, listneers)

        }
    }


}