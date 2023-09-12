package queue;

public class EmptyQueue extends QueueState{
    
    @Override
    public Object take() {
        throw new Error ("Queue is empty");
    }

    @Override
    public Object head() {
    	throw new Error ("Queue is empty");
    }
}
