package cn.dong.mediator.moduleholder;

import cn.dong.mediator.RoomContext;
import cn.dong.mediator.annotation.Module;
import cn.dong.mediator.module.modulea.AModule;
import cn.dong.mediator.module.moduleb.EngineModule;

/**
 * Java 的字段可以被子类继承到，但 Kotlin 的属性则不行，只继承到了属性的 getter 和 setter
 *
 * @author zhaodong on 2020/08/04.
 */
public class JavaBaseModuleHolder {

    @Module
    public AModule moduleA = new AModule();

    @Module
    public EngineModule moduleB = new EngineModule();
}
