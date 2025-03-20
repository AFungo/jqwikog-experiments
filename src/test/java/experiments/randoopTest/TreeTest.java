package experiments.randoopTest;

import examples.datastructure.trees.*;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;
import net.jqwik.api.randoop.*;

import org.assertj.core.api.*;

import java.util.*;

/*
 *Experimentos extraidos de JQF
 * https://rohan.padhye.org/files/jqf-issta19.pdf
 */
public class TreeTest {

	public static boolean noElementsFrom0to100InTree(Object o){
		BinaryTree b = (BinaryTree) o;
		for(int i = 0; i <= 100; i++){
			if(b.contains(i)){
				return false;
			}
		}
		return true;
	}

	@Property(tries=100)
	public void treeContainsTest(
		@ForAll @UseMethods(methods = {"insert"}) @IntRange(min = 200, max = 300)
		BinaryTree b,
		@ForAll @Size(max=100) Set<@IntRange(min=0, max=100) Integer> elements,
		@ForAll @Size(max=100) Set<@IntRange(min=0, max=100) Integer> queries
	) {
		Assume.that(TreeTest.noElementsFrom0to100InTree(b));
		for (Integer e : elements) {
			b.insert(e);
		}

		for (int q : queries) {
			Assertions.assertThat(elements.contains(q)).isEqualTo(b.contains(q));
		}
	}

}
