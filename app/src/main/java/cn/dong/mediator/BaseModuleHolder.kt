package cn.dong.mediator

import cn.dong.mediator.annotation.Module
import cn.dong.mediator.modulea.ChildAModule
import cn.dong.mediator.moduleb.BModule

/**
 * @author zhaodong on 2020/07/03.
 */
open class BaseModuleHolder {

    @Module
    val moduleA = ChildAModule()

    @Module
    lateinit var moduleB: BModule

}
