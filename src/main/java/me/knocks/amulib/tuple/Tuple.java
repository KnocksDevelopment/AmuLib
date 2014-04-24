package me.knocks.amulib.tuple;

/**
 * Created by shoebox on 4/24/2014.
 */
public class Tuple<T, K> implements ITuple<T, K> {

    protected final T pairedFirst;
    protected final K pairedSecond;

    public Tuple(T p1, K p2) {
        this.pairedFirst = p1;
        this.pairedSecond = p2;
    }

    @Override
    public T getPairedFirst() {
        return pairedFirst;
    }

    @Override
    public K getPairedSecond() {
        return pairedSecond;
    }

    @Override
    public int hashCode() {
        if (getPairedFirst() == null || getPairedSecond() == null)
            return 0;
        return getPairedFirst().hashCode() ^ getPairedSecond().hashCode();
    }
}
