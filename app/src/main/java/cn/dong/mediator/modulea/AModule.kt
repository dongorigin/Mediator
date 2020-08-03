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
class AModule : BaseModule(), ServiceProvider<AService> {

    private val view = AModuleView()
    private val viewModel = AServiceViewModel()

    @ModuleService
    private lateinit var engineProvider: EngineProvider

    @ModuleService
    private lateinit var audioRecorderProvider: AudioRecorderProvider

    override fun provide(): AService = viewModel
}
