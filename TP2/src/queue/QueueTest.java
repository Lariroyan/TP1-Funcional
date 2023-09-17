package queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class QueueTest {
	
  String objectSomething = "Something";
  String firstAddedObject = "First";
  String secondAddedObject = "Second";
  
	
  @Test public void test01QueueShouldBeEmptyWhenCreated() {
    assertTrue( new Queue().isEmpty() );
  }

  @Test public void test02AddElementsToTheQueue() {
    assertFalse( queueWithSomething().isEmpty() );
  }
  
  @Test public void test03AddedElementsIsAtHead() {
    assertEquals( objectSomething, queueWithSomething().head() );
  }

  @Test public void test04TakeRemovesElementsFromTheQueue() {
    Queue queue = queueWithSomething();
    queue.take();
    
    assertTrue( queue.isEmpty() );
  }

  @Test public void test05TakeReturnsLastAddedObject() {
	Queue queue = queueWithSomething();
    
    assertEquals( objectSomething, queue.take() );
  }

  @Test public void test06QueueBehavesFIFO() {
    Queue queue = queueWithTwoObjects();

    assertEquals( queue.take(), firstAddedObject );
    assertEquals( queue.take(), secondAddedObject );
    assertTrue( queue.isEmpty() );
  }

  @Test public void test07HeadReturnsFirstAddedObject() {
    Queue queue = queueWithTwoObjects();

    assertEquals( queue.head(), firstAddedObject );
  }

  @Test public void test08HeadDoesNotRemoveObjectFromQueue() {
    Queue queue = queueWithSomething();
    
    assertEquals( 1, queue.size() );
    queue.head();
    assertEquals( 1, queue.size() );
  }

  @Test public void test09SizeRepresentsObjectInTheQueue() {
    assertEquals( 2, queueWithTwoObjects().size() );
  }

  @Test public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() {
    assertThrowsLike( () -> new Queue().take(), EmptyQueue.errorMessage_QueueIsEmpty );
  }

  @Test public void test09CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
    Queue queue = queueWithSomething();
    queue.take();
    
    assertThrowsLike( () -> queue.take(), EmptyQueue.errorMessage_QueueIsEmpty );
  }

  @Test public void test10CanNotHeadWhenThereAreNoObjectsInTheQueue() {
    assertThrowsLike( () -> new Queue().head(), EmptyQueue.errorMessage_QueueIsEmpty );
  }
  
  

  private Queue queueWithSomething() {
		return new Queue().add( objectSomething );
	}
  
  private Queue queueWithTwoObjects() {
		return new Queue().add( firstAddedObject ).add( secondAddedObject );
	}
  
  private void assertThrowsLike( Executable executable, String message ) {
	  assertEquals( message, assertThrows( Error.class, executable ).getMessage() );
  }
  
}