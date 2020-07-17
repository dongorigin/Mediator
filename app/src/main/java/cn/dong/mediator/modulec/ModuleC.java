package cn.dong.mediator.modulec;

import cn.dong.mediator.annotation.ModuleService;
import cn.dong.mediator.modulea.ModuleA;

/**
 * @author zhaodong on 2020/07/08.
 */
public class ModuleC {

    @ModuleService
    private ModuleA moduleA;
}
