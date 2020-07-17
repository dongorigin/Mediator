package cn.dong.mediator.checker

import cn.dong.mediator.annotation.ModuleService
import cn.dong.mediator.annotation.ModuleServiceProvider
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.tools.Diagnostic.Kind

/**
 * @author zhaodong on 2020/07/03.
 */
class MediatorCheckerProcessor : AbstractProcessor() {
    private lateinit var elementUtils: Elements  // 元素操作辅助类
    private lateinit var messager: Messager  // 日志辅助类
    private lateinit var filer: Filer // 文件辅助类

    @Synchronized
    override fun init(env: ProcessingEnvironment) {
        super.init(env)
        elementUtils = env.elementUtils
        messager = env.messager
        filer = env.filer
    }

    override fun getSupportedAnnotationTypes(): Set<String> = setOf(
        ModuleServiceProvider::class.java.canonicalName,
        ModuleService::class.java.canonicalName
    )

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    override fun process(elements: Set<TypeElement>, env: RoundEnvironment): Boolean {
        messager.printMessage(Kind.NOTE, "mediator checker process start~\n")

        val moduleHolderMap: MutableMap<Element, ModuleHolderClass> = mutableMapOf()
        val moduleMap = mutableMapOf<Element, ModuleClass>()
        // module
        for (element in env.getElementsAnnotatedWith(ModuleServiceProvider::class.java)) {
            val moduleClass = moduleMap.getOrDefault(element, ModuleClass())
            val annotation = element.getAnnotation(ModuleServiceProvider::class.java)
            val providerAnnotation = elementUtils.getAllAnnotationMirrors(element)
                .find { it.annotationType == elementUtils.getTypeElement(ModuleServiceProvider::class.java.name).asType() }
            val elementValuesWithDefaults =
                elementUtils.getElementValuesWithDefaults(providerAnnotation)
            elementValuesWithDefaults.values.first().value
        }

//        // module
//        for (element in env.getElementsAnnotatedWith(ModuleService::class.java)) {
//            val moduleElement = element.enclosingElement
//            val moduleClass = moduleMap.getOrDefault(moduleElement, ModuleClass())
//            if (element is TypeElement) {
////                printMessage(Kind.NOTE, element, element.asType().toString())
//                moduleClass.requiredServices.add(element.asType().toString())
//            }
//        }

//        // module holder
//        for (element in env.getElementsAnnotatedWith(Module::class.java)) {
//            val moduleHolderElement = element.enclosingElement
//            val moduleHolderClass = moduleHolderMap.getOrDefault(
//                moduleHolderElement,
//                ModuleHolderClass()
//            )
//            moduleHolderClass
//        }

        messager.printMessage(Kind.NOTE, "mediator checker process end~")

        return false
    }

    private fun error(element: Element, message: String) {
        printMessage(Kind.ERROR, element, message)
    }

    private fun printMessage(kind: Kind, element: Element, message: String?) {
        processingEnv.messager.printMessage(kind, message, element)
    }
}

class ModuleClass {
    val providedServices = mutableListOf<String>()
    val requiredServices = mutableListOf<String>()
}

class ModuleHolderClass {
    val modules = mutableListOf<ModuleClass>()

}
