package cn.dong.mediator.annotation

import kotlin.reflect.KClass

/**
 * @author zhaodong on 2020/07/02.
 */
@Target(AnnotationTarget.CLASS)
annotation class ModuleServiceProvider(vararg val value: KClass<*>) {
}
