package com.zss.zrl;
/**
 * 负责加密解密处理 
 * @author zss
 */
public class DecryptHandler implements IHandler {

	@Override
	public byte[] doIn(byte[] data) {
		for(int i=0;i<data.length;i++)
		{
			//取反处理
			data[i]=(byte) ~data[i];
		}
		return data;
	}

	@Override
	public byte[] doOut(byte[] data) {
		return doIn(data);
	}

}
