package com.example.handsofcompassion.Repository

import android.annotation.SuppressLint
import com.example.handsofcompassion.Adapter.AdapterBasicBasket
import com.example.handsofcompassion.Adapter.AdapterMansClothing
import com.example.handsofcompassion.Adapter.AdapterMensChildrenClothing
import com.example.handsofcompassion.Adapter.AdapterNonPerishable
import com.example.handsofcompassion.Adapter.AdapterToys
import com.example.handsofcompassion.Adapter.AdapterWomansChildrenClothing
import com.example.handsofcompassion.Adapter.AdapterWomansClothing
import com.example.handsofcompassion.Adapter.DonorAdapter
import com.example.handsofcompassion.Data.BasicBasket
import com.example.handsofcompassion.Data.FireStore.StockFireStore
import com.example.handsofcompassion.Data.MensChildrenClothing
import com.example.handsofcompassion.Data.MensClothing
import com.example.handsofcompassion.Data.NonPerishable
import com.example.handsofcompassion.Data.Toys
import com.example.handsofcompassion.Data.WomanChildrenClothing
import com.example.handsofcompassion.Data.WomanClothing
import com.example.handsofcompassion.Listneers.AuthListneers
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class RepositoryStock @Inject constructor(private val stockFireStore: StockFireStore) {


    fun saveNonPerishable(
        foods: String,
        validity: String,
        amount: String,
        listeners: AuthListneers
    ) {

        stockFireStore.saveNonPerishable(foods, validity, amount , listeners)

    }

    fun saveBasicBasket(
        items:MutableList<String?>,
        listneers: AuthListneers
    ) {

        stockFireStore.saveBasicBasket(
            items,
            listneers
        )

    }
    fun mensClothingSave(
        typeClothing: String,
        sizeClothing: String,
        stateClothing: String,
        listeners: AuthListneers
    ) {
        stockFireStore.mensClothingSave(typeClothing, sizeClothing, stateClothing, listeners)
    }

    fun mensClothingChildrenSave(
        typeClothing: String,
        sizeClothing: String,
        stateClothing: String,
        listeners: AuthListneers
    ) {
        stockFireStore.mensClothingChildrenSave(typeClothing, sizeClothing, stateClothing, listeners)
    }

    fun womansClothingSave(
        typeClothing: String,
        sizeClothing: String,
        stateClothing: String,
        listeners: AuthListneers
    ) {
        stockFireStore.womansClothingSave(typeClothing, sizeClothing, stateClothing, listeners)
    }

    fun womanClothingChildrenSave(
        typeClothing: String,
        sizeClothing: String,
        stateClothing: String,
        listeners: AuthListneers
    ) {
        stockFireStore.womanClothingChildrenSave(typeClothing, sizeClothing, stateClothing, listeners)
    }
    fun toysSave(
        type: String,
        state: String,
        amount: String,
        listeners: AuthListneers
    ) {

        stockFireStore.toysSave(type, state, amount, listeners)

    }

    fun getNonPerecible(
        nonPerecibleList: MutableList<NonPerishable>,
        adapter: AdapterNonPerishable
    ) {

        stockFireStore.getNonPerecible(
            nonPerecibleList,
            adapter
        )

    }

    fun getBasicBasket(
        basicBasketList: MutableList<BasicBasket>,
        adapter: AdapterBasicBasket
    ) {

        stockFireStore.getBasicBasket(
            basicBasketList,
            adapter
        )

    }
    fun getMensClhoting(
        mensClothingList: MutableList<MensClothing>,
        adapter: AdapterMansClothing
    ) {

        stockFireStore.getMensClhoting(
            mensClothingList,
            adapter
        )

    }

    @SuppressLint("NotifyDatasetChanged")
    fun getMensClhotingChildren(
        mensClothingChildrenList: MutableList<MensChildrenClothing>,
        adapter: AdapterMensChildrenClothing
    ) {

        stockFireStore.getMensClhotingChildren(mensClothingChildrenList, adapter)


    }

    fun getWomansClothing(
        womansClothingList: MutableList<WomanClothing>,
        adapter: AdapterWomansClothing
    ) {

        stockFireStore.getWomansClothing(womansClothingList, adapter)

    }

    @SuppressLint("NotifyDatasetChanged")
    fun getWomansClhotingChildren(
        womanClothingChildrenList: MutableList<WomanChildrenClothing>,
        adapter: AdapterWomansChildrenClothing
    ) {

        stockFireStore.getWomansClhotingChildren(womanClothingChildrenList, adapter)

    }

    fun getToys(
        toysList: MutableList<Toys>,
        adapter: AdapterToys
    ) {

        stockFireStore.getToys(
            toysList,
            adapter
        )

    }

    }