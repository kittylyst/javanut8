package javanut8.ch11;

import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRefEx {
	
	@Test
	public void testBasic() {
		ReflectionExamples rfx = new ReflectionExamples();
		rfx.run(TestRefEx.class);
	}
	
	@Deprecated
	public String badTestString() {
		return super.toString();
	}

	private static class TestMap extends HashMap<String, String> {
		
	}
	
	@Test
	public void testCommonAncestor() {
		assertEquals(null, ReflectionExamples.commonAncestor(null, null));
		assertEquals(null, ReflectionExamples.commonAncestor(null, String.class));
		
		assertEquals(null, ReflectionExamples.commonAncestor(int.class, String.class));
		assertEquals(null, ReflectionExamples.commonAncestor(int.class, long.class));
		
		assertEquals(String.class, ReflectionExamples.commonAncestor(String.class, String.class));
		assertEquals(Object.class, ReflectionExamples.commonAncestor(Object.class, String.class));
		
		assertEquals(AbstractMap.class, ReflectionExamples.commonAncestor(ConcurrentHashMap.class, HashMap.class));
		assertEquals(AbstractMap.class, ReflectionExamples.commonAncestor(ConcurrentHashMap.class, TestMap.class));
	}
	
}
