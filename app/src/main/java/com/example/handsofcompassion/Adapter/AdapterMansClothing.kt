package com.example.handsofcompassion.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.handsofcompassion.Data.MensClothing
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Lists.DetailList.MensClothingListDetail
import com.example.handsofcompassion.ViewModel.ViewModelStock
import com.example.handsofcompassion.databinding.ItemMensBinding
import com.google.firebase.firestore.FirebaseFirestore

class AdapterMansClothing (
    private val context: Context,
    private val mensClothingList: MutableList<MensClothing>
): RecyclerView.Adapter<AdapterMansClothing.MansClothingViewHolder>() {

    lateinit var dialog: AlertDialog
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MansClothingViewHolder {
        val itemList = ItemMensBinding.inflate(LayoutInflater.from(context), parent, false)
        return MansClothingViewHolder(itemList)
    }

    override fun getItemCount(): Int = mensClothingList.size
    override fun onBindViewHolder(holder: MansClothingViewHolder, position: Int) {

        val mansClothing = mensClothingList[position]

     holder.type.text = mansClothing.typeClothing
     holder.size.text = mansClothing.sizeClothing
     holder.state.text = mansClothing.stateClothing


        holder.edt.setOnClickListener {

            if (context is AppCompatActivity) {
                val intent = Intent(context, MensClothingListDetail::class.java)
                intent.putExtra("id", mensClothingList[position].id)
                intent.putExtra(ViewModelStock.TYPE_CLOTHING_MEN, mensClothingList[position].typeClothing)
                intent.putExtra(ViewModelStock.SIZE_CLOTHING_MEN, mensClothingList[position].sizeClothing)
                intent.putExtra(ViewModelStock.STATE_CLOTHING_MEN, mensClothingList[position].stateClothing)
                context.startActivity(intent)
                context.finish()
            }

        }

        holder.delete.setOnClickListener {

            val id = mensClothingList[position].id
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

    inner class MansClothingViewHolder(binding: ItemMensBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val type = binding.tvTipoDeRoupa
        val size = binding.tvTamanho
        val state = binding.tvEstadoRoupa
        val edt = binding.imgEdit
        val delete = binding.imgDelete
        val profile = binding.cardProfile

    }
    private fun alertDialog(id: String, position: Int) {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle(R.string.desejaExcluirRoupa)
        alertDialog.setMessage(R.string.temCerteza)
        alertDialog.setPositiveButton(context.getString(R.string.sim)) { dialog, whintch ->

            firestore.collection("MensClothing").document(id).delete()
                .addOnCompleteListener {

                    if (it.isSuccessful) {

                        mensClothingList.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemChanged(position, mensClothingList)


                    }

                }.addOnFailureListener { }
        }
        alertDialog.setNegativeButton(R.string.nao) { dialog, whintch -> }
        dialog = alertDialog.create()
        dialog.show()
    }
}