package me.knocks.amulib.tuple;

/**
 * Generic Tuple interface
 */
public interface ITuple<T, K> {
    public T getPairedFirst();
    public K getPairedSecond();
}
