package cn.dong.mediator

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * @author zhaodong on 2020/07/09.
 */
open class BaseModule : LifecycleObserver {

    init {
        Log.i("BaseModule", "init: ${this::class.simpleName}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.i("BaseModule", "onCreate: ${this::class.simpleName}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Log.i("BaseModule", "onStart: ${this::class.simpleName}")
    }
}
