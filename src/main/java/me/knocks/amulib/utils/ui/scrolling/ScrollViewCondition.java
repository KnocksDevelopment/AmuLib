package me.knocks.amulib.utils.ui.scrolling;

/**
 * Allows you to set up conditions where a scrolled item is not shown.
 */
public interface ScrollViewCondition<T> {
    public boolean shouldView(T object);
}
