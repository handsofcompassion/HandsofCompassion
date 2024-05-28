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
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.DonationSuccess
import com.example.handsofcompassion.UI.DonationsType.ui.TypesOfFoodDonor
import com.example.handsofcompassion.UI.SelectionScreen
import com.example.handsofcompassion.ViewModel.ViewModelStock
import com.example.handsofcompassion.databinding.ActivityBasicBasketDonorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasicBasketDonor : AppCompatActivity() {

  private lateinit var binding: ActivityBasicBasketDonorBinding
  private val viewModel: ViewModelStock by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityBasicBasketDonorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsToolBar()

        binding.btnAlimentoNaoPerecivel.setOnClickListener {

            viewModel.selectedItemsCheckBox.clear()

            viewModel.addItemToList(binding.checkBoxArroz)
           viewModel.addItemToList(binding.checkBoxFeijao)
           viewModel.addItemToList(binding.checkBoxMacarrao)
           viewModel.addItemToList(binding.checkBoxFarinhaTrigo)
           viewModel.addItemToList(binding.checkBoxGraoBico)
           viewModel.addItemToList(binding.checkBoxAveia)
           viewModel.addItemToList(binding.checkBoxQuinoa)
           viewModel.addItemToList(binding.checkBoxCuscuz)
          viewModel.addItemToList(binding.checkBoxMassaTomate)
           viewModel.addItemToList(binding.checkBoxOleoVegetal)
           viewModel.addItemToList(binding.checkBoxAzeiteOliva)
           viewModel.addItemToList(binding.checkBoxVinagre)
           viewModel.addItemToList(binding.checkBoxSal)
           viewModel.addItemToList(binding.checkBoxAcucar)
           viewModel.addItemToList(binding.checkBoxCafe)
           viewModel.addItemToList(binding.checkBoxCha)
           viewModel.addItemToList(binding.checkBoxLeitePo)
           viewModel.addItemToList(binding.checkBoxLeiteCondensado)
           viewModel.addItemToList(binding.checkBoxSopaPo)
           viewModel.addItemToList(binding.checkBoxErvilhas)
           viewModel.addItemToList(binding.checkBoxMilho)
           viewModel.addItemToList(binding.checkBoxAtumEnlatado)
           viewModel.addItemToList(binding.checkBoxSardinhaEnlatada)
           viewModel.addItemToList(binding.checkBoxCarneEnlatada)
           viewModel.addItemToList(binding.checkBoxSopaEnlatada)
           viewModel.addItemToList(binding.checkBoxLeiteEvaporado)

            viewModel.saveBasicBasket(viewModel.selectedItemsCheckBox, object : AuthListneers {
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
        val toolbar = binding.toolbasicbasket
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
}