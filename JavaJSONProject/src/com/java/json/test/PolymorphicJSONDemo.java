package com.java.json.test;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class PolymorphicJSONDemo
{
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException,
			IOException
	{
		Animal animal = new Dog();

		ObjectMapper mapper = new ObjectMapper();

		//System.out.println(mapper.writeValueAsString(animal));
		
		//Animal animalAfter = mapper.readValue(mapper.writeValueAsString(animal), Animal.class);
		
		System.out.println("----------------");
		
		Container container = new Container();
		container.setAnimal(animal);
		
		System.out.println(mapper.writeValueAsString(container));
		
		Container containerAfter = mapper.readValue(mapper.writeValueAsString(container), Container.class);
		
		Animal animalAfter = containerAfter.getAnimal();
		
		System.out.println("----------");
	}
}

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.WRAPPER_OBJECT, property = "type")
abstract class Animal
{
	public String name;
}

class Dog extends Animal
{
	public String breed = "PUG";
	public String leashColor = "black";
}

class Cat extends Animal
{
	public String favoriteToy;
}