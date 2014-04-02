package me.knocks.amulib.registers.interfaces;


public interface IObjectInfo<ObjectType> {
    ObjectType getObject();
    Object getAttribute(String key);
    void setAttribute(String key, Object obj);
    void removeAttribute(String key);
}
