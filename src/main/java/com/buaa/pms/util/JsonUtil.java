package com.buaa.pms.util;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

public class JsonUtil {
    public static Object jsonToBean(JSONObject jsonObject,Class cls) {
        JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher());
        return JSONObject.toBean(jsonObject, cls);
    }
}
