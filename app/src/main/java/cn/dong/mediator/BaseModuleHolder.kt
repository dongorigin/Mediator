package cn.dong.mediator

import cn.dong.mediator.annotation.Module
import cn.dong.mediator.modulea.AModule
import cn.dong.mediator.moduleb.BModule

/**
 * @author zhaodong on 2020/07/03.
 */
open class BaseModuleHolder {

    @Module
    var moduleA = AModule()

    @Module
    val moduleB = BModule()

}
