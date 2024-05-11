package com.example.handsofcompassion.UI

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
import com.example.handsofcompassion.ViewModel.ViewModelDonor
import com.example.handsofcompassion.databinding.ActivityCreateNewDonorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateNewDonor : AppCompatActivity() {

    private lateinit var binding: ActivityCreateNewDonorBinding
    private val viewModel: ViewModelDonor by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNewDonorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsToolBar()

        binding.btnCadastrar.setOnClickListener {

            val name = binding.editName.text.toString()
            val cpf = binding.editCpf.text.toString()
            val phone = binding.editTelefone.text.toString()
            val email = binding.editEmail.text.toString()
            val address = binding.editEnderecoCompleto.text.toString()
            val birth = binding.editNascimento.text.toString()

            viewModel.createDonor(name, cpf, phone, email, address, birth, object : AuthListneers {
                override fun onSucess(mensage: String) {

                    Toast.makeText(applicationContext, mensage, Toast.LENGTH_LONG).show()
                    startTypeDonationActivity()
                }

                override fun onFailure(error: String) {

                    Toast.makeText(applicationContext, error, Toast.LENGTH_LONG).show()
                }
            })
        }


    }
    private fun settingsToolBar() {
        val toolbar = binding.toolbarCreateNewDonor
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setTitleTextColor(Color.WHITE)
        val titleText = resources.getString(R.string.donationishere).toUpperCase()
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
        val intent = Intent(this, SearchOrNewDonation::class.java)
        startActivity(intent)
        finish()
    }

    private fun startTypeDonationActivity() {
        val intent = Intent(this, TypeOfDonationDonor::class.java)
        startActivity(intent)
        finish()
    }
}