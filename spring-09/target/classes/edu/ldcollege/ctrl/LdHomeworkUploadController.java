package edu.ldcollege.ctrl;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.ldcollege.bean.LoginReult;
import edu.ldcollege.domain.LdUser;
import edu.ldcollege.service.LdHomeworkService;

/**
 * 作业上传控制器
 * 
 * @author zss
 */
@Controller
public class LdHomeworkUploadController {
	@Autowired
	private LdHomeworkService service;
	@Value("${spring.http.multipart.max-file-size}")
	private Integer filemaxSize;

	@PostMapping("/upload")
	@ResponseBody
	public LoginReult handleFileUpload(HttpSession session, @RequestParam("homework") String hmname,
			@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		LoginReult result = checkFile(file);
		if (result.getState() == 1) {
			return result;
		}
		LdUser user = (LdUser) session.getAttribute("user");
		String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/uploadfile/"
				+ file.getOriginalFilename();
		System.out.println(path);
		path = path.replaceFirst("/", "");
		service.saveFile(file, path);
		service.addorUpdateHomeWork(user, hmname, file.getOriginalFilename());
		return result;
	}

	private LoginReult checkFile(MultipartFile file) {
		LoginReult result = new LoginReult();
		int fileSize = 0;
		String fileName = file.getOriginalFilename();
		try {
			fileSize = file.getBytes().length;
		} catch (IOException e) {
			result.setErrorinfo("上传文件出错!!");
			result.setState(1);
			return result;
		}
		System.out.println("==" + filemaxSize);
		if (fileSize > filemaxSize) {
			result.setErrorinfo("文件最大不能超过5M");
			result.setState(1);
			return result;
		}
		if (!(fileName.indexOf(".pdf") != -1 || fileName.indexOf(".ppf") != -1)) {
			result.setErrorinfo("文件格式只能是pdf或者ppt");
			result.setState(1);
			return result;
		}
		return result;
	}
}
