package cn.dong.mediator.manifest

import cn.dong.mediator.Module
import cn.dong.mediator.ModuleManifest
import cn.dong.mediator.RoomContext

/**
 * @author zhaodong on 2020/08/11.
 */
class BManifest(roomContext: RoomContext) : ModuleManifest {
    override val modules: List<Module>
        get() = emptyList()

    override fun getLayoutId(): Int? = null
}
