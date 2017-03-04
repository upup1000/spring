package edu.ldcollege.ctrl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ldcollege.bean.HomeWork;
import edu.ldcollege.domain.LdHomeWork;
import edu.ldcollege.domain.LdUser;
import edu.ldcollege.service.LdHomeworkService;

/**
 * 作业控制器
 * @author zss
 */
@Controller
public class LdHomeworkController {

	@Autowired
	private LdHomeworkService service;

	@RequestMapping("/gethomework")
	@ResponseBody
	public List<HomeWork> getMyHomeWork(HttpSession session) {
		LdUser user = (LdUser) session.getAttribute("user");
		List<LdHomeWork> works = service.getHomeWorkByUserId(user.getId());
		if (works != null) {
			List<HomeWork> views=new ArrayList<>();
			for(LdHomeWork w:works)
			{
				views.add(new HomeWork(w));
			}
			return views;
		}
		return Collections.emptyList();
	}
}
