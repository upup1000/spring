package edu.ldcollege.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import edu.ldcollege.domain.LdHomeWork;
import edu.ldcollege.domain.LdHomeWorkExample;
import edu.ldcollege.domain.LdUser;
import edu.ldcollege.mapping.LdHomeWorkMapper;
import edu.ldcollege.util.FileUtil;

@Component
public class LdHomeworkService {

	@Autowired
	private LdHomeWorkMapper mapper;
	
	public boolean saveFile(MultipartFile file,String path)
	{
		try {
			FileUtil.addFile(file.getBytes(),path);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void addorUpdateHomeWork(LdUser user,String homeWorkName,String path)
	{
		LdHomeWork homeWork=new LdHomeWork();
		homeWork.setBestflag(false);
		homeWork.setClassid(user.getClassid());
		homeWork.setLessionid(user.getLessionid());
		homeWork.setNegativecount(0);
		homeWork.setHomeworkfilename(homeWorkName);
		homeWork.setHomeworkfilepath(path);
		homeWork.setUserid(user.getId());
		homeWork.setStarcount(0);
		homeWork.setCorrectflag(0);
		homeWork.setCreatedate(new Date());
		mapper.insert(homeWork);
	}
	
	public List<LdHomeWork> getHomeWorkByUserId(int userId)
	{
		LdHomeWorkExample ex=new LdHomeWorkExample();
		ex.createCriteria().andUseridEqualTo(userId);
		return mapper.selectByExample(ex);
	}
	
}
