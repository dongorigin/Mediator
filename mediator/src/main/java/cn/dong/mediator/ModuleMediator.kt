package cn.dong.mediator

/**
 * 模块中介，负责牵线搭桥
 *
 * @author zhaodong on 2020/07/02.
 */
class ModuleMediator {

    private val services = HashMap<Class<*>, Any>()

    fun <T : Any> register(service: T, serviceClass: Class<T>) {
        services[serviceClass] = service
    }

    fun <T> findService(serviceClass: Class<T>): T? {
        return services[serviceClass] as? T
    }

}
