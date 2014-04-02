package me.knocks.amulib.utils.structures.tree;

import me.knocks.amulib.utils.structures.tree.generic.INode;
import me.knocks.amulib.utils.structures.tree.iteration.NodeIterator;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GenericNode<T> implements INode<T>, Iterable<INode<T>> {

    protected T data;
    protected INode<T> parent;
    protected List<INode<T>> children;
    protected List<INode<T>> elementsIndex;

    public GenericNode(T data) {
        this.data = data;
        this.children = new LinkedList<INode<T>>();
        this.elementsIndex = new LinkedList<INode<T>>();
        this.elementsIndex.add(this);
    }

    @Override
    public Iterator<INode<T>> iterator() {
        return new NodeIterator<T>(this);
    }

    @Override
    public boolean isRoot() {
        return parent == null;
    }

    @Override
    public boolean isLeaf() {
        return children.size() == 0;
    }

    @Override
    public INode<T> getParent() {
        return parent;
    }

    @Override
    public void setParent(INode<T> n) {
        this.parent = n;
    }

    @Override
    public List<INode<T>> getChildList() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public INode<T>[] getChildren() {
        return children.toArray(new INode[children.size()]);
    }

    @Override
    public INode<T> addChild(T node) {
        GenericNode<T> child = new GenericNode<T>(node);
        children.add(child);
        return child;
    }

    @Override
    public void addChild(INode<T> node) {
        children.add(node);
    }

    @Override
    public void removeChild(INode<T> node) {
        children.remove(node);
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public int getLevel() {
        return isRoot() ? 0 : parent.getLevel() + 1;
    }
}
