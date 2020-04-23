package com.seven.agent.plugin;

import com.seven.agent.bean.FieldDefine;

/**
 * @Description: TODO
 * @Author chendongdong
 * @Date 2019/9/25 10:17
 * @Version V1.0
 **/
public abstract class AbstractPlugin implements IPlugin {
    public FieldDefine[] buildFieldDefine(){
        return null;
    }
}
