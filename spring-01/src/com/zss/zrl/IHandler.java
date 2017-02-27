package com.zss.zrl;
/**
 * 职责接口
 * @author zss
 */
public interface IHandler {

	public byte [] doIn(byte[] data);
	
	public byte [] doOut(byte[] data);
}
