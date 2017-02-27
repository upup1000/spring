package com.zss.cl;

public class Main {

	public static void main(String[] args) {
		Main.calculation("+", 10, 10);
		Main.calculation("-", 10, 10);
		Main.calculation("*", 10, 10);
	}
	
	public static void calculation(String type,int a,int b)
	{
		Result result;
		switch (type) {
		case "+":
			result=new AddImpl();
			break;
		case "-":
			result=new MinusImp();
			break;
		case "*":
			result=new MultiplicationImpl();
			break;
		default:
			System.out.println("没有对应的算法");
			return;
		}
		System.out.println(type+"计算结果"+result.getResult(a, b));
		
	}
}
