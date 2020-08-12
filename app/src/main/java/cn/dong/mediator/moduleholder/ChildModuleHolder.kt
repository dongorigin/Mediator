package cn.dong.mediator.moduleholder

import cn.dong.mediator.annotation.Module
import cn.dong.mediator.module.modulec.CModule

/**
 * @author zhaodong on 2020/08/04.
 */
@cn.dong.mediator.annotation.ModuleHolder
class ChildModuleHolder : BaseModuleHolder() {

    @Module
    val moduleC = CModule()

}
