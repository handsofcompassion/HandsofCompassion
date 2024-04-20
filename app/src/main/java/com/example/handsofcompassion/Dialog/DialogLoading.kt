package com.example.handsofcompassion.Dialog

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Hilt_MainActivity

class DialogLoading(private val activity: Activity) {

    lateinit var dialog: AlertDialog

    fun initDialog(){

        val builder = AlertDialog.Builder(activity)
        val layoutInflater = activity.layoutInflater
        builder.setView(layoutInflater.inflate(R.layout.dialog_loading, null))
        builder.setCancelable(false)
        dialog = builder.create()
        dialog.show()
    }

    fun exitDialog(){

        dialog.dismiss()

    }

}