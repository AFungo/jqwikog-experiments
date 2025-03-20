package experiments.randoopTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import datastructure.queue.BoundedQueue;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.randoop.UseMethods;


class BoundedQueuePropertyBasedTests {

	  
	  @Property(tries = 20)
	  void boundedQueueProperty(@UseMethods(methods = {"enqueue"}) @ForAll @IntRange(min = 90, max = 150) BoundedQueue queue) {
		  
		 System.out.println(queue.toString()); 
		 Assume.that(!queue.isFull());
		 
	   	 int oldSize = queue.size();
	     queue.enqueue(2);
	     assertThat(queue.size(),is(oldSize+1));
		 
	  }
	  
	  
	  
	
	
	
	

}
