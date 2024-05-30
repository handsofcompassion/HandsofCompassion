package com.example.handsofcompassion.ViewModel

import android.annotation.SuppressLint
import android.widget.CheckBox
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.handsofcompassion.Adapter.AdapterBasicBasket
import com.example.handsofcompassion.Adapter.AdapterMansClothing
import com.example.handsofcompassion.Adapter.AdapterMensChildrenClothing
import com.example.handsofcompassion.Adapter.AdapterNonPerishable
import com.example.handsofcompassion.Adapter.AdapterToys
import com.example.handsofcompassion.Adapter.AdapterWomansChildrenClothing
import com.example.handsofcompassion.Adapter.AdapterWomansClothing
import com.example.handsofcompassion.Adapter.DonorAdapter
import com.example.handsofcompassion.Data.BasicBasket
import com.example.handsofcompassion.Data.MensChildrenClothing
import com.example.handsofcompassion.Data.MensClothing
import com.example.handsofcompassion.Data.NonPerishable
import com.example.handsofcompassion.Data.Toys
import com.example.handsofcompassion.Data.WomanChildrenClothing
import com.example.handsofcompassion.Data.WomanClothing
import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.Repository.RepositoryStock
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelStock @Inject constructor(private val repositoryStock: RepositoryStock) :
    ViewModel() {

        companion object {

            const val TYPE_TOY_EXTRA = "type_toy_extra"
            const val STATE_TOY_EXTRA = "state_toy_extra"
            const val AMOUNT_TOY_EXTRA = "amount_toy_extra"
            const val ITEM_1_EXTRA = "item_1_extra"
            const val ITEM_2_EXTRA = "item_2_extra"
            const val ITEM_3_EXTRA = "item_3_extra"
            const val ITEM_4_EXTRA = "item_4_extra"
            const val ITEM_5_EXTRA = "item_5_extra"
            const val ITEM_6_EXTRA = "item_6_extra"
            const val ITEM_7_EXTRA = "item_7_extra"
            const val ITEM_8_EXTRA = "item_8_extra"
            const val ITEM_9_EXTRA = "item_9_extra"
            const val ITEM_10_EXTRA = "item_10_extra"
            const val ITEM_11_EXTRA = "item_11_extra"
            const val ITEM_12_EXTRA = "item_12_extra"
            const val TYPE_FOOD = "type_food"
            const val VALIDATY_FOOD = "validaty_food"
            const val AMOUNT_FOOD = "amount_food"
            const val TYPE_CLOTHING_MEN = "type_clothing_men"
            const val SIZE_CLOTHING_MEN = "size_clothing_men"
            const val STATE_CLOTHING_MEN = "state_clothing_men"
            const val TYPE_CLOTHING_MEN_CHILDREN = "type_clothing_men_children"
            const val SIZE_CLOTHING_MEN_CHILDREN = "size_clothing_men_children"
            const val STATE_CLOTHING_MEN_CHILDREN = "state_clothing_men_children"
            const val TYPE_CLOTHING_WOMAN = "type_clothing_woman"
            const val SIZE_CLOTHING_WOMAN = "size_clothing_woman"
            const val STATE_CLOTHING_WOMAN = "state_clothing_woman"
            const val TYPE_CLOTHING_WOMAN_CHILDREN = "type_clothing_woman_children"
            const val SIZE_CLOTHING_WOMAN_CHILDREN = "size_clothing_woman_children"
            const val STATE_CLOTHING_WOMAN_CHILDREN = "state_clothing_woman_children"




        }

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

    fun getNonPerecible(
        nonPerecibleList: MutableList<NonPerishable>,
        adapter: AdapterNonPerishable
    ) {
        viewModelScope.launch {

            repositoryStock.getNonPerecible(
                nonPerecibleList, adapter
            )
        }

    }

    fun getBasicBasket(
        basicBasketList: MutableList<BasicBasket>,
        adapter: AdapterBasicBasket
    ) {

        viewModelScope.launch {

            repositoryStock.getBasicBasket(
                basicBasketList,
                adapter
            )

        }


    }

    fun getMensClhoting(
        mensClothingList: MutableList<MensClothing>,
        adapter: AdapterMansClothing
    ) {

        viewModelScope.launch {

         repositoryStock.getMensClhoting(
             mensClothingList,
             adapter
         )

        }
    }

    @SuppressLint("NotifyDatasetChanged")
    fun getMensClhotingChildren(
        mensClothingChildrenList: MutableList<MensChildrenClothing>,
        adapter: AdapterMensChildrenClothing
    ) {

        viewModelScope.launch {

            repositoryStock.getMensClhotingChildren(mensClothingChildrenList,adapter)


        }


    }
    fun getWomansClothing(
        womansClothingList: MutableList<WomanClothing>,
        adapter: AdapterWomansClothing
    ) {

        viewModelScope.launch {


            repositoryStock.getWomansClothing(womansClothingList, adapter)

        }


    }

        @SuppressLint("NotifyDatasetChanged")
        fun getWomansClhotingChildren(
            womanClothingChildrenList: MutableList<WomanChildrenClothing>,
            adapter: AdapterWomansChildrenClothing
        ) {

            viewModelScope.launch {


                repositoryStock.getWomansClhotingChildren(womanClothingChildrenList, adapter)

            }

        }


    fun getToys(
        toysList: MutableList<Toys>,
        adapter: AdapterToys
    ) {

        viewModelScope.launch {

            repositoryStock.getToys(
                toysList,
                adapter
            )

        }


    }







        fun formatBirth(data: String?): String {
        return data?.replace("(\\d{4})(\\d{2})(\\d{2})".toRegex(), "$3/$2/$1") ?: ""
    }

}