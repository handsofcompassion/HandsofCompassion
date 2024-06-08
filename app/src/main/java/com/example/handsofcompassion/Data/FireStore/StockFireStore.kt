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
import com.example.handsofcompassion.Data.BasicBasket
import com.example.handsofcompassion.Data.MensChildrenClothing
import com.example.handsofcompassion.Data.MensClothing
import com.example.handsofcompassion.Data.NonPerishable
import com.example.handsofcompassion.Data.Toys
import com.example.handsofcompassion.Data.WomanChildrenClothing
import com.example.handsofcompassion.Data.WomanClothing
import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.R
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import java.util.UUID
import javax.inject.Inject


class StockFireStore @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val context: Context
) {


    private val nonPerecible: String = "NonPerishable"
    private val basicBasket: String = "BasicBasket"
    private val mensClothing: String = "MensClothing"
    private val mensClothingChildren: String = "MensClothingChildren"
    private val womensClothing: String = "WomansClothing"
    private val womensClothingChildren: String = "WomansClothingChildren"
    private val toys: String = "Toys"


    fun saveNonPerishable(
        foods: String,
        validity: String,
        amount: String,
        listeners: AuthListneers
    ) {

        if (foods.isBlank() || validity.isBlank() || amount.isBlank()) {
            listeners.onFailure(context.getString(R.string.preencha))
            return
        }

        val id = UUID.randomUUID().toString()
        val nonPerishableData = hashMapOf(
            "id" to id,
            "foods" to foods,
            "validity" to validity,
            "amount" to amount,
        )


        val db = firestore.collection(nonPerecible).document(id)


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

        val id = UUID.randomUUID().toString()

        val basketData = hashMapOf<String, Any>(
            "id" to id
        )

        items.forEachIndexed { index, item ->
            basketData["item${index + 1}"] = item ?: ""
        }


        val db = firestore.collection(basicBasket).document(id)
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
        saveClothing(mensClothing, typeClothing, sizeClothing, stateClothing, listeners)
    }

    fun mensClothingChildrenSave(
        typeClothing: String,
        sizeClothing: String,
        stateClothing: String,
        listeners: AuthListneers
    ) {
        saveClothing(mensClothingChildren, typeClothing, sizeClothing, stateClothing, listeners)
    }

    fun womansClothingSave(
        typeClothing: String,
        sizeClothing: String,
        stateClothing: String,
        listeners: AuthListneers
    ) {
        saveClothing(womensClothing, typeClothing, sizeClothing, stateClothing, listeners)
    }

    fun womanClothingChildrenSave(
        typeClothing: String,
        sizeClothing: String,
        stateClothing: String,
        listeners: AuthListneers
    ) {
        saveClothing(womensClothingChildren, typeClothing, sizeClothing, stateClothing, listeners)
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
        val id = UUID.randomUUID().toString()

        val toyData = hashMapOf(
            "id" to id,
            "type" to type,
            "state" to state,
            "amount" to amount
        )


        val db = firestore.collection(toys).document(id)

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
        size: String,
        state: String,
        listeners: AuthListneers
    ) {
        if (typeClothing.isBlank() || size.isBlank() || state.isBlank()) {
            listeners.onFailure(context.getString(R.string.preencha))
            return
        }
        val id = UUID.randomUUID().toString()

        val clothingData = hashMapOf(
            "id" to id,
            "typeClothing" to typeClothing,
            "sizeClothing" to size,
            "stateClothing" to state
        )

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

        firestore.collection(nonPerecible)
            .orderBy("foods").get()
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

        firestore.collection(basicBasket)
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

        firestore.collection(mensClothing)
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

        firestore.collection(mensClothingChildren)
            .orderBy("typeClothing").get()
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    for (document in task.result) {

                        val mensClothingChildre =
                            document.toObject(MensChildrenClothing::class.java)
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

        firestore.collection(womensClothing)
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

        firestore.collection(womensClothingChildren)
            .orderBy("typeClothing").get()
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    for (document in task.result) {

                        val womansClothingChildre =
                            document.toObject(WomanChildrenClothing::class.java)
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

        firestore.collection(toys)
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

    @SuppressLint("NotifyDatasetChanged")
    fun updateNonPerecibles(
        id: String,
        foods: String,
        validity: String,
        amount: String,
        adapter: AdapterNonPerishable,
        listeners: AuthListneers
    ) {
        if (foods.isBlank() || validity.isBlank() || amount.isBlank()) {
            listeners.onFailure(context.getString(R.string.preencha))
            return
        }


        val nonPerishableData = hashMapOf(
            "foods" to foods,
            "validity" to validity,
            "amount" to amount,
        )


        val db = firestore.collection(nonPerecible).document(id)


        db.update(nonPerishableData.toMap())
            .addOnSuccessListener {
                listeners.onSucess(context.getString(R.string.dadosatualizados))
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                listeners.onFailure(context.getString(R.string.erroserver))
            }
    }

    @SuppressLint("NotifyDatasetChanged")
    fun updateBasicBasket(
        id: String,
        items: MutableList<BasicBasket>,
        adapter: AdapterBasicBasket,
        listeners: AuthListneers
    ) {

        if (items.size < 5) {
            listeners.onFailure(context.getString(R.string.selecione5itens))
            return
        } else if (items.size > 12) {
            listeners.onFailure(context.getString(R.string.selecione12itens))
            return
        }

        val basketData = hashMapOf<String, Any>(
            "id" to id
        )

        items.forEachIndexed { index, item ->
            basketData["item${index + 1}"] = item ?: ""
        }

        val db = firestore.collection("basicBasket").document(id)
        db.update(basketData).addOnSuccessListener {
            listeners.onSucess(context.getString(R.string.dadosatualizados))
            adapter.notifyDataSetChanged()
        }
            .addOnFailureListener { e ->
                listeners.onFailure(context.getString(R.string.erroserver))
            }
    }

    @SuppressLint("NotifyDatasetChanged")
    fun updateMensClothing(
        id: String,
        typeClothing: String,
        sizeClothing: String,
        stateClothing: String,
        adapter: AdapterMansClothing,
        listeners: AuthListneers
    ) {
        if (typeClothing.isBlank() || sizeClothing.isBlank() || stateClothing.isBlank()) {
            listeners.onFailure(context.getString(R.string.preencha))
            return
        }

        val clothingData = hashMapOf(
            "id" to id,
            "typeClothing" to typeClothing,
            "sizeClothing" to sizeClothing,
            "stateClothing" to stateClothing
        )


        val db = firestore.collection(mensClothing).document(id)
        db.update(clothingData.toMap()).addOnSuccessListener {
            listeners.onSucess(context.getString(R.string.dadosatualizados))
            adapter.notifyDataSetChanged()
        }
            .addOnFailureListener { e ->
                listeners.onFailure(context.getString(R.string.erroserver))
            }

    }

    @SuppressLint("NotifyDatasetChanged")
    fun updateMensChildrenClothing(
        id: String,
        typeClothing: String,
        sizeClothing: String,
        stateClothing: String,
        adapter: AdapterMensChildrenClothing,
        listeners: AuthListneers
    ) {
        if (typeClothing.isBlank() || sizeClothing.isBlank() || stateClothing.isBlank()) {
            listeners.onFailure(context.getString(R.string.preencha))
            return
        }

        val clothingData = hashMapOf(
            "id" to id,
            "typeClothing" to typeClothing,
            "sizeClothing" to sizeClothing,
            "stateClothing" to stateClothing
        )


        val db = firestore.collection(mensClothingChildren).document(id)
        db.update(clothingData.toMap()).addOnSuccessListener {
            listeners.onSucess(context.getString(R.string.dadosatualizados))
            adapter.notifyDataSetChanged()
        }
            .addOnFailureListener { e ->
                listeners.onFailure(context.getString(R.string.erroserver))
            }

    }

    @SuppressLint("NotifyDatasetChanged")
    fun updateWomansClothing(
        id: String,
        typeClothing: String,
        sizeClothing: String,
        stateClothing: String,
        adapter: AdapterWomansClothing,
        listeners: AuthListneers
    ) {
        if (typeClothing.isBlank() || sizeClothing.isBlank() || stateClothing.isBlank()) {
            listeners.onFailure(context.getString(R.string.preencha))
            return
        }

        val clothingData = hashMapOf(
            "id" to id,
            "typeClothing" to typeClothing,
            "sizeClothing" to sizeClothing,
            "stateClothing" to stateClothing
        )


        val db = firestore.collection(womensClothing).document(id)
        db.update(clothingData.toMap()).addOnSuccessListener {
            listeners.onSucess(context.getString(R.string.dadosatualizados))
            adapter.notifyDataSetChanged()
        }
            .addOnFailureListener { e ->
                listeners.onFailure(context.getString(R.string.erroserver))
            }

    }

    @SuppressLint("NotifyDatasetChanged")
    fun updateWomansChildrenClothing(
        id: String,
        typeClothing: String,
        sizeClothing: String,
        stateClothing: String,
        adapter: AdapterWomansChildrenClothing,
        listeners: AuthListneers
    ) {
        if (typeClothing.isBlank() || sizeClothing.isBlank() || stateClothing.isBlank()) {
            listeners.onFailure(context.getString(R.string.preencha))
            return
        }

        val clothingData = hashMapOf(
            "id" to id,
            "typeClothing" to typeClothing,
            "sizeClothing" to sizeClothing,
            "stateClothing" to stateClothing
        )


        val db = firestore.collection(womensClothingChildren).document(id)
        db.update(clothingData.toMap()).addOnSuccessListener {
            listeners.onSucess(context.getString(R.string.dadosatualizados))
            adapter.notifyDataSetChanged()
        }
            .addOnFailureListener { e ->
                listeners.onFailure(context.getString(R.string.erroserver))
            }

    }

    @SuppressLint("NotifyDatasetChanged")
    fun updateToys(
        id: String,
        type: String,
        state: String,
        amount: String,
        adapter: AdapterToys,
        listeners: AuthListneers
    ) {
        if (type.isNullOrBlank() || state.isNullOrBlank() || amount.isBlank()) {
            listeners.onFailure(context.getString(R.string.preencha))
            return
        }

        val toyData = hashMapOf(
            "id" to id,
            "type" to type,
            "state" to state,
            "amount" to amount
        )


        val db = firestore.collection(toys).document(id)
        db.update(toyData.toMap()).addOnSuccessListener {
            listeners.onSucess(context.getString(R.string.dadosatualizados))
            adapter.notifyDataSetChanged()
        }
            .addOnFailureListener { e ->
                listeners.onFailure(context.getString(R.string.erroserver))
            }

    }

    @SuppressLint("NotifyDatasetChanged")
    fun searchBasketBasic(
        typedText: String,
        basicBasketsList: MutableList<BasicBasket>,
        adapter: AdapterBasicBasket
    ) {
        val queries = (1..12).map { itemIndex ->
            firestore.collection(basicBasket)
                .orderBy("item$itemIndex")
                .startAt(typedText)
                .endAt(typedText + "\uf8ff")
                .limit(3)
                .get()
        }

        basicBasketsList.clear()

        Tasks.whenAllSuccess<QuerySnapshot>(queries)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val newBasicBasketList = mutableListOf<BasicBasket>()

                    task.result.forEach { querySnapshot ->
                        for (document in querySnapshot) {
                            val basicBasket = document.toObject(BasicBasket::class.java)
                            newBasicBasketList.add(basicBasket)
                        }
                    }

                    basicBasketsList.addAll(newBasicBasketList.distinctBy { it.id }) // Remove duplicatas e adiciona à lista existente
                    adapter.notifyDataSetChanged() // Notifica o RecyclerView sobre as mudanças
                }
            }
    }

    @SuppressLint("NotifyDatasetChanged")
    fun searchNonPerecibles(
        typedText: String,
        nonPerishableList: MutableList<NonPerishable>,
        adapter: AdapterNonPerishable
    ) {
        val query = firestore.collection(nonPerecible)
            .orderBy("foods")
            .startAt(typedText)
            .endAt(typedText + "\uf8ff")
            .limit(2)

        nonPerishableList.clear()
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val newNonPerecibleList = mutableListOf<NonPerishable>()

                for (document in task.result) {
                    val nonPerecible = document.toObject(NonPerishable::class.java)
                    newNonPerecibleList.add(nonPerecible)
                }

                nonPerishableList.addAll(newNonPerecibleList) // Adiciona os novos receptores à lista existente
                adapter.notifyDataSetChanged() // Notifica o RecyclerView sobre as mudanças
            }
        }

    }

    @SuppressLint("NotifyDatasetChanged")
    fun searchMensClothing(
        typedText: String,
        mensClothingList: MutableList<MensClothing>,
        adapter: AdapterMansClothing
    ) {
        val query = firestore.collection(mensClothing)
            .orderBy("typeClothing")
            .startAt(typedText)
            .endAt(typedText + "\uf8ff")
            .limit(2)

        mensClothingList.clear()
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val newMenClothingList = mutableListOf<MensClothing>()

                for (document in task.result) {
                    val mensClothing = document.toObject(MensClothing::class.java)
                    newMenClothingList.add(mensClothing)
                }

                mensClothingList.addAll(newMenClothingList) // Adiciona os novos receptores à lista existente
                adapter.notifyDataSetChanged() // Notifica o RecyclerView sobre as mudanças
            }
        }
    }

    @SuppressLint("NotifyDatasetChanged")
    fun searchMensClothingChildren(
        typedText: String,
        mensClothingChildrenList: MutableList<MensChildrenClothing>,
        adapter: AdapterMensChildrenClothing
    ) {

        val query = firestore.collection(mensClothingChildren)
            .orderBy("typeClothing")
            .startAt(typedText)
            .endAt(typedText + "\uf8ff")
            .limit(2)

        mensClothingChildrenList.clear()
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val newMenClothingChildrenList = mutableListOf<MensChildrenClothing>()

                for (document in task.result) {
                    val mensClothingChildren = document.toObject(MensChildrenClothing::class.java)
                    newMenClothingChildrenList.add(mensClothingChildren)
                }

                mensClothingChildrenList.addAll(newMenClothingChildrenList) // Adiciona os novos receptores à lista existente
                adapter.notifyDataSetChanged() // Notifica o RecyclerView sobre as mudanças
            }
        }

    }

    @SuppressLint("NotifyDatasetChanged")
    fun searchWomansCLothing(
        typedText: String,
        womansCLothingList: MutableList<WomanClothing>,
        adapter: AdapterWomansClothing
    ) {

        val query = firestore.collection(womensClothing)
            .orderBy("typeClothing")
            .startAt(typedText)
            .endAt(typedText + "\uf8ff")
            .limit(2)

        womansCLothingList.clear()
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val newWomansClothingList = mutableListOf<WomanClothing>()

                for (document in task.result) {
                    val womansClothing = document.toObject(WomanClothing::class.java)
                    newWomansClothingList.add(womansClothing)
                }

                womansCLothingList.addAll(newWomansClothingList) // Adiciona os novos receptores à lista existente
                adapter.notifyDataSetChanged() // Notifica o RecyclerView sobre as mudanças
            }
        }
    }

    @SuppressLint("NotifyDatasetChanged")
    fun searchWomansCLothingChildren(
        typedText: String,
        womansChildrenList: MutableList<WomanChildrenClothing>,
        adapter: AdapterWomansChildrenClothing
    ) {

        val query = firestore.collection(womensClothingChildren)
            .orderBy("typeClothing")
            .startAt(typedText)
            .endAt(typedText + "\uf8ff")
            .limit(2)

        womansChildrenList.clear()
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val newWomanClothingChildrenList = mutableListOf<WomanChildrenClothing>()

                for (document in task.result) {
                    val womanClothingChildren = document.toObject(WomanChildrenClothing::class.java)
                    newWomanClothingChildrenList.add(womanClothingChildren)
                }

                womansChildrenList.addAll(newWomanClothingChildrenList) // Adiciona os novos receptores à lista existente
                adapter.notifyDataSetChanged() // Notifica o RecyclerView sobre as mudanças
            }
        }
    }

    @SuppressLint("NotifyDatasetChanged")
    fun searchToys(
        typedText: String,
        toysList: MutableList<Toys>,
        adapter: AdapterToys
    ) {

        val query = firestore.collection(toys)
            .orderBy("type")
            .startAt(typedText)
            .endAt(typedText + "\uf8ff")
            .limit(3)

        toysList.clear()
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val newToysList = mutableListOf<Toys>()

                for (document in task.result) {
                    val toys = document.toObject(Toys::class.java)
                    newToysList.add(toys)
                }

                toysList.addAll(newToysList) // Adiciona os novos receptores à lista existente
                adapter.notifyDataSetChanged() // Notifica o RecyclerView sobre as mudanças
            }
        }

    }

    fun nonPereciblesUpdateOrDelete(
        id: String,
        amount: String?,
        adapter: AdapterNonPerishable,
        position: Int,
        nonPerishableList: MutableList<NonPerishable>,
        listeners: AuthListneers
    ) {
        if (amount.isNullOrEmpty()) {
            listeners.onFailure(context.getString(R.string.preencha))
            return
        }

        val decrementValue = amount.toIntOrNull() ?: 0
        val db = firestore.collection(nonPerecible).document(id)

        db.get().addOnSuccessListener { document ->
            if (document != null && document.exists()) {
                val currentAmount = document.getString("amount")?.toIntOrNull() ?: 0
                val newAmount = currentAmount - decrementValue

                if (newAmount > 1) {
                    val nonPerishableData = hashMapOf<String, Any?>(
                        "amount" to newAmount.toString(),
                    )

                    db.update(nonPerishableData.toMap())
                        .addOnSuccessListener {
                            listeners.onSucess(context.getString(R.string.dadosatualizados))
                            adapter.notifyDataSetChanged()
                        }
                        .addOnFailureListener { e ->
                            listeners.onFailure(context.getString(R.string.erroserver))
                        }
                } else if (newAmount <= 0) {
                    db.delete()
                        .addOnSuccessListener {
                            try {
                                nonPerishableList.removeAt(position)
                                adapter.notifyDataSetChanged()
                            } catch (e: IndexOutOfBoundsException) {
                                // Handle index out of bounds exception
                                e.printStackTrace()
                            }
                        }
                        .addOnFailureListener { e ->
                            listeners.onFailure(context.getString(R.string.erroserver))
                        }
                }
            }
        }.addOnFailureListener { e ->
            listeners.onFailure(context.getString(R.string.erroserver))
        }
    }

    fun toysUpdateOrDelete(
        id: String,
        amount: String?,
        adapter: AdapterToys,
        position: Int,
        toysList: MutableList<Toys>,
        listeners: AuthListneers
    ) {
        if (amount.isNullOrEmpty()) {
            listeners.onFailure(context.getString(R.string.preencha))
            return
        }

        val decrementValue = amount.toIntOrNull() ?: 0
        val db = firestore.collection(toys).document(id)

        db.get().addOnSuccessListener { document ->
            if (document != null && document.exists()) {
                val currentAmount = document.getString("amount")?.toIntOrNull() ?: 0
                val newAmount = currentAmount - decrementValue

                if (newAmount > 1) {
                    val nonPerishableData = hashMapOf<String, Any?>(
                        "amount" to newAmount.toString(),
                    )

                    db.update(nonPerishableData.toMap())
                        .addOnSuccessListener {
                            listeners.onSucess(context.getString(R.string.dadosatualizados))
                            adapter.notifyDataSetChanged()
                        }
                        .addOnFailureListener { e ->
                            listeners.onFailure(context.getString(R.string.erroserver))
                        }
                } else if (newAmount <= 0) {
                    db.delete()
                        .addOnSuccessListener {
                            try {
                                toysList.removeAt(position)
                                adapter.notifyDataSetChanged()
                            } catch (e: IndexOutOfBoundsException) {
                                // Handle index out of bounds exception
                                e.printStackTrace()
                            }
                        }
                        .addOnFailureListener { e ->
                            listeners.onFailure(context.getString(R.string.erroserver))
                        }
                }
            }
        }.addOnFailureListener { e ->
            listeners.onFailure(context.getString(R.string.erroserver))
        }
    }



}


