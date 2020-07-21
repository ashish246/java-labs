package com.reflection.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import rest.reflection.entity.ManifestRO;

/**
 * @author Administrator
 * 
 */
public class ReflectionHelloWorld {
	public static void main(String[] args) {
		Foo f = new Foo();

		// Get class name with package info from object
		System.out.println(f.getClass().getName());

		// get class name without package info
		System.out.println(f.getClass().getSimpleName());

		// get modifier info
		System.out.println(f.getClass().getModifiers());

		// validate the modifier
		System.out.println(Modifier.isPublic(f.getClass().getModifiers()));

		// get package info
		System.out.println(f.getClass().getPackage().getName());

		// get superclass
		System.out.println(f.getClass().getSuperclass());

		// get implemented interfaces
		Class[] interfaces = f.getClass().getInterfaces();
		System.out.println(interfaces[0].getName());

		// Invoke method on unknown object
		Method method;
		try {
			method = f.getClass().getMethod("print", new Class<?>[0]);
			method.invoke(f);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Create object from Class instance
		Class<?> c = null;
		try {
			c = Class.forName("com.reflection.test.Foo");
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * create instance of "Foo"
		 */
		Foo f1 = null;

		try {
			f1 = (Foo) c.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		f1.print();

		// create instance of "Foo"
		Foo f3 = null;
		Foo f2 = null;

		/*
		 * get all PUBLIC constructors
		 */
		Constructor<?> cons[] = c.getConstructors();

		try {
			f3 = (Foo) cons[0].newInstance();
			f2 = (Foo) cons[1].newInstance("abc");
		} catch (Exception e) {
			e.printStackTrace();
		}

		f3.print();
		f2.print();

		/*
		 * If you know the precise parameter types of the constructor you want
		 * to access, you can do so rather than obtain the array all
		 * constructors. This example returns the public constructor of the
		 * given class which takes a String as parameter:
		 */
		Constructor constructor = null;
		try {
			constructor = c.getConstructor(new Class[] { String.class });
			Foo f4 = (Foo) constructor.newInstance("sddsds");

			f4.print();

			// get the constructor parameter type
			Class[] parameterTypes = constructor.getParameterTypes();
			System.out.println("Construcutor parameter type: "
					+ parameterTypes.getClass().getName());
		} catch (NoSuchMethodException | SecurityException
				| InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * get field name of a POJO using RestReflectionHelper
		 */
		System.out.println(RestReflectionHelper.getSerializedName(
				new ManifestRO(), "getContractId"));
		System.out.println(RestReflectionHelper.getSerializedName(
				new ManifestRO(), "mContractId"));

		/*
		 * accessing PRIVATE fields. Same is for PRIVATE methods
		 */
		Foo privateObject = new Foo();
		Field privateStringField;
		try {
			privateStringField = Foo.class.getDeclaredField("privateString");
			privateStringField.setAccessible(true);

			String fieldValue = (String) privateStringField.get(privateObject);

			System.out.println("private object: " + fieldValue);

		} catch (NoSuchFieldException | SecurityException
				| IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * accessing method parameter annotations
		 */
		Method method1;
		try {
			method1 = c.getMethod("doSomethingElse",
					new Class[] { String.class });
			// method1.invoke("");

			Annotation[][] parameterAnnotations = method1
					.getParameterAnnotations();
			Class[] parameterTypes = method1.getParameterTypes();
			int i = 0;
			for (Annotation[] annotations : parameterAnnotations) {
				Class parameterType = parameterTypes[i++];

				for (Annotation annotation : annotations) {
					if (annotation instanceof JsonProperty) {
						JsonProperty myAnnotation = (JsonProperty) annotation;
						System.out.println("param: " + parameterType.getName());
						System.out.println("value: " + myAnnotation.value());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * accessing GENERIC TYPE or field type, method return type or method
		 * parameter type
		 */
		Method method3;
		Type returnType = null;
		Type[] genericParameterTypes = null;

		try {
			method3 = Foo.class.getMethod("getStringList",
					new Class[] { List.class });

			returnType = method3.getGenericReturnType();

			genericParameterTypes = method3.getGenericParameterTypes();
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (returnType instanceof ParameterizedType) {
			ParameterizedType type = (ParameterizedType) returnType;
			Type[] typeArguments = type.getActualTypeArguments();
			for (Type typeArgument : typeArguments) {
				Class typeArgClass = (Class) typeArgument;
				System.out.println("typeArgClass = " + typeArgClass);
			}
		}

		for (Type genericParameterType : genericParameterTypes) {
			if (genericParameterType instanceof ParameterizedType) {
				ParameterizedType aType = (ParameterizedType) genericParameterType;
				Type[] parameterArgTypes = aType.getActualTypeArguments();
				for (Type parameterArgType : parameterArgTypes) {
					Class parameterArgClass = (Class) parameterArgType;
					System.out.println("parameterArgClass = "
							+ parameterArgClass);
				}
			}
		}

		Field field;
		Type genericFieldType = null;
		try {
			field = Foo.class.getField("stringList");

			genericFieldType = field.getGenericType();

		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (genericFieldType instanceof ParameterizedType) {
			ParameterizedType aType = (ParameterizedType) genericFieldType;
			Type[] fieldArgTypes = aType.getActualTypeArguments();
			for (Type fieldArgType : fieldArgTypes) {
				Class fieldArgClass = (Class) fieldArgType;
				System.out.println("fieldArgClass = " + fieldArgClass);
			}
		}

	}
}

class Foo implements Cloneable {
	String s = "xyz";

	private String privateString = null;

	protected List<String> stringList = new ArrayList<>();

	public Foo() {
		privateString = "private";
	}

	public Foo(String s) {
		this.s = s;
		privateString = "private";
	}

	public void print() {
		System.out.println(s);

		System.out.println(privateString);
	}

	public void doSomethingElse(
			@JsonProperty("do_somthing_else") String parameter) {
	}

	public List<String> getStringList(List<String> list) {
		return list;
	}
}