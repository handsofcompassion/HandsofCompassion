package com.example.handsofcompassion.UI.Foods

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
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.DonationSuccess
import com.example.handsofcompassion.UI.DonationsType.ui.TypesOfFoodDonor
import com.example.handsofcompassion.ViewModel.ViewModelStock
import com.example.handsofcompassion.databinding.ActivityNonPerishableFoodsDonorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NonPerishableFoodsDonor : AppCompatActivity() {

    private lateinit var binding: ActivityNonPerishableFoodsDonorBinding
    private val viewModel: ViewModelStock by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNonPerishableFoodsDonorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsToolBar()
        spinnersConfiguration()


        binding.btnAlimentoNaoPerecivel.setOnClickListener {
            // Capturar os valores selecionados nos spinners
            val selectedFood = binding.spinner.selectedItem.toString()
            val selectedQuantity = binding.spinnerQuantidade.selectedItem.toString()
            val validity = binding.editNascimento.text.toString()

            // Chamar a função saveNonPerishable com os valores selecionados
            viewModel.saveNonPerishable(selectedFood, validity, selectedQuantity, object : AuthListneers{
                override fun onSucess(mensage: String) {

                    Toast.makeText(applicationContext, mensage, Toast.LENGTH_LONG).show()
                    startDonationIsSucsstivity()

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
        val titleText = resources.getString(R.string.doe_btn).toUpperCase()
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
            startSearchOrNewDonationActivity()
        }
    }
    private fun startSearchOrNewDonationActivity() {
        val intent = Intent(this, TypesOfFoodDonor::class.java)
        startActivity(intent)
        finish()
    }

    private fun startDonationIsSucsstivity() {
        val intent = Intent(this, DonationSuccess::class.java)
        startActivity(intent)
    }
    private fun spinnersConfiguration() {
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
                binding.btnAlimentoNaoPerecivel.setOnClickListener {

                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Aqui você pode adicionar código para lidar com a ausência de seleção, se necessário
                // Neste exemplo, um aviso é exibido
                Toast.makeText(applicationContext, "Nenhum item selecionado", Toast.LENGTH_SHORT).show()
            }
        }



        val itensSpinner2 = (1..30).map { it.toString() }

        val spinner2 = binding.spinnerQuantidade

        // Adapter para o Spinner
        val spinnerAdapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, itensSpinner2)
        spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = spinnerAdapter2
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