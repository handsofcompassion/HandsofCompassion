package com.example.handsofcompassion.Repository

import com.example.handsofcompassion.Data.FireStore.StockFireStore
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
}