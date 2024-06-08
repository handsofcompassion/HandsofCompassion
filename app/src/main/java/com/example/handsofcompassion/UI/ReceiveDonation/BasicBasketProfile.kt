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
import com.example.handsofcompassion.Adapter.AdapterBasicBasket
import com.example.handsofcompassion.Data.BasicBasket
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Foods.BasicBasketReceiver
import com.example.handsofcompassion.UI.ReceiverSuccess
import com.example.handsofcompassion.ViewModel.ViewModelStock
import com.example.handsofcompassion.databinding.ActivityBasicBasketProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BasicBasketProfile : AppCompatActivity() {

    private lateinit var binding: ActivityBasicBasketProfileBinding
    private lateinit var adapter: AdapterBasicBasket
    private val viewModel: ViewModelStock by viewModels()
    private val basicBasketList: MutableList<BasicBasket> = mutableListOf()
    private  lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasicBasketProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsToolBar()

        adapter = AdapterBasicBasket(this, basicBasketList)

        // Obtendo dados da Intent
        val item1Edt = intent.getStringExtra(ViewModelStock.ITEM_1_EXTRA)
        val item2Edt = intent.getStringExtra(ViewModelStock.ITEM_2_EXTRA)
        val item3Edt = intent.getStringExtra(ViewModelStock.ITEM_3_EXTRA)
        val item4Edt = intent.getStringExtra(ViewModelStock.ITEM_4_EXTRA)
        val item5Edt = intent.getStringExtra(ViewModelStock.ITEM_5_EXTRA)
        val item6Edt = intent.getStringExtra(ViewModelStock.ITEM_6_EXTRA)
        val item7Edt = intent.getStringExtra(ViewModelStock.ITEM_7_EXTRA)
        val item8Edt = intent.getStringExtra(ViewModelStock.ITEM_8_EXTRA)
        val item9Edt = intent.getStringExtra(ViewModelStock.ITEM_9_EXTRA)
        val item10Edt = intent.getStringExtra(ViewModelStock.ITEM_10_EXTRA)
        val item11Edt = intent.getStringExtra(ViewModelStock.ITEM_11_EXTRA)
        val item12Edt = intent.getStringExtra(ViewModelStock.ITEM_12_EXTRA)


        binding.item1Response.text = item1Edt
        binding.item2Response.text = item2Edt
        binding.item3Response.text = item3Edt
        binding.item4Response.text = item4Edt
        binding.item5Response.text = item5Edt
        binding.item6Response.text = item6Edt
        binding.item7Response.text = item7Edt
        binding.item8Response.text = item8Edt
        binding.item9Response.text = item9Edt
        binding.item10Response.text = item10Edt
        binding.item11Response.text = item11Edt
        binding.item12Response.text = item12Edt

        binding.btnReceber.setOnClickListener {

            alertDialog()

        }
    }
    private fun settingsToolBar() {
        val toolbar = binding.toolbarEmployees
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setTitleTextColor(Color.WHITE)
        val titleText = resources.getString(R.string.cestaBasica).toUpperCase()
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
                viewModel.basicBasketList.collect{it

                    viewModel.deleteBasicBasketReceiver(id!!, it.size)
                }
            }
        }
        alertDialog.setNegativeButton(R.string.nao) { dialog, whintch -> }
        dialog = alertDialog.create()
        dialog.show()
    }
    private fun startCreateNewDonorActivity() {
        val intent = Intent(this, BasicBasketReceiver::class.java)
        startActivity(intent)
        finish()
    }
    private fun startTypeDonationActivity() {
        val intent = Intent(this, ReceiverSuccess::class.java)
        startActivity(intent)
        finish()
    }

}