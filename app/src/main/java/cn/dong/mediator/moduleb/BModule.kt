package cn.dong.mediator.moduleb

import cn.dong.mediator.ILayoutManager
import cn.dong.mediator.Module
import cn.dong.mediator.RoomContext
import cn.dong.mediator.annotation.ModuleServiceProvider

/**
 * 问题：如何知道服务此时是否存在？
 *
 * @author zhaodong on 2020/07/02.
 */
@ModuleServiceProvider(EngineProvider::class, AudioRecorderProvider::class)
class BModule(roomContext: RoomContext) : Module, EngineProvider, AudioRecorderProvider {

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

    override fun setup(layoutManager: ILayoutManager) {
        TODO("Not yet implemented")
    }
    //endregion

}
