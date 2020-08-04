package cn.dong.mediator.modulec;

import cn.dong.mediator.annotation.ModuleService;
import cn.dong.mediator.modulea.AService;

/**
 * @author zhaodong on 2020/07/08.
 */
public class CModule {

    @ModuleService
    private AService aService;
}
