package experiments.randoopTest.metamorphic;

import epa.*;

import net.jqwik.api.Assume;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.randoop.AssumeMethod;
import net.jqwik.api.randoop.UseMethods;
import org.assertj.core.api.Assertions;
import randoop.com.google.gson.Gson;
import randoop.com.google.gson.reflect.*;

import java.lang.reflect.*;

public class StackTest {

	public static boolean EPAPrecondition(Object o){
		if(o == null){
			return false;
		}
		Stack s = (Stack) o;
		return s.isGetEnabled() && s.isSetEnabled() && s.isRemove2Enabled();
	}

	@Property(tries=100)
	public void test1(@ForAll @UseMethods(methods = {"addElement"}) @IntRange(min = 0, max = 25) Stack<Integer> s){
		Assume.that(StackTest.EPAPrecondition(s));
		Gson gson = new Gson();
		Type stackType = new TypeToken<Stack<Integer>>() {}.getType();
		Stack<Integer> obj2 = gson.fromJson(gson.toJson(s), stackType);

		s.isEmpty();

		obj2.insertElementAt(1,0);
		obj2.lastElement();
		obj2.set(0,1);
		obj2.remove(0);

		Assertions.assertThat(obj2).isEqualTo(s);

	}


}
