package queue;

public class EmptyQueue extends QueueState{
	
	public static final String ErrorMessage_QueueIsEmpty = "Queue is empty";

	@Override
    public Object take() {
        throw new Error (ErrorMessage_QueueIsEmpty);
    }
	
	@Override
    public Object head() {
    	throw new Error (ErrorMessage_QueueIsEmpty);
    }
}
