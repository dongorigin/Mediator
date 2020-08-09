package cn.dong.mediator.checker

import cn.dong.mediator.annotation.ModuleHolder
import cn.dong.mediator.annotation.ModuleService
import cn.dong.mediator.annotation.ModuleServiceProvider
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic.Kind

/**
 * @author zhaodong on 2020/07/03.
 */
class MediatorCheckerProcessor : AbstractProcessor() {

    override fun getSupportedAnnotationTypes(): Set<String> = setOf(
        ModuleServiceProvider::class.java.canonicalName,
        ModuleService::class.java.canonicalName
    )

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    override fun process(elements: Set<TypeElement>, env: RoundEnvironment): Boolean {
        for (element in env.getElementsAnnotatedWith(ModuleHolder::class.java)) {
            val moduleHolderClass = ModuleHolderClass(element as TypeElement, processingEnv)
            val missingServices = moduleHolderClass.findMissingServices()
            if (missingServices.isNotEmpty()) {
                error(element, "[${element.simpleName}] missing required services [$missingServices]")
            }
        }
        return false
    }

    private fun error(element: Element, message: String) {
        processingEnv.messager.printMessage(Kind.ERROR, message, element)
    }
}
