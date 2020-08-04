package cn.dong.mediator

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var moduleHolder: ChildModuleHolder

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log("onCreate")
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        log("onStart")
    }

    override fun onResume() {
        super.onResume()
        log("onResume")
        setupModules()
    }

    private fun setupModules() {
        moduleHolder = ChildModuleHolder()
        log("add observer before")
        // 问题：onResume 才注册，还会收到已经发生的事件通知吗？比如 onCreate、onStart
        // 实测会收到
        lifecycle.addObserver(moduleHolder.moduleA)
        lifecycle.addObserver(moduleHolder.moduleB)
        log("add observer after")
    }

    private fun log(message: String) {
        Log.i(TAG, message)
    }
}
