package cn.dong.mediator.annotation

import kotlin.reflect.KClass

/**
 * @author zhaodong on 2020/07/08.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ModuleServiceProvider(vararg val value: KClass<*>)
