package cn.dong.mediator.checker

import cn.dong.mediator.annotation.Module
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.TypeElement
import javax.lang.model.type.TypeMirror

/**
 * @author zhaodong on 2020/07/07.
 */
class ModuleHolderClass(
    private val holderElement: TypeElement,
    private val env: ProcessingEnvironment
) {

    private val modules = mutableListOf<ModuleClass>()
    private val providedServices = mutableSetOf<TypeMirror>()
    private val requiredServices = mutableSetOf<TypeMirror>()

    init {
        for (memberElement in holderElement.getAllMembers()) {
            if ((memberElement.kind.isField)
                && memberElement.isAnnotationPresent(Module::class.java)
            ) {
                val moduleClass = ModuleClass(memberElement.getDeclaredTypeElement(), env)
                modules.add(moduleClass)
                providedServices.addAll(moduleClass.providedServices)
                requiredServices.addAll(moduleClass.requiredServices)
            }
        }
        println("[ModuleHolder]${holderElement.simpleName}: provide=$providedServices, required=$requiredServices")
    }

    fun findMissingServices(): List<TypeMirror> {
        val missingServices = mutableListOf<TypeMirror>()
        for (requiredService in requiredServices) {
            if (providedServices.find { env.typeUtils.isSameType(it, requiredService) } == null) {
                missingServices.add(requiredService)
            }
        }
        return missingServices
    }
}
