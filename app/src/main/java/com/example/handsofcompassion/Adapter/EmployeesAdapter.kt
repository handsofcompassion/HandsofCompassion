package com.example.handsofcompassion.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Lists.DetailList.EmployeesListDetail
import com.example.handsofcompassion.ViewModel.ViewModelEmployees
import com.example.handsofcompassion.databinding.ItemEmployeesBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class EmpplyeesAdapter(
    private val context: Context,
    private val employeesList: MutableList<com.example.handsofcompassion.Data.Employees>
) : RecyclerView.Adapter<EmpplyeesAdapter.EmployeesViewHolder>() {

    lateinit var dialog: AlertDialog
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private var user = auth.currentUser

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeesViewHolder {
        val itemList = ItemEmployeesBinding.inflate(LayoutInflater.from(context), parent, false)
        return EmployeesViewHolder(itemList)
    }

    override fun getItemCount() = employeesList.size

    override fun onBindViewHolder(holder: EmployeesViewHolder, position: Int) {

        holder.txtName.text = employeesList[position].name
        holder.txtEmail.text = employeesList[position].email

        holder.delete.setOnClickListener {

            val id = employeesList[position].id
            alertDialog(id.toString(), position)

        }

        holder.edt.setOnClickListener {

            if (context is AppCompatActivity) {
                val intent = Intent(context, EmployeesListDetail::class.java)
                intent.putExtra("id", employeesList[position].id)
                intent.putExtra(ViewModelEmployees.EXTRA_NAME, employeesList[position].name)
                intent.putExtra(ViewModelEmployees.EXTRA_EMAIL, employeesList[position].email)
                context.startActivity(intent)
                context.finish()
            }
        }
    }

    inner class EmployeesViewHolder(binding: ItemEmployeesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val txtName = binding.tvName
        val txtEmail = binding.tvEmail
        val edt = binding.imgEdit
        val delete = binding.imgDelete

    }

    private fun alertDialog(id: String, position: Int) {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle(R.string.excluirfuncionario)
        alertDialog.setMessage(R.string.desejaexcluirmsm)
        alertDialog.setPositiveButton(context.getString(R.string.sim)) { dialog, whintch ->

            firestore.collection("Users").document(id).delete()
                .addOnCompleteListener {

                    if (it.isSuccessful) {

                        employeesList.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemChanged(position, employeesList)

                        user!!.delete()

                    }

                }.addOnFailureListener { }


        }
        alertDialog.setNegativeButton(R.string.nao) { dialog, whintch -> }
        dialog = alertDialog.create()
        dialog.show()
    }
}