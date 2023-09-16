package queue;

public class EmptyQueue extends QueueState{
	
    public static final String errorMessage_QueueIsEmpty = "Queue is empty";

    @Override
    public Object take() {
        throw new Error (errorMessage_QueueIsEmpty);
    }
	
    @Override
    public Object head() {
        throw new Error (errorMessage_QueueIsEmpty);
    }
}
