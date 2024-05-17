package com.example.handsofcompassion.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.handsofcompassion.Data.Receiver
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Lists.DetailList.EmployeesListDetail
import com.example.handsofcompassion.UI.Lists.DetailList.ReceiversListDetail
import com.example.handsofcompassion.ViewModel.ViewModelEmployees
import com.example.handsofcompassion.ViewModel.ViewModelReceiver
import com.example.handsofcompassion.databinding.ItemReceiversBinding
import com.google.firebase.firestore.FirebaseFirestore

class ReceiverAdapter (private val context: Context,
private val receiverList: MutableList<Receiver>
) : RecyclerView.Adapter<ReceiverAdapter.ReceiverViewHolder>() {

    lateinit var dialog: AlertDialog
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiverViewHolder {
        val itemList = ItemReceiversBinding.inflate(LayoutInflater.from(context), parent, false)
        return ReceiverViewHolder(itemList)
    }

    override fun getItemCount() = receiverList.size

    override fun onBindViewHolder(holder: ReceiverViewHolder, position: Int) {

        holder.txtName.text = receiverList[position].name
        holder.txtCpf.text = receiverList[position].cpf
        holder.txtPhone.text = receiverList[position].phone
        holder.txtEmail.text = receiverList[position].email
        holder.txtAdress.text = receiverList[position].address
        holder.txtBirth.text = receiverList[position].birth

        holder.delete.setOnClickListener {

            val id = receiverList[position].id
            alertDialog(id.toString(), position)

        }

        holder.edt.setOnClickListener {

            if (context is AppCompatActivity) {
                val intent = Intent(context, ReceiversListDetail::class.java)
                intent.putExtra("id", receiverList[position].id)
                intent.putExtra(ViewModelReceiver.EXTRA_NAME, receiverList[position].name)
                intent.putExtra(ViewModelReceiver.EXTRA_CPF, receiverList[position].cpf)
                intent.putExtra(ViewModelReceiver.EXTRA_PHONE, receiverList[position].phone)
                intent.putExtra(ViewModelReceiver.EXTRA_EMAIL, receiverList[position].email)
                intent.putExtra(ViewModelReceiver.EXTRA_ADRESS, receiverList[position].address)
                intent.putExtra(ViewModelReceiver.EXTRA_BIRTH, receiverList[position].birth)
                context.startActivity(intent)
                context.finish()
            }

        }

    }


    inner class ReceiverViewHolder(binding: ItemReceiversBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val txtName = binding.tvName
        val txtCpf = binding.tvCpf
        val txtPhone = binding.tvTelefone
        val txtEmail = binding.tvEmail
        val txtAdress = binding.tvEndereco
        val txtBirth = binding.tvNascimento
        val edt = binding.imgEdit
        val delete = binding.imgDelete
        val profile = binding.cardProfile

    }

    private fun alertDialog(id: String, position: Int) {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle(R.string.excluirDonatario)
        alertDialog.setMessage(R.string.desejaexcluirmsmmm)
        alertDialog.setPositiveButton(context.getString(R.string.sim)) { dialog, whintch ->

            firestore.collection("Receivers").document(id).delete()
                .addOnCompleteListener {

                    if (it.isSuccessful) {

                        receiverList.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemChanged(position, receiverList)

                    }

                }.addOnFailureListener { }
        }
        alertDialog.setNegativeButton(R.string.nao) { dialog, whintch -> }
        dialog = alertDialog.create()
        dialog.show()
    }


}