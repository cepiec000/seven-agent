package com.seven.agent.plugin;

import com.seven.agent.plugin.jdbc.PreparedStatementExecutePlugin;
import com.seven.agent.plugin.link.LinkPlugin;
import com.seven.agent.plugin.logger.LoggerPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Author chendongdong
 * @Date 2019/9/25 10:19
 * @Version V1.0 拦截
 **/
public class PluginLoader {
    private static List<AbstractPlugin> pluginList = new ArrayList<>(16);

    public static List<AbstractPlugin> loadPlugins() {
        if (pluginList == null || pluginList.size() == 0) {
            pluginList.add(new LoggerPlugin());
            pluginList.add(new LinkPlugin());
            pluginList.add(new PreparedStatementExecutePlugin());
        }
        return pluginList;
    }

}
