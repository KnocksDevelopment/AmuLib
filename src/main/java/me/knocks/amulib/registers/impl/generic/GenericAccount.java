package me.knocks.amulib.registers.impl.generic;


import me.knocks.amulib.registers.interfaces.IAccount;
import me.knocks.amulib.registers.interfaces.IObjectInfo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Generic implementation of an account.
 */
public class GenericAccount<ObjectType> implements IAccount<ObjectType> {
    protected List<IObjectInfo<ObjectType>> objectList = new CopyOnWriteArrayList<IObjectInfo<ObjectType>>();


    @Override
    public IObjectInfo<ObjectType>[] getAllContents() {
        IObjectInfo<ObjectType>[] array = (IObjectInfo<ObjectType>[]) Array.newInstance(
                GenericObjectInfo.class, objectList.size());
        return objectList.toArray(array);
    }

    @Override
    public List<IObjectInfo<ObjectType>> getAllContentsList() {
        return Collections.unmodifiableList(objectList);
    }

    @Override
    public IObjectInfo<ObjectType>[] getContentsWithAttribute(String attr) {
        List<IObjectInfo> returnList = new ArrayList<IObjectInfo>();
        for (IObjectInfo<ObjectType> info : objectList) {
            if (info.getAttribute(attr) != null)
                returnList.add(info);
        }
        IObjectInfo<ObjectType>[] array = (IObjectInfo<ObjectType>[]) Array.newInstance(
                GenericObjectInfo.class, returnList.size());
        return returnList.toArray(array);
    }

    @Override
    public IObjectInfo<ObjectType>[] getContentsWithoutAttribute(String attr) {
        List<IObjectInfo> returnList = new ArrayList<IObjectInfo>();
        for (IObjectInfo<ObjectType> info : objectList) {
            if (info.getAttribute(attr) == null)
                returnList.add(info);
        }
        IObjectInfo<ObjectType>[] array = (IObjectInfo<ObjectType>[]) Array.newInstance(
                GenericObjectInfo.class, returnList.size());
        return returnList.toArray(array);
    }

    @Override
    public IObjectInfo<ObjectType>[] getContentsWhereAttributeIs(String attr, Object val) {
        List<IObjectInfo> returnList = new ArrayList<IObjectInfo>();
        for (IObjectInfo<ObjectType> info : objectList) {
            if (info.getAttribute(attr) != null && info.getAttribute(attr).equals(val))
                returnList.add(info);
        }
        IObjectInfo<ObjectType>[] array = (IObjectInfo<ObjectType>[]) Array.newInstance(
                GenericObjectInfo.class, returnList.size());
        return returnList.toArray(array);
    }

    @Override
    public IObjectInfo<ObjectType> addObject(ObjectType objectType) {
        IObjectInfo<ObjectType> returnVal = new GenericObjectInfo<ObjectType>(objectType);
        objectList.add(returnVal);
        return returnVal;
    }

    @Override
    public void removeObject(IObjectInfo<ObjectType> objInfo) {
        objectList.remove(objInfo);
    }

    @Override
    public void removeInfoWithObject(ObjectType objectType) {
        for (IObjectInfo<ObjectType> info : objectList)
            if (info.getObject().equals(objectType))
                objectList.remove(info);
    }
}
