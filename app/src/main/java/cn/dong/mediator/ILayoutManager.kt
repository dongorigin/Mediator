package cn.dong.mediator

import android.view.View

/**
 * @author zhaodong on 2020/08/11.
 */
interface ILayoutManager {

    /** 兼容逻辑 */
    val rootView: View

    // 测试接口，先不管层级和位置，只添加
    fun addView(view: View)

}
