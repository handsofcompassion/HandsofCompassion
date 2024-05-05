package com.example.handsofcompassion.Data.FireStore


import android.annotation.SuppressLint
import android.content.Context
import com.example.handsofcompassion.Adapter.EmpplyeesAdapter
import com.example.handsofcompassion.Data.Employees
import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID
import javax.inject.Inject


class EmployeesFireStore @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
    private val context: Context
) {

    val id = UUID.randomUUID().toString()

    @SuppressLint("NotifyDatasetChanged")
    fun getEmployees(
        employeesList: MutableList<Employees>,
        adapter: EmpplyeesAdapter
    ) {

        firestore.collection("Users")
            .orderBy("name").get()
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    for (document in task.result) {

                        val employees = document.toObject(Employees::class.java)
                        employeesList.add(employees)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
    }

    @SuppressLint("NotifyDatasetChanged")
    fun searchEmployees(
        typedText: String,
        employeesList: MutableList<Employees>,
        adapter: EmpplyeesAdapter
    ) {
        val query = firestore.collection("Users").orderBy("name")
            .startAt(typedText).endAt(typedText + "\uf8ff").limit(3)

        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val newEmployeesList = mutableListOf<Employees>()

                for (document in task.result) {
                    val employees = document.toObject(Employees::class.java)
                    newEmployeesList.add(employees)
                }

                employeesList.clear()
                employeesList.addAll(newEmployeesList)
                adapter.notifyDataSetChanged()
            }
        }
    }

    @SuppressLint("NotifyDatasetChanged")
    fun updateEmployees(
        name: String,
        email: String,
        password: String,
        id: String,
        adapter: EmpplyeesAdapter,
        listeners: AuthListneers
    ) {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            listeners.onFailure(context.getString(R.string.preencha))
        } else {

            val userData = hashMapOf(
                "name" to name,
                "email" to email,
                "password" to password
            )

            firestore.collection("Users").document(id)
                .update(userData.toMap())
                .addOnSuccessListener {
                    listeners.onSucess(context.getString(R.string.dadosatualizados))
                    adapter.notifyDataSetChanged()


                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {

                        }.addOnFailureListener { }

                }
                .addOnFailureListener { exception ->
                    listeners.onFailure(context.getString(R.string.falhaDados))
            }
        }
    }

}