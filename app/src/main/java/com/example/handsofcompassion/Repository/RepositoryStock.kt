package com.example.handsofcompassion.Repository

import android.annotation.SuppressLint
import com.example.handsofcompassion.Adapter.AdapterBasicBasket
import com.example.handsofcompassion.Adapter.AdapterMansClothing
import com.example.handsofcompassion.Adapter.AdapterMensChildrenClothing
import com.example.handsofcompassion.Adapter.AdapterNonPerishable
import com.example.handsofcompassion.Adapter.AdapterToys
import com.example.handsofcompassion.Adapter.AdapterWomansChildrenClothing
import com.example.handsofcompassion.Adapter.AdapterWomansClothing
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

        stockFireStore.saveNonPerishable(foods, validity, amount, listeners)

    }

    fun saveBasicBasket(
        items: MutableList<String?>,
        listneers: AuthListneers
    ) {

        stockFireStore.saveBasicBasket(
            items,
            listneers
        )

    }

    fun mensClothingSave(
        typeClothing: String,
        size: String,
        state: String,
        listeners: AuthListneers
    ) {
        stockFireStore.mensClothingSave(typeClothing, size, state, listeners)
    }

    fun mensClothingChildrenSave(
        typeClothing: String,
        size: String,
        state: String,
        listeners: AuthListneers
    ) {
        stockFireStore.mensClothingChildrenSave(typeClothing, size, state, listeners)
    }

    fun womansClothingSave(
        typeClothing: String,
        size: String,
        state: String,
        listeners: AuthListneers
    ) {
        stockFireStore.womansClothingSave(typeClothing, size, state, listeners)
    }

    fun womanClothingChildrenSave(
        typeClothing: String,
        size: String,
        state: String,
        listeners: AuthListneers
    ) {
        stockFireStore.womanClothingChildrenSave(typeClothing, size, state, listeners)
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

    fun updateNonPerecibles(
        id: String,
        foods: String,
        validity: String,
        amount: String,
        adapter: AdapterNonPerishable,
        listeners: AuthListneers
    ) {

        stockFireStore.updateNonPerecibles(
            id,
            foods,
            validity,
            amount,
            adapter,
            listeners
        )

    }

    fun updateBasicBasket(
        id: String,
        items: MutableList<BasicBasket>,
        adapter: AdapterBasicBasket,
        listeners: AuthListneers
    ) {

        stockFireStore.updateBasicBasket(
            id,
            items,
            adapter,
            listeners
        )

    }

    fun updateMensClothing(
        id: String,
        typeClothing: String,
        sizeClothing: String,
        stateClothing: String,
        adapter: AdapterMansClothing,
        listeners: AuthListneers
    ) {

        stockFireStore.updateMensClothing(
            id,
            typeClothing,
            sizeClothing,
            stateClothing,
            adapter,
            listeners
        )
    }

    fun updateMensChildrenClothing(
        id: String,
        typeClothing: String,
        size: String,
        state: String,
        adapter: AdapterMensChildrenClothing,
        listeners: AuthListneers
    ) {

        stockFireStore.updateMensChildrenClothing(
            id,
            typeClothing,
            size,
            state,
            adapter,
            listeners
        )

    }

    fun updateWomansClothing(
        id: String,
        typeClothing: String,
        size: String,
        state: String,
        adapter: AdapterWomansClothing,
        listeners: AuthListneers
    ) {

        stockFireStore.updateWomansClothing(
            id,
            typeClothing,
            size,
            state,
            adapter,
            listeners
        )

    }

    fun updateWomansChildrenClothing(
        id: String,
        typeClothing: String,
        size: String,
        state: String,
        adapter: AdapterWomansChildrenClothing,
        listeners: AuthListneers
    ) {

        stockFireStore.updateWomansChildrenClothing(
            id,
            typeClothing,
            size,
            state,
            adapter,
            listeners
        )


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

        stockFireStore.updateToys(
            id,
            type,
            state,
            amount,
            adapter,
            listeners
        )
    }

    fun searchBasketBasic(
        typedText: String,
        basicBasketsList: MutableList<BasicBasket>,
        adapter: AdapterBasicBasket
    ) {

        stockFireStore.searchBasketBasic(
            typedText,
            basicBasketsList,
            adapter
        )

    }

    fun searchNonPerecibles(
        typedText: String,
        nonPerishableList: MutableList<NonPerishable>,
        adapter: AdapterNonPerishable
    ) {

        stockFireStore.searchNonPerecibles(
            typedText,
            nonPerishableList,
            adapter
        )

    }

    fun searchMensClothing(
        typedText: String,
        mensClothingList: MutableList<MensClothing>,
        adapter: AdapterMansClothing
    ) {

        stockFireStore.searchMensClothing(
            typedText,
            mensClothingList,
            adapter
        )

    }

    fun searchMensClothingChildren(
        typedText: String,
        mensClothingChildrenList: MutableList<MensChildrenClothing>,
        adapter: AdapterMansClothing
    ) {

        stockFireStore.searchMensClothingChildren(
            typedText,
            mensClothingChildrenList,
            adapter
        )
    }

    fun searchWomansCLothing(
        typedText: String,
        womansCLothingList: MutableList<WomanClothing>,
        adapter: AdapterWomansClothing
    ) {

        stockFireStore.searchWomansCLothing(
            typedText,
            womansCLothingList,
            adapter
        )

    }

    fun searchWomansCLothingChildren(
        typedText: String,
        womansChildrenList: MutableList<WomanChildrenClothing>,
        adapter: AdapterWomansChildrenClothing
    ) {

        stockFireStore.searchWomansCLothingChildren(
            typedText,
            womansChildrenList,
            adapter
        )

    }

    fun searchToys(
        typedText: String,
        toysList: MutableList<Toys>,
        adapter: AdapterToys
    ) {

        stockFireStore.searchToys(
            typedText,
            toysList,
            adapter
        )

    }
}