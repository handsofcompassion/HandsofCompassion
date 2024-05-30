package com.example.handsofcompassion.Data.FireStore

import android.annotation.SuppressLint
import android.content.Context
import com.example.handsofcompassion.Adapter.AdapterBasicBasket
import com.example.handsofcompassion.Adapter.AdapterMansClothing
import com.example.handsofcompassion.Adapter.AdapterMensChildrenClothing
import com.example.handsofcompassion.Adapter.AdapterNonPerishable
import com.example.handsofcompassion.Adapter.AdapterToys
import com.example.handsofcompassion.Adapter.AdapterWomansChildrenClothing
import com.example.handsofcompassion.Adapter.AdapterWomansClothing
import com.example.handsofcompassion.Adapter.DonorAdapter
import com.example.handsofcompassion.Data.BasicBasket
import com.example.handsofcompassion.Data.Donor
import com.example.handsofcompassion.Data.MensChildrenClothing
import com.example.handsofcompassion.Data.MensClothing
import com.example.handsofcompassion.Data.NonPerishable
import com.example.handsofcompassion.Data.Toys
import com.example.handsofcompassion.Data.WomanChildrenClothing
import com.example.handsofcompassion.Data.WomanClothing
import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Lists.ToysList
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

    @SuppressLint("NotifyDatasetChanged")
    fun getNonPerecible(
        nonPerecibleList: MutableList<NonPerishable>,
        adapter: AdapterNonPerishable
    ) {

        firestore.collection("NonPerishable")
            .orderBy("type").get()
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    for (document in task.result) {

                        val nonPerecible = document.toObject(NonPerishable::class.java)
                        nonPerecibleList.add(nonPerecible)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
    }

    @SuppressLint("NotifyDatasetChanged")
    fun getBasicBasket(
        basicBasketList: MutableList<BasicBasket>,
        adapter: AdapterBasicBasket
    ) {

        firestore.collection("BasicBasket")
            .orderBy("item1").get()
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    for (document in task.result) {

                        val basicBasket = document.toObject(BasicBasket::class.java)
                        basicBasketList.add(basicBasket)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
    }

@SuppressLint("NotifyDatasetChanged")
    fun getMensClhoting(
        mensClothingList: MutableList<MensClothing>,
        adapter: AdapterMansClothing
    ) {

        firestore.collection("MensClothing")
            .orderBy("typeClothing").get()
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    for (document in task.result) {

                        val mensClothing = document.toObject(MensClothing::class.java)
                        mensClothingList.add(mensClothing)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
    }

    @SuppressLint("NotifyDatasetChanged")
    fun getMensClhotingChildren(
        mensClothingChildrenList: MutableList<MensChildrenClothing>,
        adapter: AdapterMensChildrenClothing
    ) {

        firestore.collection("MensClothingChildren")
            .orderBy("typeClothing").get()
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    for (document in task.result) {

                        val mensClothingChildre = document.toObject(MensChildrenClothing::class.java)
                        mensClothingChildrenList.add(mensClothingChildre)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
    }

    @SuppressLint("NotifyDatasetChanged")
    fun getWomansClothing(
        womansClothingList: MutableList<WomanClothing>,
        adapter: AdapterWomansClothing
    ) {

        firestore.collection("WomansClothing")
            .orderBy("typeClothing").get()
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    for (document in task.result) {

                        val womansClothing = document.toObject(WomanClothing::class.java)
                        womansClothingList.add(womansClothing)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
    }

    @SuppressLint("NotifyDatasetChanged")
    fun getWomansClhotingChildren(
        womanClothingChildrenList: MutableList<WomanChildrenClothing>,
        adapter: AdapterWomansChildrenClothing
    ) {

        firestore.collection("MensClothingChildren")
            .orderBy("typeClothing").get()
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    for (document in task.result) {

                        val womansClothingChildre = document.toObject(WomanChildrenClothing::class.java)
                        womanClothingChildrenList.add(womansClothingChildre)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
    }

    @SuppressLint("NotifyDatasetChanged")
    fun getToys(
        toysList: MutableList<Toys>,
        adapter: AdapterToys
    ) {

        firestore.collection("Toys")
            .orderBy("type").get()
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    for (document in task.result) {

                        val toys = document.toObject(Toys::class.java)
                        toysList.add(toys)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
    }


}

