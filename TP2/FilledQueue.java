package queue;

import java.util.ArrayList;
import java.util.List;

public class FilledQueue extends QueueState {

	public List<Object> objects = new ArrayList<>();

	public FilledQueue () {
		objects.add(new EmptyQueue());
	}

   @Override
   public boolean isEmpty() {
       return false;
   }

   @Override
   public QueueState add(Object cargo) {
   		objects.add(cargo);
   		return this;
   }

   @Override
   public Object take() {
        return objects.remove(0);
   }

   @Override
   public Object head() {
        return objects.get(0);
   }

   @Override
   public int size() {
        return objects.size() - 1;
   }
}