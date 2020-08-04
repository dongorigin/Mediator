package cn.dong.mediator.checker

import cn.dong.mediator.annotation.ModuleService
import cn.dong.mediator.annotation.ModuleServiceProvider
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.TypeElement
import javax.lang.model.type.DeclaredType
import javax.lang.model.type.TypeMirror

class ModuleClass(
    private val element: TypeElement,
    private val env: ProcessingEnvironment
) {
    val providedServices = mutableListOf<TypeMirror>()
    val requiredServices = mutableListOf<DeclaredType>()

    init {
        // provided services
        element.findAllAnnotationMirror(ModuleServiceProvider::class.java, env.elementUtils)
            ?.findAnnotationValue("value")
            ?.getClasses()
            ?.forEach { providedServices.add(it) }
        println("module: provide=${element.findAllAnnotationMirror(ModuleServiceProvider::class.java, env.elementUtils)}")

        // required services
        for (memberElement in env.elementUtils.getAllMembers(element)) {

            println("[moduleMember:${memberElement}, type=${memberElement.asType()}, kind=${memberElement.kind}]")
            if ((memberElement.kind.isField || memberElement.isMethod())
                && memberElement.isAnnotationPresent(ModuleService::class.java)
            ) {
//                memberElement.findAllAnnotationMirror()

                //        val optional = serviceProvider
//                ?.findAnnotationValueWithDefaults("optional", elementUtils)
//                ?.value
                val type = memberElement.asType()
                println("isModuleService! $type")
                if (type is DeclaredType) {
                    requiredServices.add(type)
                }
            }
        }

        println("module:${element.simpleName}, providedServices:$providedServices, requiredServices:$requiredServices ")
    }
}
