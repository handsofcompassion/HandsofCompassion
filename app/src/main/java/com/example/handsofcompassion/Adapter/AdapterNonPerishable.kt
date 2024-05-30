package com.example.handsofcompassion.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.handsofcompassion.Data.MensChildrenClothing
import com.example.handsofcompassion.Data.NonPerishable
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Lists.DetailList.DonorsListDetail
import com.example.handsofcompassion.UI.Lists.DetailList.MensClothingChildrenListDetail
import com.example.handsofcompassion.UI.Lists.DetailList.NonPerecibleListDetail
import com.example.handsofcompassion.UI.Lists.DetailList.WomansClothingListDetail
import com.example.handsofcompassion.ViewModel.ViewModelDonor
import com.example.handsofcompassion.ViewModel.ViewModelStock
import com.example.handsofcompassion.databinding.ItemMensBinding
import com.example.handsofcompassion.databinding.ItemMensChildrenBinding
import com.example.handsofcompassion.databinding.ItemNonPerecibleBinding
import com.example.handsofcompassion.databinding.ItemWomansBinding
import com.google.firebase.firestore.FirebaseFirestore

class AdapterNonPerishable  (
    private val context: Context,
    private val nonPerishableList: MutableList<NonPerishable>
) : RecyclerView.Adapter<AdapterNonPerishable.NonPerecibleViewHolder>() {

    lateinit var dialog: AlertDialog
    private val firestore = FirebaseFirestore.getInstance()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NonPerecibleViewHolder {
        val itemList = ItemNonPerecibleBinding.inflate(LayoutInflater.from(context), parent, false)
        return NonPerecibleViewHolder(itemList)
    }

    override fun getItemCount(): Int = nonPerishableList.size


    override fun onBindViewHolder(holder: NonPerecibleViewHolder, position: Int) {

        holder.type
        holder.validity
        holder.amount

        holder.delete.setOnClickListener {

            val id = nonPerishableList[position].id
            alertDialog(id.toString(), position)

        }

        holder.edt.setOnClickListener {

            if (context is AppCompatActivity) {
                val intent = Intent(context, DonorsListDetail::class.java)
                intent.putExtra("id", nonPerishableList[position].id)
                intent.putExtra(ViewModelStock.TYPE_FOOD, nonPerishableList[position].type)
                intent.putExtra(ViewModelStock.VALIDATY_FOOD, nonPerishableList[position].validity)
                intent.putExtra(ViewModelStock.AMOUNT_FOOD, nonPerishableList[position].amount)
                context.startActivity(intent)
                context.finish()
            }
        }

        holder.profile.setOnClickListener {



        }


    }



    inner class NonPerecibleViewHolder(binding: ItemNonPerecibleBinding):
            RecyclerView.ViewHolder(binding.root) {

                val type = binding.tvTipoDealiemnto
                val validity = binding.tvValidade
                val amount = binding.tvQuantidadealimentos
                val edt = binding.imgEdit
                val delete = binding.imgDelete
                val profile = binding.cardProfile


            }

    private fun alertDialog(id: String, position: Int) {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle(R.string.desejaExcluirRoupa)
        alertDialog.setMessage(R.string.temCerteza)
        alertDialog.setPositiveButton(context.getString(R.string.sim)) { dialog, which ->

            firestore.collection("NonPerishable").document(id).delete()
                .addOnCompleteListener {

                    if (it.isSuccessful) {

                        nonPerishableList.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemChanged(position, nonPerishableList)


                    }

                }.addOnFailureListener { }
        }
        alertDialog.setNegativeButton(R.string.nao) { dialog, which -> }
        dialog = alertDialog.create()
        dialog.show()
    }

}
