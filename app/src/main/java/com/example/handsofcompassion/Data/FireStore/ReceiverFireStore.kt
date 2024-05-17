package com.example.handsofcompassion.Data.FireStore

import android.annotation.SuppressLint
import android.content.Context
import com.example.handsofcompassion.Adapter.EmpplyeesAdapter
import com.example.handsofcompassion.Adapter.ReceiverAdapter
import com.example.handsofcompassion.Data.Receiver
import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.R
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID
import javax.inject.Inject


class ReceiverFireStore @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val context: Context
    ) {

    fun createReceiver(
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

            val userReference = firestore.collection("Receivers").document(id)

            userReference.set(donorDataMap).addOnCompleteListener {

                listneers.onSucess(context.getString(R.string.sucessoDoardo))

            }.addOnFailureListener {

                listneers.onFailure(context.getString(R.string.erroserver))

            }
        }
    }
    @SuppressLint("NotifyDatasetChanged")
    fun getReceiver(
        receiversList: MutableList<Receiver>,
        adapter: ReceiverAdapter
    ) {

        firestore.collection("Receivers")
            .orderBy("name").get()
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    for (document in task.result) {

                        val receivers = document.toObject(Receiver::class.java)
                        receiversList.add(receivers)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
    }

    @SuppressLint("NotifyDatasetChanged")
    fun searchReceivers(
        typedText: String,
        receiversList: MutableList<Receiver>,
        adapter: ReceiverAdapter
    ) {
        val query = firestore.collection("Receivers")
            .orderBy("name")
            .startAt(typedText)
            .endAt(typedText + "\uf8ff")
            .limit(3)

        receiversList.clear()
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val newReceiversList = mutableListOf<Receiver>()

                for (document in task.result) {
                    val receiver = document.toObject(Receiver::class.java)
                    newReceiversList.add(receiver)
                }

                receiversList.addAll(newReceiversList) // Adiciona os novos receptores à lista existente
                adapter.notifyDataSetChanged() // Notifica o RecyclerView sobre as mudanças
            }
        }
    }

    @SuppressLint("NotifyDatasetChanged")
    fun searchReceiversCPF(
        typedText: String,
        receiversList: MutableList<Receiver>,
        adapter: ReceiverAdapter
    ) {
        val query = firestore.collection("Receivers")
            .orderBy("cpf")
            .startAt(typedText)
            .endAt(typedText + "\uf8ff")
            .limit(1)

        receiversList.clear()
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val newReceiversList = mutableListOf<Receiver>()

                for (document in task.result) {
                    val receiver = document.toObject(Receiver::class.java)
                    newReceiversList.add(receiver)
                }

                receiversList.addAll(newReceiversList) // Adiciona os novos receptores à lista existente
                adapter.notifyDataSetChanged() // Notifica o RecyclerView sobre as mudanças
            }
        }
    }

    @SuppressLint("NotifyDatasetChanged")
    fun updateReceivers(
        name: String,
        cpf: String,
        phone: String,
        email: String,
        address: String,
        birth: String,
        id: String,
        adapter: ReceiverAdapter,
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

            firestore.collection("Receivers").document(id)
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