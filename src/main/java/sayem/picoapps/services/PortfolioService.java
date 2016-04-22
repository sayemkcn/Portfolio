package sayem.picoapps.services;

import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service(value="portfolioService")
public class PortfolioService {
	
	public boolean isImageValid(MultipartFile multipartFile){
		try {
			Image image = ImageIO.read(this.convertToFile(multipartFile));
			if (image!=null) {
				return true;
			}
		} catch (IOException e) {
			System.out.println("IOException: "+e.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return false;
	}
	
	
	private File convertToFile(MultipartFile multipartFile) throws Exception{
		File file = new File(multipartFile.getOriginalFilename());
		file.createNewFile();
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		fileOutputStream.write(multipartFile.getBytes());
		fileOutputStream.close();
		return file;
	}
}
