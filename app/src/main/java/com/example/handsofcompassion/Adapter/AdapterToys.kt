package com.example.handsofcompassion.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.handsofcompassion.Data.Donor
import com.example.handsofcompassion.Data.Toys
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Lists.DetailList.DonorsListDetail
import com.example.handsofcompassion.UI.Lists.DetailList.ToysListDetail
import com.example.handsofcompassion.ViewModel.ViewModelDonor
import com.example.handsofcompassion.ViewModel.ViewModelStock
import com.example.handsofcompassion.databinding.ItemDonorsBinding
import com.example.handsofcompassion.databinding.ItemToysBinding
import com.google.firebase.firestore.FirebaseFirestore

class AdapterToys(
    private val context: Context,
    private val toysList: MutableList<Toys>
): RecyclerView.Adapter<AdapterToys.ToysViewHolder>() {


    lateinit var dialog: AlertDialog
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToysViewHolder {
        val itemList = ItemToysBinding.inflate(LayoutInflater.from(context), parent, false)
        return ToysViewHolder(itemList)
    }

    override fun getItemCount(): Int = toysList.size
    override fun onBindViewHolder(holder: ToysViewHolder, position: Int) {

        holder.type.text = toysList[position].type
        holder.state.text = toysList[position].state
        holder.amout.text = toysList[position].amount

        holder.edt.setOnClickListener {

            if (context is AppCompatActivity) {
                val intent = Intent(context, ToysListDetail::class.java)
                intent.putExtra("id", toysList[position].id)
                intent.putExtra(ViewModelStock.TYPE_TOY_EXTRA, toysList[position].type)
                intent.putExtra(ViewModelStock.STATE_TOY_EXTRA, toysList[position].state)
                intent.putExtra(ViewModelStock.AMOUNT_TOY_EXTRA, toysList[position].amount)
                context.startActivity(intent)
                context.finish()
            }

        }
        holder.delete.setOnClickListener {

            val id = toysList[position].id
            alertDialog(id.toString(), position)

        }

        holder.profile.setOnClickListener {

            //QUANDO FIZER SELECIONAR O BRINQUEDO COLOCAR AQUI A TELA CRIADA...

//            if (context is AppCompatActivity) {
//                val intent = Intent(context, ToysListDetail::class.java)
//                intent.putExtra("id", toysList[position].id)
//                intent.putExtra(ViewModelStock.TYPE_TOY_EXTRA, toysList[position].type)
//                intent.putExtra(ViewModelStock.STATE_TOY_EXTRA, toysList[position].state)
//                intent.putExtra(ViewModelStock.AMOUNT_TOY_EXTRA, toysList[position].amount)
//                context.startActivity(intent)
//                context.finish()
//            }


        }
    }

    inner class ToysViewHolder(binding: ItemToysBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val type = binding.tvTipoDeBrinquedo
        val state = binding.tvEstadoBrinquedo
        val amout = binding.tvQuantidadeBriquedos
        val edt = binding.imgEdit
        val delete = binding.imgDelete
        val profile = binding.cardProfile

    }
    private fun alertDialog(id: String, position: Int) {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle(R.string.desejaExpluirBrinquedo)
        alertDialog.setMessage(R.string.temCerteza)
        alertDialog.setPositiveButton(context.getString(R.string.sim)) { dialog, whintch ->

            firestore.collection("Toys").document(id).delete()
                .addOnCompleteListener {

                    if (it.isSuccessful) {

                        toysList.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemChanged(position, toysList)


                    }

                }.addOnFailureListener { }
        }
        alertDialog.setNegativeButton(R.string.nao) { dialog, whintch -> }
        dialog = alertDialog.create()
        dialog.show()
    }
}