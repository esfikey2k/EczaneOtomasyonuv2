
package com.safakkurt.eczaneotomasyonuv2.functions.extensionfunction

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button

fun ViewGroup.showDialog(layout: Int,button: Int){
    val dialogBinding= LayoutInflater.from(context).inflate(layout,this,false)
    val myDialog= Dialog(context)
    myDialog.setContentView(dialogBinding)

    myDialog.setCancelable(true)
    myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    myDialog.show()

    val buttonClose= dialogBinding.findViewById<Button>(button)
    buttonClose.setOnClickListener {
        myDialog.dismiss()
    }
}