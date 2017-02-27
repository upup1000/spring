package leader04.logic.test1;

import annotation.ZComponent;

@ZComponent
public class AbstractDao<T> {
	public boolean craeteDomainObj(T domainObj)
	{
		return true;
	}
	boolean deleteDomainObj(Integer objId)
	{
		return true;
	}
}
