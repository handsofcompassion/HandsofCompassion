package com.example.handsofcompassion.ViewModel

import android.widget.CheckBox
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.Repository.RepositoryStock
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelStock @Inject constructor(private val repositoryStock: RepositoryStock) :
    ViewModel() {

    val selectedItemsCheckBox = mutableListOf<String?>()

    fun addItemToList(checkBox: CheckBox) {
        if (checkBox.isChecked) {
            selectedItemsCheckBox.add(checkBox.text.toString())
        }
    }

    fun saveNonPerishable(
        foods: String,
        validity: String,
        amount: String,
        listeners: AuthListneers
    ) {

        repositoryStock.saveNonPerishable(foods, validity, amount, listeners)

    }

    fun saveBasicBasket(
        items: MutableList<String?>,
        listneers: AuthListneers
    ) {

        viewModelScope.launch {

            repositoryStock.saveBasicBasket(
                items,
                listneers
            )
        }
    }

    fun mensClothingSave(
        typeClothing: String,
        sizeClothing: String,
        stateClothing: String,
        listeners: AuthListneers
    ) {

        viewModelScope.launch {
            repositoryStock.mensClothingSave(typeClothing, sizeClothing, stateClothing, listeners)
        }
    }

    fun mensClothingChildrenSave(
        typeClothing: String,
        sizeClothing: String,
        stateClothing: String,
        listeners: AuthListneers
    ) {

        viewModelScope.launch {
            repositoryStock.mensClothingChildrenSave(typeClothing, sizeClothing, stateClothing, listeners)
        }
    }

    fun womansClothingSave(
        typeClothing: String,
        sizeClothing: String,
        stateClothing: String,
        listeners: AuthListneers
    ) {

        viewModelScope.launch {
            repositoryStock.womansClothingSave(typeClothing, sizeClothing, stateClothing, listeners)
        }
    }

    fun womanClothingChildrenSave(
        typeClothing: String,
        sizeClothing: String,
        stateClothing: String,
        listeners: AuthListneers
    ) {
        viewModelScope.launch {
            repositoryStock.womanClothingChildrenSave(

                typeClothing, sizeClothing, stateClothing, listeners
            )
        }
    }

    fun toysSave(
        type: String,
        state: String,
        amount: String,
        listeners: AuthListneers
    ) {

        viewModelScope.launch {
            repositoryStock.toysSave(type, state, amount, listeners)
        }

    }


}