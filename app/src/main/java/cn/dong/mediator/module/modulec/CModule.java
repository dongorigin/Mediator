package cn.dong.mediator.module.modulec;

import org.jetbrains.annotations.NotNull;

import cn.dong.mediator.ILayoutManager;
import cn.dong.mediator.Module;
import cn.dong.mediator.annotation.ModuleService;
import cn.dong.mediator.module.modulea.AService;
import cn.dong.mediator.module.moduleb.AudioRecorderProvider;

/**
 * @author zhaodong on 2020/07/08.
 */
public class CModule implements Module {

    // Field
    @ModuleService
    private AService aService;

    // getter method
    @ModuleService
    private AudioRecorderProvider getAudioRecorderProvider() {
        return null;
    }

    @Override
    public void setup(@NotNull ILayoutManager layoutManager) {

    }

    @Override
    public void onServiceReady() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }
}
