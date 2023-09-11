package queue;

public class Queue {
    private QueueState queueState;

    public Queue() {
        queueState = new EmptyQueue();
    }

    public boolean isEmpty() {
        return queueState.isEmpty();
    }

    public Queue add(Object cargo) {
        queueState = queueState.add(cargo);
        return this;
    }

    public Object take() {
        return queueState.take();
    }

    public Object head() {
        return queueState.head();
    }

    public int size() {
        return queueState.size();
    }
}