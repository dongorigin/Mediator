package cn.dong.mediator.util

import android.content.Context
import android.widget.Toast
import cn.dong.mediator.App

/**
 * @author zhaodong on 2020/07/09.
 */
object Toasts {

    private val context: Context
        get() = App.appContext

    fun show(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
