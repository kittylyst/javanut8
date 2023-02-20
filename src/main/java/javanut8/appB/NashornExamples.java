package javanut8.appB;

import javax.script.*;

public class NashornExamples {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NashornExamples nex = new NashornExamples();
//		nex.run();
		nex.run2();
	}

	private void run2() {
		ScriptEngineManager m = new ScriptEngineManager();
		ScriptEngine e = m.getEngineByName("nashorn");

		try {
		  e.eval("var i = 1;");
	      e.eval("var f = function () { " +
	      		"var j = 0;" +
	      		"print(i + j); " +
	      		"return function() {j++;}; };");
	      e.eval("print(f()());");
		} catch (final ScriptException se) {
		  System.out.println(se.toString());
		}
		e.getContext().setBindings(new SimpleBindings(), 101);
		System.out.println(e.getContext().getScopes());
	}

	private void run() {
		ScriptEngineManager m = new ScriptEngineManager();
		ScriptEngine e = m.getEngineByName("nashorn");

		try {
		  e.eval("print('Hello World!');");
		  e.eval("var i = 27;");
		  e.put("j", 15);
		  e.eval("var z = i + j;");
		} catch (final ScriptException se) {
		  // ...
		}
		int theAnswer = ((Number) e.get("z")).intValue();
		System.out.println("The Answer: "+ theAnswer);
	}

}
