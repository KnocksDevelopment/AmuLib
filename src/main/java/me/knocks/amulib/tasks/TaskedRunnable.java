package me.knocks.amulib.tasks;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * A runnable that can have objects submitted to it for handling upon the next call
 * of run().
 * the @interface InternalTask is used to handle tasks internally.
 */
public class TaskedRunnable implements Runnable {

    protected final Map<Class<?>, Method> associatedMethods = new HashMap<Class<?>, Method>();
    protected final Queue<Object> queuedObjects = new LinkedList<Object>();

    public TaskedRunnable() {
        loadHandlers();
    }

    @Override
    public void run() {
        while (queuedObjects.size() > 0) {
            handleObject(queuedObjects.element());
        }
    }

    protected final void loadHandlers() {
        for (Method m : this.getClass().getMethods()) {
            InternalTask itask = m.getAnnotation(InternalTask.class);
            if (itask == null)
                continue;
            Class<?> handledClass = itask.getType();
            if (handledClass == null)
                continue;
            if (m.getTypeParameters().length > 1 || m.getTypeParameters().length < 1)
                continue;
            if (!m.getTypeParameters()[0].getClass().equals(handledClass))
                continue;
            this.associatedMethods.put(handledClass, m);
        }
    }

    protected void handleObject(Object obj) {
        if (obj == null)
            return;
        if (associatedMethods.get(obj.getClass()) == null)
            return;
        try {
            associatedMethods.get(obj.getClass()).invoke(this, obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void submit(Object obj) {
        if (obj == null)
            throw new NullPointerException("obj");
        queuedObjects.add(obj);
    }

}
