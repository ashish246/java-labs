package com.java.json.test;

import org.codehaus.jackson.annotate.JsonTypeInfo;

public class Container 
{
   private Animal animal;

   public Animal getAnimal()
   {
      return animal;
   }

   @JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.WRAPPER_OBJECT, property="type")
   public void setAnimal(Animal animal)
   {
      this.animal = animal;
   }
}