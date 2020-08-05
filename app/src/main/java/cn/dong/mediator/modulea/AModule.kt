package cn.dong.mediator.modulea

import cn.dong.mediator.BaseModule
import cn.dong.mediator.ServiceProvider
import cn.dong.mediator.annotation.ModuleService
import cn.dong.mediator.annotation.ModuleServiceProvider
import cn.dong.mediator.moduleb.AudioRecorderProvider
import cn.dong.mediator.moduleb.EngineProvider

/**
 * @author zhaodong on 2020/07/02.
 */
@ModuleServiceProvider(AService::class)
open class AModule : BaseModule(), ServiceProvider<AService> {

    private val view = AModuleView()
    private val viewModel = AModuleViewModel()

    // property lazy
    @ModuleService
    private val engineProvider: EngineProvider by lazy { TODO() }

    // property lateinit
    @ModuleService
    lateinit var AudioRecorderProvider: AudioRecorderProvider

    override fun provide(): AService = viewModel

    fun foo() {
        engineProvider.engineService
    }
}
