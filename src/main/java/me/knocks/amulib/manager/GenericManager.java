package me.knocks.amulib.manager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Generic manager class designed to be extended by things like module managers
 * and other objects that do not need to extend a list.
 */
public class GenericManager<Type> {

    protected List<Type> managedList = new ArrayList<Type>();

    protected final Class<Type> managedType;

    public GenericManager(Class<Type> managedType) {
        this.managedType = managedType;
    }

    public final Class<Type> getManagedType() {
        return managedType;
    }

    public void add(Type t) {
        managedList.add(t);
    }

    public void remove(Type t) {
        managedList.remove(t);
    }

    public Type[] getContents() {
        Type[] result = (Type[]) Array.newInstance(managedType, managedList.size());
        for (int i = 0; i < managedList.size(); i++) {
            result[i] = managedList.get(i);
        }
        return result;
    }

    public <T extends Type> T getContainedByClass(Class<T> classType) {
        for (Type t : managedList) {
            if (t.getClass().equals(classType))
                return (T)t;
        }
        return null;
    }

    public List<Type> getContentsList() {
        return Collections.unmodifiableList(managedList);
    }

    public void clear() {
        managedList.clear();
    }
}
