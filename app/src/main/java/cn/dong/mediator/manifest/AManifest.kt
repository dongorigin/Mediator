package cn.dong.mediator.manifest

import cn.dong.mediator.Module
import cn.dong.mediator.ModuleManifest
import cn.dong.mediator.R
import cn.dong.mediator.RoomContext
import cn.dong.mediator.modulea.AModule
import cn.dong.mediator.moduleb.BModule
import cn.dong.mediator.modulec.CModule

/**
 * @author zhaodong on 2020/08/11.
 */
class AManifest(roomContext: RoomContext) : ModuleManifest {

    private val _modules = mutableListOf(
        AModule(roomContext),
        BModule(roomContext),
        CModule()
    )

    override val modules: List<Module>
        get() = _modules

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

}
