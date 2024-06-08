package com.example.handsofcompassion.UI.ReceiveDonation

import android.app.AlertDialog
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
import androidx.lifecycle.lifecycleScope
import com.example.handsofcompassion.Adapter.AdapterNonPerishable
import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Foods.NonPerishableFoodsReceiver
import com.example.handsofcompassion.UI.ReceiverSuccess
import com.example.handsofcompassion.ViewModel.ViewModelStock
import com.example.handsofcompassion.databinding.ActivityNonPereciblesProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NonPereciblesProfile : AppCompatActivity() {

    private lateinit var binding: ActivityNonPereciblesProfileBinding
    private val viewModel: ViewModelStock by viewModels()
    private lateinit var adapter: AdapterNonPerishable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNonPereciblesProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsToolBar()

        val typeEdt = intent.getStringExtra(ViewModelStock.TYPE_FOOD)
        val sizeEdt = intent.getStringExtra(ViewModelStock.VALIDATY_FOOD)
        val stateEdt = intent.getStringExtra(ViewModelStock.AMOUNT_FOOD)

        binding.tvTipoDealiemnto.text = typeEdt
        binding.tvValidade.text = sizeEdt
        binding.tvQuantidadealimentos.text = stateEdt

        binding.btnReceber.setOnClickListener {

            alertDialog()

        }

    }

    private fun settingsToolBar() {
        val toolbar = binding.toolbarEmployees
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setTitleTextColor(Color.WHITE)
        val titleText = resources.getString(R.string.naopereciveis).toUpperCase()
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
            startCreateNewDonorActivity()

        }
    }

    private fun alertDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(R.string.desejareceberdoacao)
        alertDialog.setPositiveButton(R.string.sim) { dialog, which ->


            startTypeDonationActivity()
            lifecycleScope.launch(Dispatchers.IO){
                viewModel.nonPerishableList.collect{it

                    adapter =  AdapterNonPerishable(applicationContext, it)

                    val id = intent.extras!!.getString("id")
                    val amount = binding.editNascimento.text.toString()
                    viewModel.nonPereciblesUpdateOrDelete(id!!, amount, adapter, it.size, it, object : AuthListneers{
                        override fun onSucess(mensage: String) {
                            Toast.makeText(applicationContext, mensage, Toast.LENGTH_LONG).show()
                        }

                        override fun onFailure(error: String) {
                            Toast.makeText(applicationContext, error, Toast.LENGTH_LONG).show()
                        }
                    })

                }
            }



        }
        alertDialog.setNegativeButton(R.string.nao) { dialog, which -> }
        val dialog = alertDialog.create()
        dialog.show()
    }


    private fun startTypeDonationActivity() {
        val intent = Intent(this, ReceiverSuccess::class.java)
        startActivity(intent)
        finish()
    }

    private fun startCreateNewDonorActivity() {
        val intent = Intent(this, NonPerishableFoodsReceiver::class.java)
        startActivity(intent)
        finish()
    }

}