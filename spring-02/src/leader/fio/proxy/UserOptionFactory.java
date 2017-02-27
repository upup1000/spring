package leader.fio.proxy;

import leader.fio.IUserOption;
import leader.fio.impl.BinaryUserOption;
import leader.fio.impl.JsonUserOption;
import leader.fio.impl.XMLUserOption;

/**
 * 工厂类
 * 
 * @author zss
 */
public class UserOptionFactory {

	public static IUserOption create(int type) {
		switch (type) {
		case 1:
			return new JsonUserOption();
		case 2:
			return new BinaryUserOption();
		case 3:
			return new XMLUserOption();
		default:
			break;
		}
		return new JsonUserOption();
	}
}
