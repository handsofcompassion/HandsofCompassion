package com.example.handsofcompassion.Adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.handsofcompassion.Data.Donor
import com.example.handsofcompassion.R
import com.example.handsofcompassion.databinding.ItemDonorsBinding
import com.google.firebase.firestore.FirebaseFirestore


class DonorAdapter(
    private val context: Context,
    private val donorList: MutableList<Donor>
) : RecyclerView.Adapter<DonorAdapter.DonorViewHolder>() {

    lateinit var dialog: AlertDialog
    private val firestore = FirebaseFirestore.getInstance()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonorViewHolder {
        val itemList = ItemDonorsBinding.inflate(LayoutInflater.from(context), parent, false)
        return DonorViewHolder(itemList)
    }

    override fun getItemCount() = donorList.size
    override fun onBindViewHolder(holder: DonorViewHolder, position: Int) {

        holder.txtName.text = donorList[position].name
        holder.txtCpf.text = donorList[position].cpf
        holder.txtPhone.text = donorList[position].phone
        holder.txtEmail.text = donorList[position].email
        holder.txtAdress.text = donorList[position].address
        holder.txtBirth.text = donorList[position].birth

        holder.delete.setOnClickListener {

            val id = donorList[position].id
            alertDialog(id.toString(), position)

        }
    }


    inner class DonorViewHolder(binding: ItemDonorsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val txtName = binding.tvName
        val txtCpf = binding.tvCpf
        val txtPhone = binding.tvTelefone
        val txtEmail = binding.tvEmail
        val txtAdress = binding.tvEndereco
        val txtBirth = binding.tvNascimento
        val edt = binding.imgEdit
        val delete = binding.imgDelete

    }

    private fun alertDialog(id: String, position: Int) {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle(R.string.excluirDoador)
        alertDialog.setMessage(R.string.desejaexcluirmsmm)
        alertDialog.setPositiveButton(context.getString(R.string.sim)) { dialog, whintch ->

            firestore.collection("Donors").document(id).delete()
                .addOnCompleteListener {

                    if (it.isSuccessful) {

                        donorList.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemChanged(position, donorList)


                    }

                }.addOnFailureListener { }
        }
        alertDialog.setNegativeButton(R.string.nao) { dialog, whintch -> }
        dialog = alertDialog.create()
        dialog.show()
    }
}