package com.example.handsofcompassion.UI.Lists.DetailList

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.handsofcompassion.Adapter.AdapterWomansChildrenClothing
import com.example.handsofcompassion.Data.WomanChildrenClothing
import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Lists.WomansClothingChildrenList
import com.example.handsofcompassion.ViewModel.ViewModelStock
import com.example.handsofcompassion.databinding.ActivityWomansClothingChildrenListDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WomansClothingChildrenListDetail : AppCompatActivity() {

    private lateinit var binding: ActivityWomansClothingChildrenListDetailBinding
    private lateinit var adapterWomansClothing: AdapterWomansChildrenClothing
    private val womansChildrenClothingList: MutableList<WomanChildrenClothing> = mutableListOf()
    private val viewModel: ViewModelStock by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWomansClothingChildrenListDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsToolBar()

        adapterWomansClothing = AdapterWomansChildrenClothing(this, womansChildrenClothingList)

        val spinnerType = intent.getStringExtra(ViewModelStock.TYPE_CLOTHING_WOMAN_CHILDREN)
        val spinnerSize = intent.getStringExtra(ViewModelStock.SIZE_CLOTHING_WOMAN_CHILDREN)
        val spinnerState = intent.getStringExtra(ViewModelStock.STATE_CLOTHING_WOMAN_CHILDREN)
        configurationsSpinner1(binding.spinnerTipo, spinnerType)
        configurationsSpinner2(binding.spinnerTamanho, spinnerSize)
        configurationsSpinner3(binding.spinnerEstado, spinnerState)

        binding.btnAlimentoNaoPerecivel.setOnClickListener {

            val id = intent.extras!!.getString("id")
            val type = binding.spinnerTipo.selectedItem.toString()
            val size = binding.spinnerTamanho.selectedItem.toString()
            val state = binding.spinnerEstado.selectedItem.toString()

            viewModel.updateWomansChildrenClothing(id!!,type,size,state, adapterWomansClothing,object :
                AuthListneers {
                override fun onSucess(mensage: String) {
                    Toast.makeText(applicationContext, mensage, Toast.LENGTH_LONG).show()
                    startReceiversListActivity()
                }

                override fun onFailure(error: String) {
                    Toast.makeText(applicationContext, error, Toast.LENGTH_LONG).show()
                }
            })

        }

    }

    private fun settingsToolBar() {
        val toolbar = binding.toolnonPerecible
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setTitleTextColor(Color.WHITE)
        val titleText = resources.getString(R.string.atualizeEstoque).toUpperCase()
        val title = SpannableString(titleText)

        title.setSpan(
            StyleSpan(Typeface.BOLD), 0, title.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        title.setSpan(
            ForegroundColorSpan(Color.WHITE), 0, title.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        title.setSpan(
            RelativeSizeSpan(1.5f),
            0, title.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        toolbar.title = title

        toolbar.setNavigationOnClickListener {
            startReceiversListActivity()

        }
    }

    private fun startReceiversListActivity() {
        val intent = Intent(this, WomansClothingChildrenList::class.java)
        startActivity(intent)
        finish()
    }

    private fun configurationsSpinner1(spinner: Spinner, selectValue: String?) {

        val itemSpinnerType = listOf(
            "Camisetas/T-shirts",
            "Calças Jeans",
            "Agasalhos/Blusas de Frio",
            "Roupas Íntimas",
            "Roupas Esportivas",
            "Roupas de Dormir",
            "Roupas Formais"
        )

        // Referência ao Spinner no layout
        val spinner = binding.spinnerTipo

        // Adapter para o Spinner
        val spinnerAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, itemSpinnerType)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        // Listener para selecionar um item do Spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                // Aqui você pode adicionar código para lidar com o item selecionado, se necessário
                Toast.makeText(
                    applicationContext,
                    "Item selecionado: $selectedItem",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Aqui você pode adicionar código para lidar com a ausência de seleção, se necessário
                // Neste exemplo, um aviso é exibido
                Toast.makeText(applicationContext, "Nenhum item selecionado", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun configurationsSpinner2(spinner: Spinner, selectValue: String?) {


        val itemSpinnerSizes = listOf("P", "M", "G", "GG", "GX", "XL")

        // Referência ao Spinner no layout
        val spinnerSize = binding.spinnerTamanho

        // Adapter para o Spinner
        val spinnerAdapter2 =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, itemSpinnerSizes)
        spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSize.adapter = spinnerAdapter2

        // Listener para selecionar um item do Spinner
        spinnerSize.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                // Aqui você pode adicionar código para lidar com o item selecionado, se necessário
                Toast.makeText(
                    applicationContext,
                    "Item selecionado: $selectedItem",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Aqui você pode adicionar código para lidar com a ausência de seleção, se necessário
                // Neste exemplo, um aviso é exibido
                Toast.makeText(applicationContext, "Nenhum item selecionado", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }

    private fun configurationsSpinner3(spinner: Spinner, selectValue: String?) {

        val itemSpinnerConditions = listOf("Novo", "Semi-novo", "Usado", "Desgastado")


        // Referência ao Spinner no layout
        val spinnerConditions = binding.spinnerEstado

        // Adapter para o Spinner
        val spinnerAdapter3 =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, itemSpinnerConditions)
        spinnerAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerConditions.adapter = spinnerAdapter3

        // Listener para selecionar um item do Spinner
        spinnerConditions.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                // Aqui você pode adicionar código para lidar com o item selecionado, se necessário
                Toast.makeText(
                    applicationContext,
                    "Item selecionado: $selectedItem",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Aqui você pode adicionar código para lidar com a ausência de seleção, se necessário
                // Neste exemplo, um aviso é exibido
                Toast.makeText(applicationContext, "Nenhum item selecionado", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}