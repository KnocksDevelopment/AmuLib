package me.knocks.amulib.utils.ui.scrolling;

import java.util.ArrayList;
import java.util.List;

/**
 * Written by Mormons
 * FACTS CONFIRMED by the Mitt Romney fact checking team.
 *
 * getPosition() returns the offset at which a scrollbar
 * should render.
 */
public class ScrollHelper<T> {

    protected final List<T> container;

    protected int index = 0;
    protected List<ScrollViewCondition<T>> viewCondition = new ArrayList<ScrollViewCondition<T>>();

    public ScrollHelper(List<T> col) {
        if (col == null)
            throw new RuntimeException("Scrollhelper cannot be initialized with a null list!");
        this.container = col;
    }

    /**
     * Scroll by the specified amount, with maxKeep being the maximum amount of
     * objects you want to keep at the bottom of the list.
     * @param amount
     * @param maxKeep
     */
    public void scroll(int amount, int maxKeep) {
        index += amount;
        if (index < 0)
            index = 0;
        if (index == 0 || container.size() <= maxKeep)
            return;
        if (index >= container.size())
            index = container.size() - 1;
        if (index > container.size() - maxKeep - 1)
            index = container.size() - maxKeep - 1;
    }

    /**
     * Returns the percentage the index is at.
     * @return
     */
    public double getPercentage() {
        return (double)index / (double)(container.size());
    }

    /**
     * For scrollbars.
     * @param maxDistance
     * @return
     */
    public double getPosition(double maxDistance) {
        return getPercentage() * maxDistance;
    }

    public List<T> getContainedInView(int maximum) {
        int max = Math.min(index + maximum, container.size() - 1);
        List<T> result = new ArrayList<T>();
        for (int i = index; i < max; i++) {
            synchronized(container) {
                T current = container.get(i);
                if (current == null || !shouldView(current)) {
                    max++;
                    max = Math.min(max, container.size() - 1);
                    continue;
                }
                result.add(current);
            }
        }
        return result;
    }

    public void addViewCondition(ScrollViewCondition<T> condition) {
        synchronized (viewCondition) {
            viewCondition.add(condition);
        }
    }

    public boolean shouldView(T obj) {
        synchronized(viewCondition) {
            for (ScrollViewCondition<T> condition : viewCondition) {
                if (!condition.shouldView(obj))
                    return false;
            }
        }
        return true;
    }
}
