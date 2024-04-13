package com.example.handsofcompassion.Data.FireStore

import com.example.handsofcompassion.Adapter.EmpplyeesAdapter
import com.example.handsofcompassion.Data.Employees
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class EmployeesFireStore @Inject constructor(private val firestore: FirebaseFirestore) {

    // TODO: OUTROS MÉTODOS ESTÃO JUNTO COM A ESTRTURUA DE LOGIN DE USUÁRIO, CRIAR UM NOVO USUÁRIO SALVAM OS DADOS.//

    fun getEmployees(
        employeesList: MutableList<Employees>,
        adapter: EmpplyeesAdapter
    ) {

        firestore.collection("Users").get().addOnCompleteListener { task ->

            if (task.isSuccessful) {

                for (document in task.result){

                    val employees = document.toObject(Employees::class.java)
                    employeesList.add(employees)
                    adapter.notifyDataSetChanged()

                }
            }
        }
    }
}