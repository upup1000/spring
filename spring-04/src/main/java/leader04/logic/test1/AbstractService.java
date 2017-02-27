package leader04.logic.test1;

import annotation.ZAutoWrite;
import annotation.ZComponent;
@ZComponent
public abstract class AbstractService<T>{
	@ZAutoWrite
	private AbstractDao<T> dao;
	public boolean saveDomain(T domain)
	{
		System.out.println("check domain obj"+domain);
		doCheckDomain(domain);
		System.out.println("save domain obj"+ domain);
		dao.craeteDomainObj(domain);
		return true;
	}
	
	public AbstractDao<T> getDao()
	{
		return dao;
	}
	protected void doCheckDomain(T domain) {
		
	}
}
