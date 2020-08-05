package cn.dong.mediator.annotation

import java.lang.annotation.Inherited
import kotlin.reflect.KClass

/**
 * @author zhaodong on 2020/07/08.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Inherited
annotation class ModuleServiceProvider(vararg val value: KClass<*>)
