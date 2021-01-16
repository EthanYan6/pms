package com.buaa.pms.util;

import java.sql.Timestamp;
import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.object.AbstractObjectMorpher;

public class TimestampMorpher extends AbstractObjectMorpher {

    public Object morph(Object value) {
        if (value == null) {
            return null;
        }
        if (Long.class.isAssignableFrom(value.getClass())) {
            return new Timestamp((Long) value);
        }
        if (Timestamp.class.isAssignableFrom(value.getClass())) {
            return (Timestamp) value;
        }
        if (!supports(value.getClass())) {
            throw new MorphException(value.getClass() + " 是不支持的类型");
        }
        return null;
    }

    @Override
    public Class morphsTo() {
        return Timestamp.class;
    }

    public boolean supports(Class clazz) {
        return Long.class.isAssignableFrom(clazz);
    }

}
