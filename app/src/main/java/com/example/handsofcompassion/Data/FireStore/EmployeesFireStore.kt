package com.example.handsofcompassion.Data.FireStore

import android.util.Log
import com.example.handsofcompassion.Adapter.EmpplyeesAdapter
import com.example.handsofcompassion.Data.Employees
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import kotlin.math.log

class EmployeesFireStore @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebaseauth: FirebaseAuth
) {

    // TODO: OUTROS MÉTODOS ESTÃO JUNTO COM A ESTRTURUA DE LOGIN DE USUÁRIO, CRIAR UM NOVO USUÁRIO SALVAM OS DADOS.//

    fun getEmployees(
        employeesList: MutableList<Employees>,
        adapter: EmpplyeesAdapter
    ) {

        firestore.collection("Users").get().addOnCompleteListener { task ->

            if (task.isSuccessful) {

                for (document in task.result) {

                    val employees = document.toObject(Employees::class.java)
                    employeesList.add(employees)
                    adapter.notifyDataSetChanged()

                }
            }
        }
    }

    fun updateEmployees(
        name: String,
        email: String,
        adapter: EmpplyeesAdapter
    ) {
        var id = firebaseauth.currentUser?.uid.toString()
        firestore.collection("Users").document(id).update("name", name, "email", email)
            .addOnCompleteListener {

             adapter.notifyDataSetChanged()

            }.addOnFailureListener {

         }
    }
}