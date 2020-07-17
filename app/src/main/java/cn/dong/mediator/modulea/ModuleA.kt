package cn.dong.mediator.modulea

import cn.dong.mediator.BaseModule
import cn.dong.mediator.annotation.ModuleService
import cn.dong.mediator.annotation.ModuleServiceProvider
import cn.dong.mediator.moduleb.AudioRecorderProvider
import cn.dong.mediator.moduleb.EngineProvider
import cn.dong.mediator.service

/**
 * @author zhaodong on 2020/07/02.
 */
@ModuleServiceProvider(ServiceA::class)
class ModuleA : BaseModule(), ServiceA {

    @ModuleService
    private lateinit var engineProvider: EngineProvider

    @ModuleService
    val audioRecorderProvider: AudioRecorderProvider by service()

    override fun action(): Boolean {
        return false
    }

    fun start() {
        engineProvider.engineService?.sendMessage()
    }

    fun startRecord() {
        audioRecorderProvider.audioRecorder?.startRecord()
    }

}
