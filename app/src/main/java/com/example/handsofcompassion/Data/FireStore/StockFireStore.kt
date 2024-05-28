package com.example.handsofcompassion.Data.FireStore

import android.content.Context
import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID
import javax.inject.Inject


class StockFireStore @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val context: Context
) {


    fun saveNonPerishable(
        foods: String,
        validity: String,
        amount: String,
        listeners: AuthListneers
    ) {

        if (foods.isBlank() || validity.isBlank() || amount.isBlank() ) {
            listeners.onFailure(context.getString(R.string.preencha))
            return
        }

        val nonPerishableData = hashMapOf(
            "foods" to foods,
            "validity" to validity,
            "amount" to amount,
        )


        val id = UUID.randomUUID().toString()
        val db = FirebaseFirestore.getInstance().collection("NonPerishable").document(id)


        db.set(nonPerishableData)
            .addOnSuccessListener {
                listeners.onSucess(context.getString(R.string.alimentosalvo))
            }
            .addOnFailureListener { e ->
                listeners.onFailure(context.getString(R.string.erroserver))
            }
    }


    fun saveBasicBasket(
        items: MutableList<String?>,
        listeners: AuthListneers
    ) {
        if (items.size < 5) {
            listeners.onFailure(context.getString(R.string.selecione5itens))
            return
        } else if (items.size > 12) {
            listeners.onFailure(context.getString(R.string.selecione12itens))
            return
        }

        val basketData = hashMapOf<String, String>()
        items.forEachIndexed { index, item ->
            basketData["item${index + 1}"] = item ?: ""
        }

        val id = UUID.randomUUID().toString()

        val db = FirebaseFirestore.getInstance().collection("BasicBasket").document(id)

        db.set(basketData).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                listeners.onSucess(context.getString(R.string.sucessoCesta))
            } else {
                listeners.onFailure(context.getString(R.string.erroserver))
            }
        }.addOnFailureListener { e ->
            listeners.onFailure(context.getString(R.string.erroserver))
        }
    }



    fun mensClothingSave(
        typeClothing: String,
        sizeClothing: String,
        stateClothing: String,
        listeners: AuthListneers
    ) {
        saveClothing("MensClothing", typeClothing, sizeClothing, stateClothing, listeners)
    }

    fun mensClothingChildrenSave(
        typeClothing: String,
        sizeClothing: String,
        stateClothing: String,
        listeners: AuthListneers
    ) {
        saveClothing("MensClothingChildren", typeClothing, sizeClothing, stateClothing, listeners)
    }

    fun womansClothingSave(
        typeClothing: String,
        sizeClothing: String,
        stateClothing: String,
        listeners: AuthListneers
    ) {
        saveClothing("WomansClothing", typeClothing, sizeClothing, stateClothing, listeners)
    }

    fun womanClothingChildrenSave(
        typeClothing: String,
        sizeClothing: String,
        stateClothing: String,
        listeners: AuthListneers
    ) {
        saveClothing("WomansClothingChildren", typeClothing, sizeClothing, stateClothing, listeners)
    }
    fun toysSave(
        type: String,
        state: String,
        amount: String,
        listeners: AuthListneers
    ) {
        if (type.isNullOrBlank() || state.isNullOrBlank() || amount.isBlank()) {
            listeners.onFailure(context.getString(R.string.preencha))
            return
        }

        val toyData = hashMapOf(
            "type" to type,
            "state" to state,
            "amount" to amount
        )

        val id = UUID.randomUUID().toString()
        val db = FirebaseFirestore.getInstance().collection("Toys").document(id)

        db.set(toyData)
            .addOnSuccessListener {
                listeners.onSucess(context.getString(R.string.brinquedosalvo))
            }
            .addOnFailureListener { e ->
                listeners.onFailure(context.getString(R.string.erroserver))
            }
    }


    private fun saveClothing(
        collection: String,
        typeClothing: String,
        sizeClothing: String,
        stateClothing: String,
        listeners: AuthListneers
    ) {
        if (typeClothing.isBlank() || sizeClothing.isBlank() || stateClothing.isBlank()) {
            listeners.onFailure(context.getString(R.string.preencha))
            return
        }

        val clothingData = hashMapOf(
            "typeClothing" to typeClothing,
            "sizeClothing" to sizeClothing,
            "stateClothing" to stateClothing
        )

        val id = UUID.randomUUID().toString()
        val db = firestore.collection(collection).document(id)

        db.set(clothingData)
            .addOnSuccessListener {
                listeners.onSucess(context.getString(R.string.roupasalvo))
            }
            .addOnFailureListener { e ->
                listeners.onFailure(context.getString(R.string.erroserver))
            }
    }
}

