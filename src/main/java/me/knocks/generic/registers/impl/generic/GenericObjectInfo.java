package me.knocks.generic.registers.impl.generic;

import me.knocks.generic.registers.interfaces.IObjectInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Generic implementation of external object information.
 */
public class GenericObjectInfo<ObjectType> implements IObjectInfo<ObjectType> {
    protected Map<String, Object> objectAttributes = new HashMap<String, Object>();
    protected final ObjectType infoObject;

    public GenericObjectInfo(ObjectType obj) {
        this.infoObject = obj;
    }

    @Override
    public ObjectType getObject() {
        return infoObject;
    }

    @Override
    public Object getAttribute(String key) {
        return objectAttributes.get(key);
    }

    @Override
    public void setAttribute(String key, Object obj) {
        objectAttributes.put(key, obj);
    }

    @Override
    public void removeAttribute(String key) {
        objectAttributes.remove(key);
    }
}
