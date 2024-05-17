package com.li.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LiXL
 * @date 2024/3/12
 */
public class WebSiteFactory {

    public final Map<String, WebSite> pool = new HashMap<>();

    public WebSite getWebSiteCategory(String type) {
        if (!pool.containsKey(type)) {
            pool.put(type, new ConcreteWebSite(type));
        }
        return pool.get(type);
    }

    public int getWebSiteSize() {
        return pool.size();
    }
}
