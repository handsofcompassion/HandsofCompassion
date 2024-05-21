package com.example.handsofcompassion.UI.Profile

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
import com.example.handsofcompassion.Adapter.ReceiverAdapter
import com.example.handsofcompassion.Data.Receiver
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.SearchOrNewReceiver
import com.example.handsofcompassion.UI.TypeOfDonationReceiver
import com.example.handsofcompassion.ViewModel.ViewModelDonor
import com.example.handsofcompassion.ViewModel.ViewModelReceiver
import com.example.handsofcompassion.databinding.ActivityProfileReceiversBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileReceivers : AppCompatActivity() {

    private lateinit var binding: ActivityProfileReceiversBinding
    private val viewModel: ViewModelReceiver by viewModels()
    private lateinit var adapter: ReceiverAdapter
    private val receiversList: MutableList<Receiver> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileReceiversBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsToolBar()

        adapter = ReceiverAdapter(this, receiversList)

        val nameEdt = intent.getStringExtra(ViewModelDonor.EXTRA_NAME)
        val cpfEdt = intent.getStringExtra(ViewModelDonor.EXTRA_CPF)
        val phoneEdt = intent.getStringExtra(ViewModelDonor.EXTRA_PHONE)
        val emailEdt = intent.getStringExtra(ViewModelDonor.EXTRA_EMAIL)
        val adressEdt = intent.getStringExtra(ViewModelDonor.EXTRA_ADRESS)
        val birthEdt = intent.getStringExtra(ViewModelDonor.EXTRA_BIRTH)
        binding.tvName.text = nameEdt
        binding.tvCpf.text = viewModel.formatCpf(cpfEdt)
        binding.tvTelefone.text = viewModel.formatPhone(phoneEdt)
        binding.tvEmail.text = emailEdt
        binding.tvEndereco.text = adressEdt
        binding.tvNascimento.text = viewModel.formatBirth(birthEdt)

        binding.btnReceber.setOnClickListener {

            startTypeDonationActivity()


        }

    }
    private fun settingsToolBar() {
        val toolbar = binding.toolbarCreateNewDonor
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setTitleTextColor(Color.WHITE)
        val titleText = resources.getString(R.string.perfilReceberdor).toUpperCase()
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
        val intent = Intent(this, SearchOrNewReceiver::class.java)
        startActivity(intent)
        finish()
    }

    private fun startTypeDonationActivity() {
        val intent = Intent(this, TypeOfDonationReceiver::class.java)
        startActivity(intent)
        finish()
    }
}