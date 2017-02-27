package com.leader.spring04.fanshe;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

public class Util{

   public static void analyseFields(Class<?> cls,Object theobj) throws IllegalArgumentException, IllegalAccessException
   {
	   Field [] fields=cls.getDeclaredFields();
	   for(int j=0;j<fields.length;j++)
	   {
		   fields[j].setAccessible(true);
		   System.out.print(fields[j].getName()+",");
		   System.out.print(fields[j].getType()+",");
		   Type fieldType=fields[j].getGenericType();
		   System.out.print(fieldType.getTypeName()+",");
		  
		   if(fieldType.equals(String.class))
		   {
			   System.out.println(fields[j].get(theobj));
		   }else if(fieldType.equals(Integer.class))
		   {
			   System.out.println(fields[j].get(theobj));
		   }else if(fieldType.getTypeName().equals("int"))
		   {
			   System.out.println(fields[j].getInt(theobj));
		   }else if(fieldType instanceof GenericArrayType)
		   {
			   GenericArrayType genType=(GenericArrayType)fieldType;
			   System.out.println("GenericArrayType:"+genType.getGenericComponentType().getTypeName());
		   }else if(fieldType instanceof ParameterizedType)
		   {
			   ParameterizedType parType=(ParameterizedType)fieldType;
			   //actuals 实际值
			   System.out.println("ParameterizedType:"+parType.getRawType()+" actuals "+Arrays.toString(parType.getActualTypeArguments()));
		   }else
		   {
			   System.out.println("complix type");
		   }
	   }
   }
}
