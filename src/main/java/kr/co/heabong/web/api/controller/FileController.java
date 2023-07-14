package kr.co.heabong.web.api.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.co.heabong.web.entity.User;
import kr.co.heabong.web.entity.Org;
import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.service.UserService;
import kr.co.heabong.web.service.OrgVolService;

@RestController
@RequestMapping("api/file")
public class FileController {

	@Autowired
	private UserService service;
	
	@Autowired
	private OrgVolService orgVolService;

	@Autowired
	private ResourceLoader resourceLoader;

	@Value("${upload.files}")
	private String uploadPath;

//    @PutMapping("user/{id}/image")
//user 와 org 를 받기 위해 path를 변수화 
	@PutMapping("{path}/{id}/image")
	public String profileImgEdit(@RequestParam("file") MultipartFile file, @PathVariable String path,
			@PathVariable int id) throws IOException {

		String fullPath = null;

		if (path.equals("user")) {

			String fileName = file.getOriginalFilename();

			System.out.println(fileName);

			// 상대 경로를 ~
			Resource resource = resourceLoader.getResource(uploadPath); // uploadPath : yml파일에 설정해줌

			File pathFile = resource.getFile();
			System.out.println(pathFile);

			if (!pathFile.exists()) {
				// Directory 만들어주는 method
				// pathFile.mkdir(); 하나의 디렉토리를 만들어줌
				pathFile.mkdirs(); // 없는 모든 디렉토리를 만들어줌
			}

			// ~ 절대 경로로 변환 후
			// 절대경로 + 파일명으로 파일을 저장해주기 위한 작업
			// C:\STSWorkspace\rland\src\main\webapp\\upload\menus\??.jpg
			String realPath = Paths.get(pathFile.getAbsolutePath(), fileName).toString();
			System.out.println(realPath);

			// 파일 저장
			file.transferTo(new File(realPath));
			System.out.println("저장 경로: " + realPath);

			// 파일 경로
			fullPath = uploadPath + fileName;
			// }

			User user = service.get(id);
			user.setProfilePhoto(fullPath);
			service.update(user);
		}

		if (path.equals("org")) {
			System.out.println("orgorg");
	
			String fileName = file.getOriginalFilename();

			System.out.println(fileName);

			// 상대 경로를 ~
			Resource resource = resourceLoader.getResource(uploadPath); // uploadPath : yml파일에 설정해줌

			File pathFile = resource.getFile();
			System.out.println(pathFile);

			if (!pathFile.exists()) {
				// Directory 만들어주는 method
				// pathFile.mkdir(); 하나의 디렉토리를 만들어줌
				pathFile.mkdirs(); // 없는 모든 디렉토리를 만들어줌
			}

			// ~ 절대 경로로 변환 후
			// 절대경로 + 파일명으로 파일을 저장해주기 위한 작업
			// C:\STSWorkspace\rland\src\main\webapp\\upload\menus\??.jpg
			String realPath = Paths.get(pathFile.getAbsolutePath(), fileName).toString();
			System.out.println(realPath);

			// 파일 저장 //
			file.transferTo(new File(realPath));
			System.out.println("저장 경로: " + realPath);

			// 파일 경로
			fullPath = uploadPath + fileName;
			// }
			
			OrgVol orgVol = orgVolService.getById(id);
			System.out.println(orgVol);
			orgVol.setPhoto(fileName);
			System.out.println(orgVol);
			orgVolService.update(orgVol);
		}

		return fullPath;
	}
	

	
	
	

}