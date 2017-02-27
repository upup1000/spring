package leader04.logic.test3;

import annotation.ZAutoWrite;
import annotation.ZComponent;
import leader04.logic.test1.AbstractDao;
import leader04.logic.test1.User;

@ZComponent
public abstract class AbstractService2{
	@ZAutoWrite
	private AbstractDao<User> dao;
	public AbstractDao<User> getDao()
	{
		return dao;
	}
}
