package me.knocks.amulib.registers.interfaces;

import java.util.List;


public interface IAccount<ObjectType> {
    IObjectInfo<ObjectType>[] getAllContents();
    List<IObjectInfo<ObjectType>> getAllContentsList();
    IObjectInfo<ObjectType>[] getContentsWithAttribute(String attr);
    IObjectInfo<ObjectType>[] getContentsWithoutAttribute(String attr);
    IObjectInfo<ObjectType>[] getContentsWhereAttributeIs(String attr, Object val);
    IObjectInfo<ObjectType> addObject(ObjectType type);
    void removeObject(IObjectInfo<ObjectType> objInfo);
    void removeInfoWithObject(ObjectType type);
}
