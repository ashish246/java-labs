package com.collection.iterator;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class IteratorExample{

    public static void main(String[] args) {
        //Hashtable instance used for Iterator Example in Java 
        Hashtable<Integer, String> stockTable=new Hashtable<Integer,String>();

        //Populating Hashtable instance with sample values
        stockTable.put(new Integer(1), "Two");
        stockTable.put(new Integer(2), "One");
        stockTable.put(new Integer(4), "Four");
        stockTable.put(new Integer(3), "Three");

        //Getting Set of keys for Iteration
        Set<Entry<Integer, String>> stockSet = stockTable.entrySet();

        // Using Iterator to loop Map in Java, here Map implementation is Hashtable
        Iterator<Entry<Integer, String>> i= stockSet.iterator();
        System.out.println("Iterating over Hashtable in Java");
        
        //Iterator begins
        while(i.hasNext()){
            Map.Entry<Integer,String> m=i.next();
            int key = m.getKey();
            String value=m.getValue();
            System.out.println("Key :"+key+"  value :"+value);

        }
        System.out.println("Iteration over Hashtable finished");
    }
}

