package com.zss.zrl;

public class Main {

	public static void main(String[] args) {
		String str="hello every body!";
		byte[] data=str.getBytes();
		
		HandlerChain chain=new HandlerChain();//责任链
		chain.addFirst(new DeCompressHandler());//压缩和解压缩
		chain.addLast(new DecryptHandler());//加密和解密 
		data=chain.doOut(data);//数据 输出到客户端
		System.out.println("加密和压缩后的数据:"+new String(data));
		data=chain.doIn(data);//解析 来自客户端的数据
		System.out.println("解密和解压缩后的数据"+new String(data));
	}
	
}
