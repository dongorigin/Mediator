package cn.dong.mediator

import cn.dong.mediator.annotation.Module
import cn.dong.mediator.modulea.ModuleA
import cn.dong.mediator.moduleb.ModuleB

/**
 * @author zhaodong on 2020/07/03.
 */
class ModuleHolder {

    @Module
    val moduleA = ModuleA()

    @Module
    val moduleB = ModuleB()

}
