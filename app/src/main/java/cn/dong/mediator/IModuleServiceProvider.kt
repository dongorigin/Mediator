package cn.dong.mediator

/**
 * @author zhaodong on 2020/07/02.
 */
interface IModuleServiceProvider<T> {

    fun getService(): T
}