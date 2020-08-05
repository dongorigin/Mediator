package cn.dong.mediator.checker

import cn.dong.mediator.annotation.ModuleService
import cn.dong.mediator.annotation.ModuleServiceProvider
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.*
import javax.lang.model.type.DeclaredType
import javax.lang.model.type.TypeKind
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

        // required services
        val allMembers = element.getAllMembers()
        for (memberElement in allMembers) {
            if (memberElement.isAnnotationPresent(ModuleService::class.java)) {
                //        val optional = serviceProvider
                //                ?.findAnnotationValueWithDefaults("optional", elementUtils)
                //                ?.value

                val service: DeclaredType? = when (memberElement) {
                    is VariableElement -> {
                        // Java 字段，用字段类型
                        memberElement.asType() as DeclaredType
                    }
                    is ExecutableElement -> {
                        val returnType = memberElement.returnType
                        if (returnType is DeclaredType) {
                            // Java 方法或 Kotlin 函数，用返回值类型
                            returnType
                        } else if (memberElement.isKotlinPropertyAnnotationDelegate()) {
                            // Kotlin 属性注解生成的代理方法
                            findKotlinPropertyType(memberElement, allMembers)
                        } else {
                            null
                        }
                    }
                    else -> null
                }
                if (service != null) {
                    requiredServices.add(service)
                }
            }
        }
        println("[Module]${element.simpleName}: providedServices:$providedServices, requiredServices:$requiredServices")
    }

    private fun ExecutableElement.isKotlinPropertyAnnotationDelegate(): Boolean {
        return simpleName.endsWith(KOTLIN_PROPERTY_ANNOTATION_DELEGATE_SUFFIX)
                && returnType.kind == TypeKind.VOID
    }

    /**
     *
     */
    private fun findKotlinPropertyType(element: ExecutableElement, allMembers: List<Element>)
            : DeclaredType? {
        val endIndex = element.simpleName.length - KOTLIN_PROPERTY_ANNOTATION_DELEGATE_SUFFIX.length
        val propertyName = element.simpleName.substring(0, endIndex)
        val getterName = "get" + propertyName.capitalize()
        val getterMethod = allMembers.find {
            it.simpleName.toString() == getterName && it.kind == ElementKind.METHOD
        }
        return if (getterMethod is ExecutableElement) {
            getterMethod.returnType as DeclaredType
        } else {
            null
        }
    }

    companion object {
        private const val KOTLIN_PROPERTY_ANNOTATION_DELEGATE_SUFFIX = "\$annotations"
    }
}
