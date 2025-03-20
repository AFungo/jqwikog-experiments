package experiments.randoopTest;


import datastructure.pila.PilaSobreListasEnlazadas;
import net.jqwik.api.*;
import net.jqwik.api.constraints.*;

import net.jqwik.api.randoop.UseMethods;
import org.assertj.core.api.*;

public class PilasTest {
	@Property(tries=100)
	void pilaSizeTest(
		@ForAll @IntRange(min = 110,max = 120) @UseMethods(methods = {"push"}) PilaSobreListasEnlazadas stack) {
		System.out.println(stack);
		Assume.that(stack.length() > 4);
		int previousSize = stack.length();
		stack.pop();
		Assertions.assertThat(stack.length()).isEqualTo(previousSize-1);
	}
}
