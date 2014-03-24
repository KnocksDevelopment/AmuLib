package me.knocks.generic.manager;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

/**
 * A management class designed to be extended by objects that require an internal
 * map for tracking objects.
 */
public class GenericMapManager<KeyType, ValueType> {

    protected Map<KeyType, ValueType> managedMap = new HashMap<KeyType, ValueType>();

    protected final Class<KeyType> managedKeyType;
    protected final Class<ValueType> managedValueType;

    public GenericMapManager(Class<KeyType> keyType, Class<ValueType> valueType) {
        this.managedKeyType = keyType;
        this.managedValueType = valueType;
    }

    public void put(KeyType k, ValueType v) {
        managedMap.put(k, v);
    }

    public ValueType get(KeyType k) {
        return managedMap.get(k);
    }

    public ValueType[] getValues() {
        ValueType[] values = (ValueType[]) Array.newInstance(managedValueType, managedMap.values().size());
        for (int i = 0; i < managedMap.values().size(); i++)
            values[i] = (ValueType)managedMap.values().toArray()[i];
        return values;
    }

    public KeyType[] getKeys() {
        KeyType[] keys = (KeyType[])Array.newInstance(managedKeyType, managedMap.keySet().size());
        for (int i = 0; i < managedMap.keySet().size(); i++)
            keys[i] = (KeyType)managedMap.keySet().toArray()[i];
        return keys;
    }
}
