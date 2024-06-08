package com.example.handsofcompassion.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.handsofcompassion.Data.BasicBasket
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Lists.DetailList.BasicBasketListDetail
import com.example.handsofcompassion.UI.ReceiveDonation.BasicBasketProfile
import com.example.handsofcompassion.ViewModel.ViewModelStock
import com.example.handsofcompassion.databinding.ItemBasicBasketBinding
import com.google.firebase.firestore.FirebaseFirestore

class AdapterBasicBasket(
    private val context: Context,
    private val basicBasketList: MutableList<BasicBasket>
): RecyclerView.Adapter<AdapterBasicBasket.BasicBasketViewHolder>() {

    lateinit var dialog: AlertDialog
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasicBasketViewHolder {
        val itemList = ItemBasicBasketBinding.inflate(LayoutInflater.from(context), parent, false)
        return BasicBasketViewHolder(itemList)
    }

    override fun getItemCount(): Int = basicBasketList.size


    override fun onBindViewHolder(holder: BasicBasketViewHolder, position: Int) {
        val basicBasket = basicBasketList[position]

        holder.item1.text = basicBasket.item1
        holder.item2.text = basicBasket.item2
        holder.item3.text = basicBasket.item3
        holder.item4.text = basicBasket.item4
        holder.item5.text = basicBasket.item5
        holder.item6.text = basicBasket.item6
        holder.item7.text = basicBasket.item7
        holder.item8.text = basicBasket.item8
        holder.item9.text = basicBasket.item9
        holder.item10.text = basicBasket.item10
        holder.item11.text = basicBasket.item11
        holder.item12.text = basicBasket.item12

        holder.edt.setOnClickListener {

            if (context is AppCompatActivity) {

                val basicBasket = basicBasketList[position]
                val intent = Intent(context, BasicBasketListDetail::class.java)
                intent.putExtra("id", basicBasketList[position].id)
                intent.putExtra(ViewModelStock.ITEM_1_EXTRA, basicBasket.item1)
                intent.putExtra(ViewModelStock.ITEM_2_EXTRA, basicBasket.item2)
                intent.putExtra(ViewModelStock.ITEM_3_EXTRA, basicBasket.item3)
                intent.putExtra(ViewModelStock.ITEM_4_EXTRA, basicBasket.item4)
                intent.putExtra(ViewModelStock.ITEM_5_EXTRA, basicBasket.item5)
                intent.putExtra(ViewModelStock.ITEM_6_EXTRA, basicBasket.item6)
                intent.putExtra(ViewModelStock.ITEM_7_EXTRA, basicBasket.item7)
                intent.putExtra(ViewModelStock.ITEM_8_EXTRA, basicBasket.item8)
                intent.putExtra(ViewModelStock.ITEM_9_EXTRA, basicBasket.item9)
                intent.putExtra(ViewModelStock.ITEM_10_EXTRA, basicBasket.item10)
                intent.putExtra(ViewModelStock.ITEM_11_EXTRA, basicBasket.item11)
                intent.putExtra(ViewModelStock.ITEM_12_EXTRA, basicBasket.item12)
                context.startActivity(intent)
                context.finish()
            }
        }

        holder.edt.setOnClickListener {
            if (context is AppCompatActivity) {
                val intent = Intent(context, BasicBasketListDetail::class.java).apply {
                    putExtra("id", basicBasketList[position].id)
                }
                context.startActivity(intent)
            }
        }


        holder.delete.setOnClickListener {

           val id = basicBasketList[position].id
            alertDialog(id.toString(), position)


        }

        holder.profile.setOnClickListener {

            if (context is AppCompatActivity) {

                val basicBasket = basicBasketList[position]
                val intent = Intent(context, BasicBasketProfile::class.java)
                intent.putExtra("id", basicBasketList[position].id)
                intent.putExtra(ViewModelStock.ITEM_1_EXTRA, basicBasket.item1)
                intent.putExtra(ViewModelStock.ITEM_2_EXTRA, basicBasket.item2)
                intent.putExtra(ViewModelStock.ITEM_3_EXTRA, basicBasket.item3)
                intent.putExtra(ViewModelStock.ITEM_4_EXTRA, basicBasket.item4)
                intent.putExtra(ViewModelStock.ITEM_5_EXTRA, basicBasket.item5)
                intent.putExtra(ViewModelStock.ITEM_6_EXTRA, basicBasket.item6)
                intent.putExtra(ViewModelStock.ITEM_7_EXTRA, basicBasket.item7)
                intent.putExtra(ViewModelStock.ITEM_8_EXTRA, basicBasket.item8)
                intent.putExtra(ViewModelStock.ITEM_9_EXTRA, basicBasket.item9)
                intent.putExtra(ViewModelStock.ITEM_10_EXTRA, basicBasket.item10)
                intent.putExtra(ViewModelStock.ITEM_11_EXTRA, basicBasket.item11)
                intent.putExtra(ViewModelStock.ITEM_12_EXTRA, basicBasket.item12)
                context.startActivity(intent)
                context.finish()
            }


        }
    }
    inner class BasicBasketViewHolder(binding: ItemBasicBasketBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val item1 = binding.item1Response
        val item2 = binding.item2Response
        val item3 = binding.item3Response
        val item4 = binding.item4Response
        val item5 = binding.item5Response
        val item6 = binding.item6Response
        val item7 = binding.item7Response
        val item8 = binding.item8Response
        val item9 = binding.item9Response
        val item10 = binding.item10Response
        val item11 = binding.item11Response
        val item12 = binding.item12Response
        val edt = binding.imgEdit
        val delete = binding.imgDelete
        val profile = binding.cardProfile

    }
    private fun alertDialog(id: String, position: Int) {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle(R.string.desejaExcluirCesta)
        alertDialog.setMessage(R.string.temCerteza)
        alertDialog.setPositiveButton(context.getString(R.string.sim)) { dialog, whintch ->

            firestore.collection("BasicBasket").document(id).delete()
                .addOnCompleteListener {

                    if (it.isSuccessful) {

                        basicBasketList.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemChanged(position, basicBasketList)


                    }

                }.addOnFailureListener { }
        }
        alertDialog.setNegativeButton(R.string.nao) { dialog, whintch -> }
        dialog = alertDialog.create()
        dialog.show()
    }
}