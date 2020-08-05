package cn.dong.mediator.modulec;

import cn.dong.mediator.annotation.ModuleService;
import cn.dong.mediator.modulea.AService;
import cn.dong.mediator.moduleb.AudioRecorderProvider;

/**
 * @author zhaodong on 2020/07/08.
 */
public class CModule {

    // Field
    @ModuleService
    private AService aService;

    // getter method
    @ModuleService
    private AudioRecorderProvider getAudioRecorderProvider() {
        return null;
    }
}
