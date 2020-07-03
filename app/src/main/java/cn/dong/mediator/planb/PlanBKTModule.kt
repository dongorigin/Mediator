package cn.dong.mediator.planb

import cn.dong.mediator.annotation.ModuleService
import cn.dong.mediator.modulea.ServiceA

/**
 * @author zhaodong on 2020/07/01.
 */
class PlanBKTModule {

    @ModuleService
    private lateinit var serviceA: ServiceA

}