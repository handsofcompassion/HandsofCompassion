package com.example.handsofcompassion.Data.FireStore

import android.annotation.SuppressLint
import android.content.Context
import com.example.handsofcompassion.Adapter.DonorAdapter
import com.example.handsofcompassion.Adapter.EmpplyeesAdapter
import com.example.handsofcompassion.Adapter.ReceiverAdapter
import com.example.handsofcompassion.Data.Donor
import com.example.handsofcompassion.Data.Employees
import com.example.handsofcompassion.Data.Receiver
import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.R
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID
import javax.inject.Inject

class DonorFireStore @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val context: Context
) {

    fun createDonor(
        name: String,
        cpf: String,
        phone: String,
        email: String,
        address: String,
        birth: String,
        listneers: AuthListneers
    ) {

        if (name.isEmpty() || cpf.isEmpty() || phone.isEmpty()
            || email.isEmpty() || address.isEmpty() || birth.isEmpty()) {

            listneers.onFailure(context.getString(R.string.preencha))

        }else {

            val id = UUID.randomUUID().toString()
            val donorDataMap = hashMapOf(
                "id" to id,
                "name" to name,
                "cpf" to cpf,
                "phone" to phone,
                "email" to email,
                "address" to address,
                "birth" to birth
            )

            val userReference = firestore.collection("Donors").document(id)

            userReference.set(donorDataMap).addOnCompleteListener {

                listneers.onSucess(context.getString(R.string.sucessoDoardo))

            }.addOnFailureListener {

                listneers.onFailure(context.getString(R.string.erroserver))

            }
        }
    }

    @SuppressLint("NotifyDatasetChanged")
    fun getDonor(
        donorsList: MutableList<Donor>,
        adapter: DonorAdapter
    ) {

        firestore.collection("Donors")
            .orderBy("name").get()
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    for (document in task.result) {

                        val donors = document.toObject(Donor::class.java)
                        donorsList.add(donors)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
    }



    @SuppressLint("NotifyDatasetChanged")
    fun searchDonor(
        typedText: String,
        donorsList: MutableList<Donor>,
        adapter: DonorAdapter
    ) {
        val query = firestore.collection("Donors")
            .orderBy("name")
            .startAt(typedText)
            .endAt(typedText + "\uf8ff")
            .limit(3)

        donorsList.clear()
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val newDonorsList = mutableListOf<Donor>()

                for (document in task.result) {
                    val donor = document.toObject(Donor::class.java)
                    newDonorsList.add(donor)
                }

                donorsList.addAll(newDonorsList) // Adiciona os novos receptores à lista existente
                adapter.notifyDataSetChanged() // Notifica o RecyclerView sobre as mudanças
            }
        }
    }

    @SuppressLint("NotifyDatasetChanged")
    fun searchDonorsCPF(
        typedText: String,
        donorsList: MutableList<Donor>,
        adapter: DonorAdapter
    ) {
        val query = firestore.collection("Donors")
            .orderBy("cpf")
            .startAt(typedText)
            .endAt(typedText + "\uf8ff")
            .limit(2)

        donorsList.clear()
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val newDonorsList = mutableListOf<Donor>()

                for (document in task.result) {
                    val donor = document.toObject(Donor::class.java)
                    newDonorsList.add(donor)
                }

                donorsList.addAll(newDonorsList) // Adiciona os novos receptores à lista existente
                adapter.notifyDataSetChanged() // Notifica o RecyclerView sobre as mudanças
            }
        }
    }

    @SuppressLint("NotifyDatasetChanged")
    fun updateDonors(
        name: String,
        cpf: String,
        phone: String,
        email: String,
        address: String,
        birth: String,
        id: String,
        adapter: DonorAdapter,
        listeners: AuthListneers
    ) {
        if (name.isEmpty() || cpf.isEmpty() || phone.isEmpty() ||
            email.isEmpty() || address.isEmpty() || birth.isEmpty()) {
            listeners.onFailure(context.getString(R.string.preencha))
        } else {

            val userData = hashMapOf(
                "id" to id,
                "name" to name,
                "cpf" to cpf,
                "phone" to phone,
                "email" to email,
                "address" to address,
                "birth" to birth
            )

            firestore.collection("Donors").document(id)
                .update(userData.toMap())
                .addOnSuccessListener {
                    listeners.onSucess(context.getString(R.string.dadosatualizados))
                    adapter.notifyDataSetChanged()

                }
                .addOnFailureListener { exception ->
                    listeners.onFailure(context.getString(R.string.falhaDados))
                }
        }
    }


}
