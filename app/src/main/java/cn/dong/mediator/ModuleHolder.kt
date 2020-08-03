package cn.dong.mediator

import cn.dong.mediator.annotation.Module
import cn.dong.mediator.modulea.AModule
import cn.dong.mediator.moduleb.ModuleB

/**
 * @author zhaodong on 2020/07/03.
 */
class ModuleHolder {

    @Module
    val moduleA = AModule()

    @Module
    val moduleB = ModuleB()

}
