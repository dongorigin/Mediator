package cn.dong.mediator

/**
 * @author zhaodong on 2020/08/11.
 */
interface Module : ModuleLifecycleObserver {

    /** 只执行一次 */
    fun setup(layoutManager: ILayoutManager)

}

/** 容器有自己的生命周期，不一定与外部一致（目前是 Activity），比如 onDestroy 会比 Activity 提前 */
interface ModuleLifecycleObserver {

    fun onServiceReady() {}

    fun onStart() {}

    fun onStop() {}

    fun onDestroy() {}
}
