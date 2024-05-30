package com.example.handsofcompassion.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.handsofcompassion.Data.Donor
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Lists.DetailList.DonorsListDetail
import com.example.handsofcompassion.UI.Lists.DetailList.EmployeesListDetail
import com.example.handsofcompassion.UI.Profile.ProfileDonors
import com.example.handsofcompassion.ViewModel.ViewModelDonor
import com.example.handsofcompassion.ViewModel.ViewModelReceiver
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

        holder.edt.setOnClickListener {

            if (context is AppCompatActivity) {
                val intent = Intent(context, DonorsListDetail::class.java)
                intent.putExtra("id", donorList[position].id)
                intent.putExtra(ViewModelDonor.EXTRA_NAME, donorList[position].name)
                intent.putExtra(ViewModelDonor.EXTRA_CPF, donorList[position].cpf)
                intent.putExtra(ViewModelDonor.EXTRA_PHONE, donorList[position].phone)
                intent.putExtra(ViewModelDonor.EXTRA_EMAIL, donorList[position].email)
                intent.putExtra(ViewModelDonor.EXTRA_ADRESS, donorList[position].address)
                intent.putExtra(ViewModelDonor.EXTRA_BIRTH, donorList[position].birth)
                context.startActivity(intent)
                context.finish()
            }
        }

        holder.profile.setOnClickListener {


            if (context is AppCompatActivity) {
                val intent = Intent(context, ProfileDonors::class.java)
                intent.putExtra("id", donorList[position].id)
                intent.putExtra(ViewModelDonor.EXTRA_NAME, donorList[position].name)
                intent.putExtra(ViewModelDonor.EXTRA_CPF, donorList[position].cpf)
                intent.putExtra(ViewModelDonor.EXTRA_PHONE, donorList[position].phone)
                intent.putExtra(ViewModelDonor.EXTRA_EMAIL, donorList[position].email)
                intent.putExtra(ViewModelDonor.EXTRA_ADRESS, donorList[position].address)
                intent.putExtra(ViewModelDonor.EXTRA_BIRTH, donorList[position].birth)
                context.startActivity(intent)
                context.finish()
            }
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
        val profile = binding.cardProfile

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