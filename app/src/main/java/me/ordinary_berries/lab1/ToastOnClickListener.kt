package me.ordinary_berries.lab1

import android.content.Context
import android.view.View
import android.widget.Toast

class ToastOnClickListener(
    private val context: Context,
    private val toastMessage: String,
) : View.OnClickListener {
    override fun onClick(v: View?): Unit =
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
}