package cn.dong.mediator.checker

import cn.dong.mediator.annotation.Module
import javax.annotation.processing.Messager
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.TypeElement
import javax.lang.model.type.TypeMirror
import javax.lang.model.util.Types

/**
 * @author zhaodong on 2020/07/07.
 */
class ModuleHolderClass(
    private val holderElement: TypeElement,
    private val env: ProcessingEnvironment
) {

    private val messager: Messager = env.messager
    private val types: Types = env.typeUtils

    private val modules = mutableListOf<ModuleClass>()
    private val providedServices = mutableSetOf<TypeMirror>()
    private val requiredServices = mutableSetOf<TypeMirror>()

    init {
        println("[moduleHolder:${holderElement},kind=${holderElement.kind},enclosed=${holderElement.enclosedElements}]")

        for (memberElement in env.elementUtils.getAllMembers(holderElement)) {
            println("[holderMember:${memberElement},kind=${memberElement.kind},enclosed=${memberElement.enclosedElements}]")
            println("annotation=${env.elementUtils.getAllAnnotationMirrors(memberElement)}")

            if ((memberElement.kind.isField)
                && memberElement.isAnnotationPresent(Module::class.java)
            ) {
                println("isModule!, ${memberElement.getDeclaredTypeElement()}")
                val moduleClass = ModuleClass(memberElement.getDeclaredTypeElement(), env)
                modules.add(moduleClass)
                providedServices.addAll(moduleClass.providedServices)
                requiredServices.addAll(moduleClass.requiredServices)
            }
        }
    }

    fun check(): Boolean {
        println("check provide=$providedServices, required=$requiredServices")
        for (requiredService in requiredServices) {
            providedServices.find { types.isSameType(it, requiredService) } ?: return false
        }
        return true
    }
}
