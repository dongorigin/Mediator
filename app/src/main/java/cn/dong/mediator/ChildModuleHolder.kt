package cn.dong.mediator

import cn.dong.mediator.annotation.Module
import cn.dong.mediator.modulec.CModule

/**
 * @author zhaodong on 2020/08/04.
 */
@cn.dong.mediator.annotation.ModuleHolder
class ChildModuleHolder : JavaBaseModuleHolder() {

    @Module
    val moduleC = CModule()

}
