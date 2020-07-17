package cn.dong.mediator

import android.app.Application
import android.content.Context

/**
 * @author zhaodong on 2020/07/09.
 */
class App : Application() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}
