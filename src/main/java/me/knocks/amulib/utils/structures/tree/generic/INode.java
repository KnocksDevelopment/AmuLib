package me.knocks.amulib.utils.structures.tree.generic;

import java.util.List;

/**
 * Generic "INode" interface.
 */
public interface INode<T> extends Iterable<INode<T>>{
    boolean isRoot();
    boolean isLeaf();
    INode<T> getParent();
    void setParent(INode<T> n);
    List<INode<T>> getChildList();
    INode<T>[] getChildren();
    INode<T> addChild(T node);
    void addChild(INode<T> node);
    void removeChild(INode<T> node);
    T getData();
    int getLevel();
}
