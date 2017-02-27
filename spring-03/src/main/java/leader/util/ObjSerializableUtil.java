package leader.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化工具
 * 
 * @author zss
 */
public class ObjSerializableUtil {

	public static byte[] obj2Byte(Object obj) throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(obj);
		byte[] bytes = bo.toByteArray();
		bo.close();
		oo.close();
		return bytes;
	}

	public static <T> T byte2Obj(byte[] data, Class<T> clazz) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bi = new ByteArrayInputStream(data);
		ObjectInputStream oi = new ObjectInputStream(bi);
		Object obj = oi.readObject();
		bi.close();
		oi.close();
		return clazz.cast(obj);
	}
}
