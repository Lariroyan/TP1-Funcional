package queue;

import java.util.ArrayList;
import java.util.List;

public class Queue {
	
	private List<QueueState> listElements = new ArrayList<>();
	
    public Queue() {
        listElements.add(new EmptyQueue());
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Queue add(Object cargo) {
    	listElements.add(1, new NonEmptyQueue(cargo));
        return this;
    }

    public Object take() {
        return listElements.remove(size()).take();
    }

    public Object head() {
        return listElements.get(size()).head();
    }

    public int size() {
        return listElements.size() - 1;
    }
}
