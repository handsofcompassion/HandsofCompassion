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
import com.example.handsofcompassion.Adapter.AdapterNonPerishable
import com.example.handsofcompassion.Data.NonPerishable
import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Lists.NonPerishableList
import com.example.handsofcompassion.ViewModel.ViewModelStock
import com.example.handsofcompassion.databinding.ActivityNonPerecibleListDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NonPerecibleListDetail : AppCompatActivity() {

    private lateinit var binding: ActivityNonPerecibleListDetailBinding
    private lateinit var adapterNonPerecible: AdapterNonPerishable
    private val nonPerecibleList: MutableList<NonPerishable> = mutableListOf()
    private val viewModel: ViewModelStock by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNonPerecibleListDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsToolBar()



        adapterNonPerecible = AdapterNonPerishable(this, nonPerecibleList)

        val spinerFood = intent.getStringExtra(ViewModelStock.TYPE_FOOD)
        val editValidity = intent.getStringExtra(ViewModelStock.VALIDATY_FOOD)
        val spinnerAmount = intent.getStringExtra(ViewModelStock.AMOUNT_FOOD)
        spinnerConfiguration2(binding.spinner, spinerFood)
        binding.editValidity.setText(editValidity)
        spinnersConfiguration(binding.spinnerQuantidade, spinnerAmount)

    binding.btnAlimentoNaoPerecivelAtualizar.setOnClickListener {

        val food = binding.spinner.selectedItem.toString()
        val validity = binding.editValidity.text.toString()
        val amount = binding.spinnerQuantidade.selectedItem.toString()
        val id = intent.extras!!.getString("id")


        viewModel.updateNonPerecibles(id!!,food, validity, amount, adapterNonPerecible, object : AuthListneers{
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
        val intent = Intent(this, NonPerishableList::class.java)
        startActivity(intent)
        finish()
    }
    private fun spinnersConfiguration(spinner: Spinner, selectValue: String?) {

        val itensSpinner2 = (1..30).map { it.toString() }

        val spinner2 = binding.spinnerQuantidade

        // Adapter para o Spinner
        val spinnerAdapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, itensSpinner2)
        spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = spinnerAdapter2
        // Listener para selecionar um item do Spinner
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                // Aqui você pode adicionar código para lidar com o item selecionado, se necessário
                Toast.makeText(applicationContext, "Item selecionado: $selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Aqui você pode adicionar código para lidar com a ausência de seleção, se necessário
                // Neste exemplo, um aviso é exibido
                Toast.makeText(applicationContext, "Nenhum item selecionado", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun spinnerConfiguration2(spinner: Spinner, selectValue: String?) {

        val itensSpinner = listOf(
            "Arroz",
            "Feijão",
            "Macarrão",
            "Farinha de trigo",
            "Farinha de milho",
            "Lentilhas",
            "Grão-de-bico",
            "Aveia",
            "Quinoa",
            "Cereais em flocos",
            "Cuscuz",
            "Massa de tomate",
            "Óleo vegetal",
            "Azeite de oliva",
            "Vinagre",
            "Sal",
            "Açúcar",
            "Café",
            "Chá",
            "Leite em pó",
            "Leite condensado",
            "Sopa em pó",
            "Ervilhas",
            "Milho",
            "Enlatados de frutas",
            "Atum enlatado",
            "Sardinha enlatada",
            "Carne enlatada (como carne de porco desfiada)",
            "Sopa enlatada",
            "Leite evaporado"
        )


        // Referência ao Spinner no layout
        val spinner = binding.spinner

        // Adapter para o Spinner
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, itensSpinner)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        // Listener para selecionar um item do Spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                // Aqui você pode adicionar código para lidar com o item selecionado, se necessário
                Toast.makeText(applicationContext, "Item selecionado: $selectedItem", Toast.LENGTH_SHORT).show()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Aqui você pode adicionar código para lidar com a ausência de seleção, se necessário
                // Neste exemplo, um aviso é exibido
                Toast.makeText(applicationContext, "Nenhum item selecionado", Toast.LENGTH_SHORT).show()
            }
        }

    }
}