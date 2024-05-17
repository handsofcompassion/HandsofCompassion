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
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.handsofcompassion.Adapter.ReceiverAdapter
import com.example.handsofcompassion.Data.Receiver
import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Lists.ReceiversList
import com.example.handsofcompassion.ViewModel.ViewModelReceiver
import com.example.handsofcompassion.databinding.ActivityReceiversListDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ReceiversListDetail : AppCompatActivity() {

    private lateinit var binding: ActivityReceiversListDetailBinding
    private val viewModel: ViewModelReceiver by viewModels()
    private lateinit var adapter: ReceiverAdapter
    private val receiversList: MutableList<Receiver> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceiversListDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsToolBar()



        adapter = ReceiverAdapter(this, receiversList)

        val nameEdt = intent.getStringExtra(ViewModelReceiver.EXTRA_NAME)
        val cpfEdt = intent.getStringExtra(ViewModelReceiver.EXTRA_CPF)
        val phoneEdt = intent.getStringExtra(ViewModelReceiver.EXTRA_PHONE)
        val emailEdt = intent.getStringExtra(ViewModelReceiver.EXTRA_EMAIL)
        val adressEdt = intent.getStringExtra(ViewModelReceiver.EXTRA_ADRESS)
        val birthEdt = intent.getStringExtra(ViewModelReceiver.EXTRA_BIRTH)
        binding.editName.setText(nameEdt)
        binding.editCpf.setText(cpfEdt)
        binding.editTelefone.setText(phoneEdt)
        binding.editEmail.setText(emailEdt)
        binding.editEnderecoCompleto.setText(adressEdt)
        binding.editNascimento.setText(birthEdt)

        binding.btnAtualizar.setOnClickListener {

            val name = binding.editName.text.toString()
            val cpf = binding.editCpf.text.toString()
            val phone = binding.editTelefone.text.toString()
            val email = binding.editEmail.text.toString()
            val adress = binding.editEnderecoCompleto.text.toString()
            val birth = binding.editNascimento.text.toString()
            val id = intent.extras!!.getString("id")

            viewModel.updateReceivers(name, cpf, phone, email, adress, birth, id!!, adapter, object :
                AuthListneers {
                override fun onSucess(mensage: String) {
                    Toast.makeText(applicationContext, mensage, Toast.LENGTH_LONG).show()
                    binding.progress.visibility = View.VISIBLE
                    startReceiversListActivity()
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
        val titleText = resources.getString(R.string.atualizeSeuCadastro).toUpperCase()
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
        val intent = Intent(this, ReceiversList::class.java)
        startActivity(intent)
        finish()
    }
}