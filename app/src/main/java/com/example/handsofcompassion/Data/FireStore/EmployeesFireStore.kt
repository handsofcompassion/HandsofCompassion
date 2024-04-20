package com.example.handsofcompassion.Data.FireStore


import com.example.handsofcompassion.Adapter.EmpplyeesAdapter
import com.example.handsofcompassion.Data.Employees
import com.example.handsofcompassion.Listneers.AuthListneers
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject


class EmployeesFireStore @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
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
        id: String,
        adapter: EmpplyeesAdapter,
        listeners: AuthListneers
    ) {
        if (name.isEmpty() || email.isEmpty()) {
            listeners.onFailure("Preencha Todos os Campos.")
        } else {

            val userData = hashMapOf(
                "name" to name,
                "email" to email
            )

                firestore.collection("Users").document(id)
                    .update(userData.toMap())
                    .addOnSuccessListener {
                        listeners.onSucess("Dados atualizados com sucesso.")
                        adapter.notifyDataSetChanged()




                    }
                    .addOnFailureListener { exception ->
                        listeners.onFailure("Falha ao atualizar os dados: ${exception.message}")
            }
        }
    }
}