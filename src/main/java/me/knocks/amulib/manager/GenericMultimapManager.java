package me.knocks.amulib.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A management class designed for use with things like keybind management in LWJGL games,
 * with a mapping of Integer to a list of keybinds.
 */
public class GenericMultimapManager<KeyType, ValueType> extends GenericMapManager<KeyType, List> {

    public GenericMultimapManager(Class<KeyType> keyType) {
        super(keyType, List.class);
    }

    public void putListValue(KeyType k, ValueType v) {
        List<ValueType> lists = get(k);
        if (lists == null)
            lists = new ArrayList<ValueType>();
        lists.add(v);
        put(k, lists);
    }

    /** Modified to always return an empty list if the list is null */
    @Override
    public List<ValueType> get(KeyType k) {
        List<ValueType> list = (List<ValueType>)super.get(k);
        if (list == null) {
            list = new CopyOnWriteArrayList<ValueType>();
            put(k, list);
        }
        return list;
    }
}
