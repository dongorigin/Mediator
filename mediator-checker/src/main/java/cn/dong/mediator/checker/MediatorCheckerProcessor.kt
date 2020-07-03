package cn.dong.mediator.checker

import cn.dong.mediator.annotation.Module
import cn.dong.mediator.annotation.ModuleService
import cn.dong.mediator.annotation.ModuleServiceProvider
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.tools.Diagnostic

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
        messager.printMessage(Diagnostic.Kind.NOTE, "mediator checker process start~")

        env.getElementsAnnotatedWith(Module::class.java).forEach {

        }

        return false
    }
}
