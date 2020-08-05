package cn.dong.mediator.annotation

/**
 * @author zhaodong on 2020/07/01.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.FUNCTION)
annotation class ModuleService(val optional: Boolean = false)
