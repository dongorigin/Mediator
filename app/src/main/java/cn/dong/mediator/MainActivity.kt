package cn.dong.mediator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.dong.mediator.wrapper.WrapperFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actualWrapper = WrapperFactory.ActualWrapper<ServiceA>()
        val wrapper = WrapperFactory.create(ServiceA::class.java, actualWrapper)

        actualWrapper.actual = null
    }
}