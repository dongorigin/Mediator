package cn.dong.mediator

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

/**
 * @author zhaodong on 2020/08/11.
 */
class ModuleContainer(
    lifecycle: Lifecycle,
    val manifest: ModuleManifest,
    val layoutManager: ILayoutManager
) : DefaultLifecycleObserver {

    init {
        lifecycle.addObserver(this)
    }

    fun setup() {
        // todo 布局？
        for (module in manifest.modules) {
            module.setup(layoutManager)
        }
    }

    override fun onStart(owner: LifecycleOwner) {
        for (module in manifest.modules) {
            module.onStart()
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        for (module in manifest.modules) {
            module.onStop()
        }
    }

    /** 为保证在下一个 Activity 启动前执行，在 Activity finish 时执行，而不是在 onDestroy 时执行 */
    fun destroy() {
        for (module in manifest.modules) {
            module.onDestroy()
        }
    }
}
