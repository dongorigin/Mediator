package cn.dong.mediator

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Proxy

/**
 * 包装类
 * @author zhaodong on 2020/07/01.
 */
/** [provider] 提供实际实例 */
inline fun <reified T : Any> createWrapper(noinline provider: () -> T?): T {
    return Proxy.newProxyInstance(
        provider.javaClass.classLoader,
        arrayOf(T::class.java),
        InvocationHandler { proxy, method, args ->
            val actual: T? = provider.invoke()
            if (actual != null) {
                actual::class.java.methods
                    .find { it == method }
                    ?.invoke(actual, args)
            } else {
                null
            }
        }) as T
}
