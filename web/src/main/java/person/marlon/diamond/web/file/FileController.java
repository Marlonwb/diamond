package person.marlon.diamond.web.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import person.marlon.diamond.common.generic.ApiResponse;
import person.marlon.diamond.common.util.ResourceUtil;
import person.marlon.diamond.service.file.FileService;

import javax.annotation.Resource;
import java.io.File;

@Controller
@RequestMapping("/file")
public class FileController {
	private static Logger logger = LoggerFactory.getLogger(FileController.class);

//	BufferedImage img;


//	@RequestMapping("/upload")
//	@ResponseBody
//	public String upload(){}

//	public Dimension getPreferredSize() {
//		if (img == null) {
//			return new Dimension(100,100);
//		} else {
//			return new Dimension(img.getWidth(null), img.getHeight(null));
//		}
//	}

//	public static void main(String[] args) {
//
//		BufferedImage img = null;
//		try {
//			img = ImageIO.read(new File("C:/Users/Lenovo/Desktop/c0e0081a9f5c6aa.jpg"));
//			// retrieve image
//			File outputfile = new File("C:/Users/Lenovo/Desktop/saved.png");
//			ImageIO.write(img, "png", outputfile);
//		} catch (IOException e) {
//			logger.error("create file error {}",e);
//		}
//	}
	
	private final String UPLOAD_FILE_PATH = ResourceUtil.getValue("upload_file_path");
	
	@Resource
	private FileService fileService;
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public  String uploadFileHandler(@RequestParam("file") MultipartFile[] files) {
		if(files == null || files.length == 0){
			return new ApiResponse<>(-1,"upload file is null!").toString();
		}
		
		for (int i = 0; i < files.length; i++) {
			if (!files[i].isEmpty()) {
				try {
					// 文件存放服务端的位置
					File dir = new File(UPLOAD_FILE_PATH + File.separator + "upload");
					if (!dir.exists())
						dir.mkdirs();
					// 写文件到服务器
					File serverFile = new File(dir.getAbsolutePath() + File.separator + files[i].getOriginalFilename());
					files[i].transferTo(serverFile);//该方法重复提交会删除旧的同名图片
					logger.info("successfully uploaded file:" +  files[i].getOriginalFilename());
				} catch (Exception e) {
					logger.warn(" failed to upload " +  files[i].getOriginalFilename() + " => " + e.getMessage());
				}
			} else {
				logger.warn("failed to upload " +  files[i].getOriginalFilename() + " because the file was empty.");
			}
		}
		return new ApiResponse<>(0, "You successfully uploaded files:" +  files).toString();
	}
	
	@PostMapping(value = "/uploadFileWithAddtionalData",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String submit(@RequestParam(required = false) MultipartFile file, @ModelAttribute Model model) {
		if(model == null){
			return new ApiResponse<>(-1,"request param is empty!").toString();
		}
		
		if(file != null){
			if(!fileService.writeFile(file,"")){
				return new ApiResponse<>(-1,"write file failed!").toString();
			}
		}
		//todo some operations.
		
		return new ApiResponse<>(0, "upload success!").toString();
	}
	
}
