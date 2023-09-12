package queue;

public class NonEmptyQueue extends QueueState{
	
	public Object object;
	
	public NonEmptyQueue (Object cargo) {
		object = cargo;
	}

    public Object take() {
        return object;
    }

    public Object head() {
        return object;
    }
   
}
