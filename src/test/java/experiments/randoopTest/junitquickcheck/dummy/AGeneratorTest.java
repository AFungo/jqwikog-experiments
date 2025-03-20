package experiments.randoopTest.junitquickcheck.dummy;


import junitquickcheck.dummy.*;

import net.jqwik.api.*;

import net.jqwik.api.randoop.*;

import org.assertj.core.api.*;

import java.util.*;

public class AGeneratorTest {

	public static boolean listOfBNotNull(Object o){
		if(o == null)
			return false;
		A a = (A) o;
		return a.getListOfB() != null;
	}

    @Property(tries=100)
	public void listAreCorrectlyGenerated(@ForAll @Deps(classes = {B.class, ArrayList.class}) @UseMethods(methods = {"setListOfB"}) A a) {
		Assume.that(AGeneratorTest.listOfBNotNull(a));
        a.getListOfB().forEach(b ->
								   Assertions.assertThat(b).isInstanceOf(B.class));
    }
}
