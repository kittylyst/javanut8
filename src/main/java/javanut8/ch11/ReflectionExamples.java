package javanut8.ch11;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.channels.Channel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReflectionExamples {
	
	public void run(Class<?> clz) {
		for (Method m : clz.getMethods()) {
			for (Annotation a : m.getAnnotations()) {
				if (a.annotationType() == Deprecated.class) {
					System.out.println(m.getName());
				}
			}
		}
	}

	public static class DiskLoader extends ClassLoader {
		public DiskLoader() {
			super(DiskLoader.class.getClassLoader());
		}
		
		public Class<?> loadFromDisk(String clzPath) throws IOException {
			byte[] b = Files.readAllBytes(Paths.get(clzPath));
			
			return defineClass(null, b, 0, b.length);
		}
	}

	public void run2() {
		Object rcvr = "a";
//		Class<?> clz = String.class;
		try {
//		  Object rcvr = clz.newInstance();
		  Class<?>[] argTypes = new Class[] { };
		  Object[] args = null;
		  
		  Method meth = rcvr.getClass().getMethod("hashCode", argTypes);
		  Object ret = meth.invoke(rcvr, args);
		  System.out.println(ret);

		} catch (IllegalArgumentException | NoSuchMethodException | 
		         SecurityException e) {
		  e.printStackTrace();
		} catch (IllegalAccessException | InvocationTargetException x) {
		  x.printStackTrace();
		}
	}

	public void run3() {
		Object rcvr = "a";
		try {
		  MethodType mt = MethodType.methodType(int.class);			
		  MethodHandles.Lookup l = MethodHandles.lookup();
		  MethodHandle mh = l.findVirtual(rcvr.getClass(), "hashCode", mt);
			
		  int ret;
		  try {
			ret = (int)mh.invoke(rcvr);
			System.out.println(ret);
		  } catch (Throwable t) {
			t.printStackTrace();
		  }

		} catch (IllegalArgumentException | NoSuchMethodException | 
		         SecurityException e) {
		  e.printStackTrace();
		} catch (IllegalAccessException x) {
		  x.printStackTrace();
		}
		
	}

	public void run4() throws ClassNotFoundException, IOException {
		var current = new File( "." ).getCanonicalPath();
		var urls = new URL[] {new URL("file://"+ current + "/")};
		try (URLClassLoader loader = new URLClassLoader(urls)) {
			Class<?> clz = loader.loadClass("scratch.perf.DFACaller");
			System.out.println(clz.getName());
		}
	}
	
	public static Class<?> commonAncestor(Class<?> cl1, Class<?> cl2) {
		if (cl1 == null || cl2 == null) return null;
		if (cl1.equals(cl2)) return cl1;
		if (cl1.isPrimitive() || cl2.isPrimitive()) return null; 
		
		List<Class<?>> ancestors = new ArrayList<>();
		Class<?> c = cl1;
		while (!c.equals(Object.class)) {
			if (c.equals(cl2)) return c;
			ancestors.add(c);
			c = c.getSuperclass();
		}
		c = cl2;
		while (!c.equals(Object.class)) {
			for (Class<?> k : ancestors) {
				if (c.equals(k)) return c;
			}
			c = c.getSuperclass();
		}
		
		return Object.class;
	}
	
	public static class MyCache {
		private void flush() {
			// Flush the cache...
		}
	}
	
	public void run5() {
		Class<?> clz = MyCache.class;
		
		try {
			Object rcvr = clz.getDeclaredConstructor().newInstance();
			Class<?>[] argTypes = new Class[] { };
			Object[] args = null;
			  
			Method meth = clz.getDeclaredMethod("flush", argTypes);
			meth.setAccessible(true);
			meth.invoke(rcvr, args);

		} catch (IllegalArgumentException | NoSuchMethodException | 
				 InstantiationException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException | InvocationTargetException x) {
			x.printStackTrace();
		}
	}
	
	public static class SneakyLoader extends ClassLoader {
		public SneakyLoader() {
			super(SneakyLoader.class.getClassLoader());
		}

		public Lookup getLookup() {
			return MethodHandles.lookup();
		}
	}
	
	public static void lookupDefineClass(Lookup l) {
		MethodType mtdefClz = MethodType.methodType(Class.class, String.class, 
                byte[].class, int.class, 
                int.class); 
		
		try {
			MethodHandle mh = l.findVirtual(ClassLoader.class, "defineClass", mtdefClz);
			System.out.println(mh);
		} catch (NoSuchMethodException | IllegalAccessException e) {
			e.printStackTrace();
		}		
	}
	
	public void run6() {
		Lookup l = MethodHandles.lookup();
		lookupDefineClass(l);
		
		SneakyLoader snLdr = new SneakyLoader();
		l = snLdr.getLookup();
		lookupDefineClass(l);
	}

	public void run7() throws IOException {
		InvocationHandler h = (proxy, method, args) -> {
			String name = method.getName();
			System.out.println("Called as: "+ name);
			return switch (name) {
				case "isOpen" -> Boolean.TRUE;
				case "close" -> null;
				default -> null;
			};
		};
		Channel c = (Channel) Proxy.newProxyInstance(Channel.class.getClassLoader(), new Class[] { Channel.class }, h);
		System.out.println("Open? "+ c.isOpen());
		c.close();
	}

	 public class RememberingList implements InvocationHandler {
		 private final List<String> proxied = new ArrayList<>();		 
		 
		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			String name = method.getName();
			switch (name) {
				case "clear":
					return null;
				case "remove":
				case "removeAll":
					return false;
			}

			return method.invoke(proxied, args);
		}
	}

	
	public void run8() throws IOException {
		RememberingList hList = new RememberingList();
		
		List<String> l = (List<String>) Proxy.newProxyInstance(List.class.getClassLoader(), new Class[] { List.class }, hList);
		l.add("dog");
		l.add("cat");
		l.add("bunny");
		l.clear();
		System.out.println(l);
		
	}

	public void run9() throws Throwable {
		Lookup l = MethodHandles.lookup();
		String cat = "Cat";

		Method hc = Object.class.getMethod("hashCode");
        MethodHandle mh = null;
        try {
            mh = l.in(cat.getClass())
                    .unreflectSpecial(hc, hc.getDeclaringClass())
                    .bindTo(cat);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(cat.hashCode());
        System.out.println((int)mh.invokeWithArguments());
    }

	public void run10() {
		MethodType mtToString = MethodType.methodType(String.class);

		try {
			Lookup lookup = MethodHandles.lookup();
			MethodHandle mh = lookup.findVirtual(String.class, "toString", mtToString);
			System.out.println(mh);
		} catch (NoSuchMethodException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws Throwable {
		ReflectionExamples rfx = new ReflectionExamples();
		
		Class<?> clzToTest = ReflectionExamples.class;
		
		if (args.length > 0) {
			DiskLoader dlr = new DiskLoader();
			clzToTest = dlr.loadFromDisk(args[0]);
		}
		
//		rfx.run(clzToTest);
//		rfx.run2();
//		rfx.run3();
//		rfx.run4();
//		rfx.run5();
//		rfx.run6();
//		rfx.run7();
//		rfx.run9();
		rfx.run10();
	}

	@Deprecated
	public String badToString() {
		return super.toString();
	}

	
}
