package com.reflection.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.codehaus.jackson.annotate.JsonProperty;

import com.google.gson.annotations.SerializedName;

public class RestReflectionHelper
{
    private RestReflectionHelper()
    {
    }

    public static String getSerializedName(Class<?> pClass, String pName)
    {
        String tFieldName = getSerializedNameForMethod(pClass, pName);

        if (tFieldName == null)
        {
            return getSerializedNameForField(pClass, pName);
        }
        return tFieldName;
    }

    public static String getSerializedName(Object pObject, String pName)
    {
        String tFieldName = getSerializedNameForMethod(pObject.getClass(), pName);

        if (tFieldName == null)
        {
            return getSerializedNameForField(pObject.getClass(), pName);
        }
        return tFieldName;
    }

    public static String getSerializedNameForField(Object pObject, String pFieldName)
    {
        return getSerializedNameForField(pObject.getClass(), pFieldName);
    }

    public static String getSerializedNameForField(Class<?> pClass, String pFieldName)
    {
        Field tField;
        try
        {
            tField = pClass.getDeclaredField(pFieldName);
            tField.setAccessible(true);
        }
        catch(NoSuchFieldException | SecurityException eReflection)
        {
        	eReflection.printStackTrace();
            return null;
        }

        for (Annotation tAnnotation : tField.getDeclaredAnnotations())
        {
            if (tAnnotation instanceof SerializedName)
            {
                return ((SerializedName)tAnnotation).value();
            }
            else if (tAnnotation instanceof JsonProperty)
            {
                return ((JsonProperty)tAnnotation).value();
            }
        }

        return null;
    }

    public static String getSerializedNameForMethod(Object pObject, String pMethodName)
    {
        return getSerializedNameForMethod(pObject.getClass(), pMethodName);
    }

    public static String getSerializedNameForMethod(Class<?> pClass, String pMethodName)
    {
        Method tMethod;
        Class<?>[] tInterfaceClass = pClass.getInterfaces();
        try
        {
            tMethod = tInterfaceClass[0].getMethod(pMethodName);
            tMethod.invoke(pClass.newInstance());
        }
        catch(SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                        | InstantiationException | NoSuchMethodException eReflection)
        {
        	eReflection.printStackTrace();
            return null;
        }

        for (Annotation tAnnotation : tMethod.getDeclaredAnnotations())
        {
            if (tAnnotation instanceof SerializedName)
            {
                return ((SerializedName)tAnnotation).value();
            }
            else if (tAnnotation instanceof JsonProperty)
            {
                return ((JsonProperty)tAnnotation).value();
            }
        }

        return null;
    }

}
