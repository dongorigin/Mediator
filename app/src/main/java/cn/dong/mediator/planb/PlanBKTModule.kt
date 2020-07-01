package cn.dong.mediator.planb

import cn.dong.mediator.RoomService
import cn.dong.mediator.ServiceA

/**
 * @author zhaodong on 2020/07/01.
 */
class PlanBKTModule {

    @RoomService
    private lateinit var serviceA: ServiceA

}