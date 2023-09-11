package queue;

public abstract class QueueState {
    public abstract boolean isEmpty();
    public abstract QueueState add(Object cargo);
    public abstract Object take();
    public abstract Object head();
    public abstract int size();
}