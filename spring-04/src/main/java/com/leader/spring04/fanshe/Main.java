package com.leader.spring04.fanshe;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import org.springframework.core.ResolvableType;

public class Main {

	public static void main(String[] args) {
		MyCompBean<String> comp = new MyCompBean<String>();
		TypeVariable<Class<MyCompBean>> typevar = MyCompBean.class.getTypeParameters()[0];
		System.out.println("type name " + typevar.getName() + " type " + typevar.getTypeName());

		ParameterizedType parameterizedType = (ParameterizedType) MyChildCompBean.class.getGenericSuperclass();
		Type genericType = parameterizedType.getActualTypeArguments()[0];
		System.out.println("type is" + genericType + " is java.util.Data?" + (genericType == java.util.Date.class));

		parameterizedType = (ParameterizedType) MyChildCompBean2.class.getGenericSuperclass();
		genericType = parameterizedType.getActualTypeArguments()[0];
		System.out.println("type is" + genericType);
		// ParameterizedType realType=(ParameterizedType)genericType;
		// System.out.println("raw type"+realType.getRawType()+", realType
		// is"+realType.getActualTypeArguments()[0]);

		System.out.println("spring api");
		ResolvableType resolvableType1 = ResolvableType.forClass(MyCompBean.class);
		System.out.println("type name" + resolvableType1.getGeneric(0).resolve());

		resolvableType1 = ResolvableType.forClass(MyChildCompBean.class).getSuperType();
		System.out.println("type name" + resolvableType1.getGeneric(0).resolve());

		resolvableType1 = ResolvableType.forClass(MyChildCompBean2.class).getSuperType();
		Type theType = resolvableType1.getGeneric(0).resolve();
		System.out.println("raw name" + theType);
		theType = resolvableType1.getGeneric(0, 0).resolve();
		System.out.println("realType name" + theType);
	}
}
