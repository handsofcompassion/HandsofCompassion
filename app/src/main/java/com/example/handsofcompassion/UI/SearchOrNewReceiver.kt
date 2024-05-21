package com.example.handsofcompassion.UI

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.handsofcompassion.Adapter.ReceiverAdapter
import com.example.handsofcompassion.Data.Receiver
import com.example.handsofcompassion.Objects.ItemSpacingDecoration
import com.example.handsofcompassion.R
import com.example.handsofcompassion.ViewModel.ViewModelReceiver
import com.example.handsofcompassion.databinding.ActivitySearchOrNewReceiverBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchOrNewReceiver : AppCompatActivity() {
    private lateinit var binding: ActivitySearchOrNewReceiverBinding
    private lateinit var adapterReceiver: ReceiverAdapter
    private val receiverList: MutableList<Receiver> = mutableListOf()
    private val viewModel: ViewModelReceiver by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivitySearchOrNewReceiverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rvReceivers = binding.rvReceivers
        adapterReceiver = ReceiverAdapter(this, receiverList)
        rvReceivers.adapter = adapterReceiver
        rvReceivers.addItemDecoration(ItemSpacingDecoration(this, 0))


        binding.editName.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }
            override fun onQueryTextChange(newText: String?): Boolean {


                viewModel.searchReceiversCPF(newText!!, receiverList, adapterReceiver)
                return true
            }
        })

        binding.editName.setOnCloseListener(object : SearchView.OnCloseListener,
            androidx.appcompat.widget.SearchView.OnCloseListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onClose(): Boolean {
                binding.editName.onActionViewCollapsed()
                receiverList.clear()
                adapterReceiver.notifyDataSetChanged()
                return true
            }
        })


        binding.btnReceber.setOnClickListener {

            startCreateNewReceiverActivity()

        }

        }
    private fun startCreateNewReceiverActivity() {
        val intent = Intent(this, CreateNewReceiver::class.java)
        startActivity(intent)
        finish()
    }
    }


