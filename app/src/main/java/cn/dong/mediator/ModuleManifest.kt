package cn.dong.mediator

import androidx.annotation.LayoutRes

/**
 * @author zhaodong on 2020/08/11.
 */
interface ModuleManifest {

    val modules: List<Module>

    @LayoutRes
    fun getLayoutId(): Int?
}
