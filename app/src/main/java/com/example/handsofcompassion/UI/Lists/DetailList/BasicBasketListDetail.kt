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
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.handsofcompassion.Adapter.AdapterBasicBasket
import com.example.handsofcompassion.Data.BasicBasket
import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Lists.BasicBasketList
import com.example.handsofcompassion.ViewModel.ViewModelStock
import com.example.handsofcompassion.databinding.ActivityBasicBasketListDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BasicBasketListDetail : AppCompatActivity() {

    private lateinit var binding: ActivityBasicBasketListDetailBinding
    private lateinit var adapterBasicBasket: AdapterBasicBasket
    private val viewModel: ViewModelStock by viewModels()
    private val basicBasketList: MutableList<BasicBasket> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasicBasketListDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsToolBar()
        adapterBasicBasket = AdapterBasicBasket(this, basicBasketList)



        binding.btnAlimentoNaoPerecivel.setOnClickListener {

            val id = intent.getStringExtra("id")


                viewModel.updateBasicBasket(
                    id!!,
                    basicBasketList,
                    adapterBasicBasket,
                    object : AuthListneers {
                        override fun onSucess(mensage: String) {
                            Toast.makeText(applicationContext, mensage, Toast.LENGTH_LONG).show()
                            startBasicBasketActivity()
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
        val titleText = resources.getString(R.string.atualizeEstoque).toUpperCase()
        val title = SpannableString(titleText)

        title.setSpan(
            StyleSpan(Typeface.BOLD), 0, title.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        title.setSpan(
            ForegroundColorSpan(Color.WHITE), 0, title.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        title.setSpan(
            RelativeSizeSpan(1.5f), 0, title.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        toolbar.title = title

        toolbar.setNavigationOnClickListener {
            startBasicBasketActivity()
        }
    }

    private fun startBasicBasketActivity() {
        val intent = Intent(this, BasicBasketList::class.java)
        startActivity(intent)
        finish()
    }
}


