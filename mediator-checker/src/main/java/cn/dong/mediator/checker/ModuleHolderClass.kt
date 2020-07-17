package cn.dong.mediator.checker

import cn.dong.mediator.annotation.Module
import cn.dong.mediator.annotation.ModuleService
import cn.dong.mediator.annotation.ModuleServiceProvider
import javax.annotation.processing.Messager
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.tools.Diagnostic

/**
 * @author zhaodong on 2020/07/07.
 */
class ModuleHolderClass2(
    val moduleHolderElement: Element,
    val env: RoundEnvironment,
    val messager: Messager
) {

    val modules = mutableListOf<Element>()
    val providedServices = mutableListOf<Class<*>>()
    val requiredServices = mutableListOf<Class<*>>()

    init {
        messager.printMessage(Diagnostic.Kind.NOTE, "[moduleHolderElement:${moduleHolderElement},kind=${moduleHolderElement.kind},enclosed=${moduleHolderElement.enclosedElements}]")
        for (holderElement in moduleHolderElement.enclosedElements) {
            listOf("")
            messager.printMessage(Diagnostic.Kind.NOTE, "[holderElement:${holderElement},kind=${holderElement.kind},enclosed=${holderElement.enclosedElements}]")

            if (holderElement.kind.isField
                && holderElement.isAnnotationPresent(Module::class.java)
            ) {
                modules.add(holderElement)
                holderElement.javaClass.getAnnotation(ModuleServiceProvider::class.java)?.let { serviceProvider ->
                    serviceProvider.value
                    providedServices.addAll(serviceProvider.value.map { it.java })
                }
                for (moduleElement in holderElement.enclosedElements) {
                    messager.printMessage(Diagnostic.Kind.NOTE,
                        "[moduleElement:type=${moduleElement.asType()}, kind=${moduleElement.kind}]")
                    if (moduleElement.kind.isField
                        || moduleElement.kind == ElementKind.METHOD) {
                        moduleElement.getAnnotation(ModuleService::class.java)?.let {

//                            requiredServices.add(moduleElement)
                        }
                    }
                }

            }
        }
    }

    fun check(): Boolean {
        return false
    }

    fun <A : Annotation> Element.isAnnotationPresent(annotationClass: Class<A>): Boolean {
        return this.getAnnotation(annotationClass) != null
    }
}