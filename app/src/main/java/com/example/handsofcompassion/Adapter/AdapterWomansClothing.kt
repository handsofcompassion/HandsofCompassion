package com.example.handsofcompassion.Adapter


import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.handsofcompassion.Data.WomanClothing
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Lists.DetailList.WomansClothingListDetail
import com.example.handsofcompassion.UI.ReceiveDonation.WomansProfile
import com.example.handsofcompassion.ViewModel.ViewModelStock
import com.example.handsofcompassion.databinding.ItemWomansBinding
import com.google.firebase.firestore.FirebaseFirestore


class AdapterWomansClothing (
    private val context: Context,
    private val womanClothingList: MutableList<WomanClothing>
): RecyclerView.Adapter<AdapterWomansClothing.WomanClothingViewHolder>() {

    lateinit var dialog: AlertDialog
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WomanClothingViewHolder {
        val itemList = ItemWomansBinding.inflate(LayoutInflater.from(context), parent, false)
        return WomanClothingViewHolder(itemList)
    }

    override fun getItemCount(): Int = womanClothingList.size
    override fun onBindViewHolder(holder: WomanClothingViewHolder, position: Int) {

        holder.type.text = womanClothingList[position].typeClothing
        holder.size.text = womanClothingList[position].sizeClothing
        holder.state.text = womanClothingList[position].stateClothing


        holder.edt.setOnClickListener {

            if (context is AppCompatActivity) {
                val intent = Intent(context, WomansClothingListDetail::class.java)
                intent.putExtra("id", womanClothingList[position].id)
                intent.putExtra(ViewModelStock.TYPE_CLOTHING_WOMAN, womanClothingList[position].typeClothing)
                intent.putExtra(ViewModelStock.SIZE_CLOTHING_WOMAN, womanClothingList[position].sizeClothing)
                intent.putExtra(ViewModelStock.STATE_CLOTHING_WOMAN, womanClothingList[position].stateClothing)
                context.startActivity(intent)
                context.finish()
            }

        }
        holder.delete.setOnClickListener {

            val id = womanClothingList[position].id
            alertDialog(id.toString(), position)

        }

        holder.profile.setOnClickListener {

            //QUANDO FIZER SELECIONAR O BRINQUEDO COLOCAR AQUI A TELA CRIADA...

            if (context is AppCompatActivity) {
                val intent = Intent(context, WomansProfile::class.java)
                intent.putExtra("id", womanClothingList[position].id)
                intent.putExtra(ViewModelStock.TYPE_CLOTHING_WOMAN, womanClothingList[position].typeClothing)
                intent.putExtra(ViewModelStock.SIZE_CLOTHING_WOMAN, womanClothingList[position].sizeClothing)
                intent.putExtra(ViewModelStock.STATE_CLOTHING_WOMAN, womanClothingList[position].stateClothing)
                context.startActivity(intent)
                context.finish()
            }


        }
    }

    inner class WomanClothingViewHolder(binding: ItemWomansBinding) :
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

            firestore.collection("WomansClothing").document(id).delete()
                .addOnCompleteListener {

                    if (it.isSuccessful) {

                        womanClothingList.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemChanged(position, womanClothingList)


                    }

                }.addOnFailureListener { }
        }
        alertDialog.setNegativeButton(R.string.nao) { dialog, which -> }
        dialog = alertDialog.create()
        dialog.show()
    }
}