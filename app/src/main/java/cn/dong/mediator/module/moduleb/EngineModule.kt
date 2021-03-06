package cn.dong.mediator.module.moduleb

import cn.dong.mediator.ILayoutManager
import cn.dong.mediator.Module
import cn.dong.mediator.annotation.ModuleServiceProvider

/**
 * 问题：如何知道服务此时是否存在？
 *
 * @author zhaodong on 2020/07/02.
 */
@ModuleServiceProvider(EngineProvider::class, AudioRecorderProvider::class)
class EngineModule : Module, EngineProvider, AudioRecorderProvider {

    private var _engineService: EngineService? = null

    fun start() {
        _engineService = EngineController()
    }

    fun stop() {
        _engineService = null
    }

    //region Service
    override val engineService: EngineService?
        get() = _engineService

    override val audioRecorder: AudioRecorder?
        get() = _engineService
    //endregion

    override fun setup(layoutManager: ILayoutManager) {
    }

}
