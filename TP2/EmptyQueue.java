package queue;

public class EmptyQueue extends QueueState {
    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public QueueState add(Object cargo) {
        return new FilledQueue().add(cargo);
    }

    @Override
    public Object take() {
        throw new Error("Queue is empty");
    }

    @Override
    public Object head() {
        throw new Error("Queue is empty");
    }

    @Override
    public int size() {
        return 0;
    }
}