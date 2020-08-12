package cn.dong.mediator.moduleholder

import cn.dong.mediator.annotation.Module
import cn.dong.mediator.module.modulea.ChildAModule
import cn.dong.mediator.module.moduleb.EngineModule

/**
 * @author zhaodong on 2020/07/03.
 */
open class BaseModuleHolder {

    @Module
    val moduleA = ChildAModule()

    @Module
    lateinit var engineModule: EngineModule

}
