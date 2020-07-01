package cn.dong.mediator

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Proxy

/**
 * @author zhaodong on 2020/07/01.
 */
inline fun <reified T : Any> create(wrapper: Wrapper<T>): T {
    return Proxy.newProxyInstance(
        wrapper.javaClass.classLoader,
        arrayOf(T::class.java),
        InvocationHandler { proxy, method, args ->
            val actual: T? = wrapper.actual
            if (actual != null) {
                actual::class.java.methods
                    .find { it == method }
                    ?.invoke(actual, args)
            } else {
                null
            }
        }) as T
}

class Wrapper<T> {
    var actual: T? = null
}
