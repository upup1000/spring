package com.zss.zrl;

/**
 * 负责 解压缩 和解压缩
 * @author zss
 *
 */
public class DeCompressHandler  implements IHandler{


	@Override
	public byte[] doIn(byte[] data) {
		try {
			return GZipUtils.decompress(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public byte[] doOut(byte[] data) {
		try {
			return GZipUtils.compress(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
