package me.knocks.generic.utils.ui.pagination;

import me.knocks.generic.manager.GenericManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A generic pagination utility for use in things like command pages, etc
 */
public class Paginator<Type> extends GenericManager<PageViewCondition> {

    protected final List<Type> contained;

    public Paginator(List<Type> list) {
        super(PageViewCondition.class);
        this.contained = list;
    }

    public synchronized List<Type> getPage(int page, int pageSize) {
        List<Type> result = new ArrayList<Type>();
        int pageMin = page * pageSize;
        pageMin = Math.max(0, Math.min(pageMin, contained.size() + pageSize));
        int pageMax = Math.min(pageMin + pageSize, contained.size());
        for (int i = pageMin; i < pageMax; i++) {
            Type t = contained.get(i);
            if (!shouldView(t))
                continue;
            result.add(t);
        }
        return result;
    }

    public synchronized int getMaxPages(int pageSize) {
        int ctr = 0;
        for (Type t : contained) {
            if (!shouldView(t))
                continue;
            ctr++;
        }
        return (int)Math.ceil((double)ctr / (double)pageSize);
    }

    public synchronized boolean shouldView(Type obj) {
        if (obj == null)
            return false;
        for (PageViewCondition t : getContents()) {
            if (!t.shouldView(obj))
                return false;
        }
        return true;
    }
}
