package cn.dong.mediator

/**
 * @author zhaodong on 2020/07/28.
 */
interface ServiceProvider<Service> {

    fun provide() : Service
}
