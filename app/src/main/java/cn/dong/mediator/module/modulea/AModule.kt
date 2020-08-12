package cn.dong.mediator.module.modulea

import android.widget.FrameLayout
import cn.dong.mediator.*
import cn.dong.mediator.annotation.ModuleService
import cn.dong.mediator.annotation.ModuleServiceProvider
import cn.dong.mediator.module.moduleb.AudioRecorderProvider
import cn.dong.mediator.module.moduleb.EngineProvider

/**
 * @author zhaodong on 2020/07/02.
 */
@ModuleServiceProvider(AService::class)
open class AModule : Module, ServiceProvider<AService> {

    @ModuleService // property lazy 手动依赖注入
    private val engineProvider: EngineProvider by lazy { TODO() }

    @ModuleService // property lateinit 自动依赖注入（未实现）
    lateinit var audioRecorderProvider: AudioRecorderProvider

    private lateinit var view: AModuleView
    private val viewModel = AModuleViewModel()

    override fun provide(): AService = viewModel

    override fun setup(layoutManager: ILayoutManager) {
        // View 部分为了兼容老逻辑，先从 layout 中获取 view
        val container = layoutManager.rootView.findViewById<FrameLayout>(R.id.module_a_container)
        view = AModuleView(container)
        view.observe(viewModel)
    }
}
