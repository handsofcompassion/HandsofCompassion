package com.example.handsofcompassion.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.handsofcompassion.Data.MensChildrenClothing
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Lists.DetailList.MensClothingChildrenListDetail
import com.example.handsofcompassion.ViewModel.ViewModelStock
import com.example.handsofcompassion.databinding.ItemMensBinding
import com.example.handsofcompassion.databinding.ItemMensChildrenBinding
import com.google.firebase.firestore.FirebaseFirestore

class AdapterMensChildrenClothing (
    private val context: Context,
    private val mensClothingChildrenList: MutableList<MensChildrenClothing>
): RecyclerView.Adapter<AdapterMensChildrenClothing.MensClothingChildrenViewHolder>() {
    lateinit var dialog: AlertDialog
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MensClothingChildrenViewHolder {
        val itemList = ItemMensChildrenBinding.inflate(LayoutInflater.from(context), parent, false)
        return MensClothingChildrenViewHolder(itemList)
    }

    override fun getItemCount(): Int = mensClothingChildrenList.size
    override fun onBindViewHolder(holder: MensClothingChildrenViewHolder, position: Int) {

        holder.type.text = mensClothingChildrenList[position].typeClothing
        holder.size.text = mensClothingChildrenList[position].size
        holder.state.text = mensClothingChildrenList[position].state


        holder.edt.setOnClickListener {

            if (context is AppCompatActivity) {
                val intent = Intent(context, MensClothingChildrenListDetail::class.java)
                intent.putExtra("id", mensClothingChildrenList[position].id)
                intent.putExtra(ViewModelStock.TYPE_CLOTHING_MEN_CHILDREN, mensClothingChildrenList[position].typeClothing)
                intent.putExtra(ViewModelStock.SIZE_CLOTHING_MEN_CHILDREN, mensClothingChildrenList[position].size)
                intent.putExtra(ViewModelStock.STATE_CLOTHING_MEN_CHILDREN, mensClothingChildrenList[position].state)
                context.startActivity(intent)
                context.finish()
            }

        }
        holder.delete.setOnClickListener {

            val id = mensClothingChildrenList[position].id
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

    inner class MensClothingChildrenViewHolder(binding: ItemMensChildrenBinding) :
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
        alertDialog.setPositiveButton(context.getString(R.string.sim)) { dialog, which ->

            firestore.collection("MensClothingChildren").document(id).delete()
                .addOnCompleteListener {

                    if (it.isSuccessful) {

                        mensClothingChildrenList.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemChanged(position, mensClothingChildrenList)


                    }

                }.addOnFailureListener { }
        }
        alertDialog.setNegativeButton(R.string.nao) { dialog, which -> }
        dialog = alertDialog.create()
        dialog.show()
    }
}