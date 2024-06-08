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
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.handsofcompassion.Adapter.AdapterWomansClothing
import com.example.handsofcompassion.Data.WomanClothing
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Clothes.WomensClothingChildrensReceivers
import com.example.handsofcompassion.UI.ReceiverSuccess
import com.example.handsofcompassion.ViewModel.ViewModelStock
import com.example.handsofcompassion.databinding.ActivityWomansProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WomansProfile : AppCompatActivity() {

    private lateinit var binding: ActivityWomansProfileBinding
    private lateinit var adapter: AdapterWomansClothing
    private val viewModel: ViewModelStock by viewModels()
    private val womanList: MutableList<WomanClothing> = mutableListOf()
    private  lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWomansProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsToolBar()

        adapter = AdapterWomansClothing(this, womanList)

        val typeEdt = intent.getStringExtra(ViewModelStock.TYPE_CLOTHING_WOMAN)
        val sizeEdt = intent.getStringExtra(ViewModelStock.SIZE_CLOTHING_WOMAN)
        val stateEdt = intent.getStringExtra(ViewModelStock.STATE_CLOTHING_WOMAN)

        binding.tvTipoDeRoupa.text = typeEdt
        binding.tvTamanho.text = sizeEdt
        binding.tvEstadoRoupa.text = stateEdt

        binding.btnReceber.setOnClickListener {

            alertDialog()
        }

    }
    private fun settingsToolBar() {
        val toolbar = binding.toolbarEmployees
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setTitleTextColor(Color.WHITE)
        val titleText = resources.getString(R.string.feminino).toUpperCase()
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
        alertDialog.setPositiveButton((R.string.sim)) { dialog, whintch ->

            startTypeDonationActivity()
            val id = intent.extras!!.getString("id")
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.womansCLothingList.collect{it

                    viewModel.deleteWomansClothingReceiver(id!!, it.size)
                }
            }

        }
        alertDialog.setNegativeButton(R.string.nao) { dialog, whintch -> }
        dialog = alertDialog.create()
        dialog.show()
    }

    private fun startTypeDonationActivity() {
        val intent = Intent(this, ReceiverSuccess::class.java)
        startActivity(intent)
        finish()
    }
    private fun startCreateNewDonorActivity() {
        val intent = Intent(this, WomensClothingChildrensReceivers::class.java)
        startActivity(intent)
        finish()
    }
}