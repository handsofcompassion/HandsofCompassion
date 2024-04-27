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
import android.widget.SearchView.OnCloseListener
import android.widget.SearchView.OnQueryTextListener
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.handsofcompassion.Adapter.EmpplyeesAdapter
import com.example.handsofcompassion.Data.Employees
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Principalattendant
import com.example.handsofcompassion.ViewModel.ViewModelEmployees
import com.example.handsofcompassion.databinding.ActivityEmployeesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeesList : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeesBinding
    private lateinit var adapterEMployees: EmpplyeesAdapter
    private val employeesList: MutableList<Employees> = mutableListOf()
    private val viewModel: ViewModelEmployees by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsToolBar()

        val rvEmployees = binding.rvEmployees
        adapterEMployees = EmpplyeesAdapter(this, employeesList)
        rvEmployees.adapter = adapterEMployees

        viewModel.getEmployees(employeesList, adapterEMployees)


        binding.editName.setOnQueryTextListener(object : OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                viewModel.searchEmployees(newText!!, employeesList, adapterEMployees)
                return true
            }

        })

        binding.editName.setOnCloseListener(object : OnCloseListener,
            androidx.appcompat.widget.SearchView.OnCloseListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onClose(): Boolean {
                binding.editName.onActionViewCollapsed()
                employeesList.clear()
                adapterEMployees.notifyDataSetChanged()
                viewModel.getEmployees(employeesList, adapterEMployees)
                return true
            }
        })
    }

    private fun settingsToolBar() {
        val toolbar = binding.toolbarEmployees
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setTitleTextColor(Color.WHITE)
        val titleText = resources.getString(R.string.funcionarios).toUpperCase()
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