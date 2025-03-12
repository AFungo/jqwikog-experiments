package experiments.randoopTest;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;

import net.jqwik.api.randoop.*;

import org.junit.jupiter.api.*;

import java.util.*;
import java.util.Stack;

public class RandoopTest {

	@Property(tries = 100)
	void listTest(@ForAll @Size(max=5) List<@IntRange(min = 1,max = 10) Integer> list) {
		Assume.that(!list.isEmpty());
		int previousSize = list.size();
		System.out.println(list);
		list.remove(0);
		Assertions.assertEquals(list.size(), previousSize-1);
	}

	@Property(tries = 10)
	void stackSizeTest(@ForAll Stack<Date> stack) {
		Assume.that(!stack.isEmpty());
		int previousSize = stack.size();
		stack.peek();
		Assertions.assertEquals(stack.size(), previousSize);
	}

	@Property(tries = 10)
	void pilaTest(@ForAll @RandoopStrings(strings = {"hola", "chau", "mundo"}) ArrayList<String> stack) {
		Assume.that(!stack.isEmpty());
		System.out.println(stack);
		// int previousSize = stack.size();
		// stack.peek();
		// Assertions.assertEquals(stack.size(), previousSize);
	}

	@Property(tries = 10)
	void hashMapTest(@ForAll TreeMap<String, Integer> map) {
		Assume.that(!map.isEmpty());
		System.out.println(map);
		// int previousSize = stack.size();
		// stack.peek();
		// Assertions.assertEquals(stack.size(), previousSize);
	}



	@Property(tries = 100)
	void bitSetClearTest(@ForAll BitSet set) {
		Assume.that(!set.isEmpty());
		System.out.println(set);
		set.clear();
		Assertions.assertTrue(set.isEmpty());
	}

	@Property(tries = 100)
	void priorityQueueTest(@ForAll PriorityQueue<Date> pq) {
		System.out.println(pq);
		pq.clear();
		Assertions.assertTrue(pq.isEmpty());
	}

	@Property(tries = 100)
	void treeSetTest(@ForAll TreeSet<Date> ts) {
		System.out.println(ts);
		ts.clear();
		Assertions.assertTrue(ts.isEmpty());
	}


	@Property(tries = 100)
	void localeTest(@ForAll Locale locale) {
		System.out.println(locale);
		Assertions.assertFalse(locale.getLanguage().isEmpty());
	}
}
