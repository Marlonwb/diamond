package person.marlon.diamond.web.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {
	private static Logger logger = LoggerFactory.getLogger(FileController.class);

	BufferedImage img;


//	@RequestMapping("/upload")
//	@ResponseBody
//	public String upload(){}

	public Dimension getPreferredSize() {
		if (img == null) {
			return new Dimension(100,100);
		} else {
			return new Dimension(img.getWidth(null), img.getHeight(null));
		}
	}

	public static void main(String[] args) {

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("C:/Users/Lenovo/Desktop/c0e0081a9f5c6aa.jpg"));
			// retrieve image
			File outputfile = new File("C:/Users/Lenovo/Desktop/saved.png");
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			logger.error("create file error {}",e);
		}
	}
}
