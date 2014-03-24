package me.knocks.generic.utils.ui.pagination;

/**
 * An interface to be implemented by objects that wish to control what objects are viewed
 * by the Paginator at what times.
 */
public interface PageViewCondition<T> {
    public boolean shouldView(T object);
}
