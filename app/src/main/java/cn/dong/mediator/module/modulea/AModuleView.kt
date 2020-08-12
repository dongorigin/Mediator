package cn.dong.mediator.module.modulea

import android.widget.FrameLayout
import android.widget.TextView
import cn.dong.mediator.R

/**
 * @author zhaodong on 2020/07/28.
 */
class AModuleView(
    val container: FrameLayout
) {

    val titleText: TextView = container.findViewById(R.id.module_a_title)

    fun observe(viewModel: AModuleViewModel) {
        titleText.text = "hello module a"
    }
}
