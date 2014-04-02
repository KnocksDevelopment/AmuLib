package me.knocks.amulib.utils.structures.tree.iteration;

import me.knocks.amulib.utils.structures.tree.generic.INode;

import java.util.Iterator;

/**
 * General purpose iteration for nodes.
 * @param <T>
 */
public class NodeIterator<T> implements Iterator<INode<T>> {

    protected final INode<T> treeNode;
    protected IterationStage currentStage;
    protected INode<T> next;
    protected Iterator<INode<T>> currentChildNodeIterator;
    protected Iterator<INode<T>> childSubNodeIterator;

    public NodeIterator(INode<T> treeNode) {
        this.treeNode = treeNode;
        this.currentStage = IterationStage.ON_PARENT;
        this.currentChildNodeIterator = treeNode.getChildList().iterator();
    }


    @Override
    public boolean hasNext() {
        // set up stage for iterating on root node's children
        if (currentStage.equals(IterationStage.ON_PARENT)) {
            this.next = this.treeNode;
            currentStage = IterationStage.ON_CHILD;
            return true;
        }
        if (currentStage.equals(IterationStage.ON_CHILD)) {
            if (currentChildNodeIterator.hasNext()) {
                // set up the next child for iteration on the sub-nodes
                INode<T> nextChild = currentChildNodeIterator.next();
                childSubNodeIterator = nextChild.iterator();
                currentStage = IterationStage.ON_CHILD_SUB;
                return hasNext();
            } else {
                currentStage = null;
                return false;
            }
        }
        if (currentStage.equals(IterationStage.ON_CHILD_SUB)) {
            if (childSubNodeIterator.hasNext()) {
                // set up next sub node for iteration
                next = childSubNodeIterator.next();
                return true;
            } else {
                // we don't have anything else to iterate in the sub-nodes, so move on to the next child of the parent
                this.next = null;
                this.currentStage = IterationStage.ON_CHILD;
                return hasNext();
            }
        }
        return false;
    }

    public IterationStage getCurrentStage() {
        return currentStage;
    }

    @Override
    public INode<T> next() {
        return this.next;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
