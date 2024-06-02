package com.example.handsofcompassion.Adapter




import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.handsofcompassion.Data.WomanChildrenClothing
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Lists.DetailList.WomansClothingChildrenListDetail
import com.example.handsofcompassion.ViewModel.ViewModelStock
import com.example.handsofcompassion.databinding.ItemWomansChildrenBinding
import com.google.firebase.firestore.FirebaseFirestore

class AdapterWomansChildrenClothing

    (
    private val context: Context,
    private val womanChildrenClothingList: MutableList<WomanChildrenClothing>
): RecyclerView.Adapter<AdapterWomansChildrenClothing.WomanChildrenClothingViewHolder>() {

    lateinit var dialog: AlertDialog
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WomanChildrenClothingViewHolder {
        val itemList = ItemWomansChildrenBinding.inflate(LayoutInflater.from(context), parent, false)
        return WomanChildrenClothingViewHolder(itemList)
    }

    override fun getItemCount(): Int = womanChildrenClothingList.size
    override fun onBindViewHolder(holder: WomanChildrenClothingViewHolder, position: Int) {

        holder.type.text = womanChildrenClothingList[position].typeClothing
        holder.size.text = womanChildrenClothingList[position].sizeClothing
        holder.state.text = womanChildrenClothingList[position].stateClothing

        holder.edt.setOnClickListener {
            if (context is AppCompatActivity) {
                val intent = Intent(context, WomansClothingChildrenListDetail::class.java)
                intent.putExtra("id", womanChildrenClothingList[position].id)
                intent.putExtra(ViewModelStock.TYPE_CLOTHING_WOMAN_CHILDREN, womanChildrenClothingList[position].typeClothing)
                intent.putExtra(ViewModelStock.SIZE_CLOTHING_WOMAN_CHILDREN, womanChildrenClothingList[position].sizeClothing)
                intent.putExtra(ViewModelStock.STATE_CLOTHING_WOMAN_CHILDREN, womanChildrenClothingList[position].stateClothing)
                context.startActivity(intent)
                context.finish()
            }
        }

        holder.delete.setOnClickListener {
            val id = womanChildrenClothingList[position].id
            alertDialog(id.toString(), position)
        }

        holder.profile.setOnClickListener {
            //QUANDO FIZER SELECIONAR O BRINQUEDO COLOCAR AQUI A TELA CRIADA...
        }
    }

    inner class WomanChildrenClothingViewHolder(binding: ItemWomansChildrenBinding) :
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
            firestore.collection("WomansClothingChildren").document(id).delete()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        womanChildrenClothingList.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemChanged(position, womanChildrenClothingList)
                    }
                }.addOnFailureListener { }
        }
        alertDialog.setNegativeButton(R.string.nao) { dialog, which -> }
        dialog = alertDialog.create()
        dialog.show()
    }
}
