package com.example.handsofcompassion.Repository

import com.example.handsofcompassion.Adapter.EmpplyeesAdapter
import com.example.handsofcompassion.Data.Employees
import com.example.handsofcompassion.Data.FireStore.EmployeesFireStore
import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.UI.ForgotPassword
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class RepositoryEmployees @Inject constructor(private val employeesFireStore: EmployeesFireStore) {

    // TODO: OUTROS MÉTODOS ESTÃO JUNTO COM A ESTRTURUA DE LOGIN DE USUÁRIO, CRIAR UM NOVO USUÁRIO SALVAM OS DADOS.
    fun getEmployees(
        employeesList: MutableList<Employees>,
        adapter: EmpplyeesAdapter,
    ) {
        employeesFireStore.getEmployees(employeesList, adapter)
    }

    fun searchEmployees(
        typedText: String,
        employeesList: MutableList<Employees>,
        adapter: EmpplyeesAdapter
    ) {

        employeesFireStore.searchEmployees(typedText, employeesList, adapter)

    }

    fun updateEmployees(
        name: String,
        email: String,
        password: String,
        id: String,
        adapter: EmpplyeesAdapter,
        listneers: AuthListneers
    ) {

        employeesFireStore.updateEmployees(name,email,password,id,adapter,listneers)
    }

}