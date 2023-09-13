package queue;

public class NonEmptyQueue extends QueueState{
    public Object object;
	
    public NonEmptyQueue (Object cargo) {
        object = cargo;
    }
	
    @Override
    public Object take() {
        return object;
    }
    
    @Override
    public Object head() {
        return object;
    }
}
