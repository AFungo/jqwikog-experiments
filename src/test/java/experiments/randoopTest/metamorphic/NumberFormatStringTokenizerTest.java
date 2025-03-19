package experiments.randoopTest.metamorphic;

import epa.*;

import net.jqwik.api.Assume;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.randoop.AssumeMethod;

import net.jqwik.api.randoop.RandoopStrings;
import net.jqwik.api.randoop.UseMethods;
import org.assertj.core.api.Assertions;
import randoop.com.google.gson.Gson;


public class NumberFormatStringTokenizerTest {

	public static boolean EPAPrecondition(Object o){
		if(o == null){
			return false;
		}
		NumberFormatStringTokenizer itr = (NumberFormatStringTokenizer) o;
		return itr.isNextTokenEnabled() && itr.isLetterOrDigitAheadEnabled()
			&& itr.isHasMoreTokensEnabled() && itr.isNextIsSepEnabled();
	}

	//@UseMethods(methods = {"nextToken", "isLetterOrDigitAhead"})
	@Property
	public void test1(@ForAll @UseMethods(methods = {"nextToken", "isLetterOrDigitAhead"})
						  @RandoopStrings(strings = {"n-e!x*t#T.o[k}e?n", "h-o$l(a", "m!u|n°d,o", ";hel-lo-", "!wo&rl/d", "app--le", "banana"})
						  NumberFormatStringTokenizer tok){
		Assume.that(NumberFormatStringTokenizerTest.EPAPrecondition(tok));
		Gson gson = new Gson();
		NumberFormatStringTokenizer obj2 = gson.fromJson(gson.toJson(tok), NumberFormatStringTokenizer.class);

		tok.hasMoreTokens();
		tok.nextIsSep();
		tok.isLetterOrDigitAhead();
		tok.hasMoreTokens();
		tok.nextToken();

		obj2.hasMoreTokens();
		obj2.isLetterOrDigitAhead();
		obj2.hasMoreTokens();
		obj2.hasMoreTokens();
		obj2.nextToken();

		Assertions.assertThat(obj2).isEqualTo(tok);
	}

	@Property
	public void test2(@ForAll @UseMethods(methods = {"nextToken", "isLetterOrDigitAhead"})
						  @RandoopStrings(strings = {"n-e!x*t#T.o[k}e?n", "h-o$l(a", "m!u|n°d,o", ";hel-lo-", "!wo&rl/d", "app--le", "banana"})
						  NumberFormatStringTokenizer tok){
		Assume.that(NumberFormatStringTokenizerTest.EPAPrecondition(tok));
		Gson gson = new Gson();
		NumberFormatStringTokenizer obj2 = gson.fromJson(gson.toJson(tok), NumberFormatStringTokenizer.class);

		tok.isLetterOrDigitAhead();
		tok.nextToken();

		obj2.isLetterOrDigitAhead();
		obj2.hasMoreTokens();
		obj2.nextToken();
		obj2.isLetterOrDigitAhead();


		Assertions.assertThat(obj2).isEqualTo(tok);
	}
}
