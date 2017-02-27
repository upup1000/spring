package leader.bean;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户session管理器
 * @author zss
 */
public class UserSessionManager {

	private static Map<String,UserSession> smap=new ConcurrentHashMap<String,UserSession>();
	
	public static void addSession(UserSession session)
	{
		smap.put(session.getSessionId(), session);
	}
	
	public static UserSession getSession(String sessionId)
	{
		UserSession session=smap.get(sessionId);
		if(session!=null&&session.isValid())
		{
			return session;
		}
		smap.remove(sessionId);
		return null;
	}
	
	/**
	 * 超时移除 
	 */
	public static void checkTime()
	{
		Collection<UserSession> sessions= smap.values();
		for(UserSession session:sessions)
		{
			if(!session.isValid())
			{
				smap.remove(session.getUserId());
			}
		}
	}
}
