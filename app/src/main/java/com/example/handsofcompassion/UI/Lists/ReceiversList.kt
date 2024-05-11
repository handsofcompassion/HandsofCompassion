package com.example.handsofcompassion.UI.Lists

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.handsofcompassion.Adapter.ReceiverAdapter
import com.example.handsofcompassion.Data.Receiver
import com.example.handsofcompassion.Objects.ItemSpacingDecoration
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Principalattendant
import com.example.handsofcompassion.ViewModel.ViewModelReceiver
import com.example.handsofcompassion.databinding.ActivityReceiversListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReceiversList : AppCompatActivity() {

    private lateinit var binding: ActivityReceiversListBinding
    private lateinit var adapterReceiver: ReceiverAdapter
    private val receiverList: MutableList<Receiver> = mutableListOf()
    private val viewModel: ViewModelReceiver by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceiversListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsToolBar()

        val rvReceivers = binding.rvReceivers
        adapterReceiver = ReceiverAdapter(this, receiverList)
        rvReceivers.adapter = adapterReceiver
        rvReceivers.addItemDecoration(ItemSpacingDecoration(this, 0))

        viewModel.getReceiver(receiverList, adapterReceiver)

        binding.editName.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                viewModel.searchReceivers(newText!!, receiverList, adapterReceiver)
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
                viewModel.getReceiver(receiverList, adapterReceiver)
                return true
            }
        })

    }

    private fun settingsToolBar() {
        val toolbar = binding.toolbarEmployees
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setTitleTextColor(Color.WHITE)
        val titleText = resources.getString(R.string.beneficiarios).toUpperCase()
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
            startPrincipalActivity()
        }
    }

    private fun startPrincipalActivity() {
        val intent = Intent(this, Principalattendant::class.java)
        startActivity(intent)
        finish()
    }
}