package cn.dong.mediator.checker

import cn.dong.mediator.annotation.ModuleHolder
import cn.dong.mediator.annotation.ModuleService
import cn.dong.mediator.annotation.ModuleServiceProvider
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.lang.model.util.Types
import javax.tools.Diagnostic.Kind

/**
 * @author zhaodong on 2020/07/03.
 */
class MediatorCheckerProcessor : AbstractProcessor() {

    private lateinit var elementUtils: Elements  // 元素操作辅助类
    private lateinit var messager: Messager  // 日志辅助类
    private lateinit var filer: Filer // 文件辅助类
    private lateinit var types: Types

    @Synchronized
    override fun init(env: ProcessingEnvironment) {
        super.init(env)
        elementUtils = env.elementUtils
        messager = env.messager
        filer = env.filer
        types = env.typeUtils
    }

    override fun getSupportedAnnotationTypes(): Set<String> = setOf(
        ModuleServiceProvider::class.java.canonicalName,
        ModuleService::class.java.canonicalName
    )

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    override fun process(elements: Set<TypeElement>, env: RoundEnvironment): Boolean {
        messager.printMessage(Kind.NOTE, "mediator checker process start~|")

        // module holder
        for (element in env.getElementsAnnotatedWith(ModuleHolder::class.java)) {
            val moduleHolderClass = ModuleHolderClass(element as TypeElement, processingEnv)
            if (!moduleHolderClass.check()) {
                error(element, "module holder [${element.simpleName}] not valid")
            }
        }
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
