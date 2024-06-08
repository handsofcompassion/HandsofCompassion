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
import com.example.handsofcompassion.Adapter.AdapterToys
import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.ReceiverSuccess
import com.example.handsofcompassion.UI.Toys.ToysReceiver
import com.example.handsofcompassion.ViewModel.ViewModelStock
import com.example.handsofcompassion.databinding.ActivityToysProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ToysProfile : AppCompatActivity() {

    private lateinit var binding: ActivityToysProfileBinding
    private lateinit var adapter: AdapterToys
    private val viewModel: ViewModelStock by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToysProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsToolBar()

        val typeEdt = intent.getStringExtra(ViewModelStock.TYPE_TOY_EXTRA)
        val stateEdt = intent.getStringExtra(ViewModelStock.STATE_TOY_EXTRA)
        val amountEdt = intent.getStringExtra(ViewModelStock.AMOUNT_TOY_EXTRA)

        binding.tvTipoDeBrinquedo.text = typeEdt
        binding.tvEstadoBrinquedo.text = stateEdt
        binding.tvQuantidadeBriquedos.text = amountEdt

        binding.btnReceber.setOnClickListener {

                alertDialog()

        }


    }
    private fun settingsToolBar() {
        val toolbar = binding.toolbarEmployees
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setTitleTextColor(Color.WHITE)
        val titleText = resources.getString(R.string.brinquedos).toUpperCase()
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
    private fun startCreateNewDonorActivity() {
        val intent = Intent(this, ToysReceiver::class.java)
        startActivity(intent)
        finish()
    }
    private fun alertDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(R.string.desejareceberdoacao)
        alertDialog.setPositiveButton(R.string.sim) { dialog, which ->


            lifecycleScope.launch(Dispatchers.IO){
                viewModel.toysList.collect{it

                    adapter = AdapterToys(applicationContext, it)

                    startTypeDonationActivity()
                    val id = intent.extras!!.getString("id")
                    val amount = binding.editQuantidade.text.toString()
                    viewModel.toysUpdateOrDelete(id!!, amount, adapter, it.size, it, object : AuthListneers{
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
}