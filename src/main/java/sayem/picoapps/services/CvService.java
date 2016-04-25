package sayem.picoapps.services;

import java.awt.Image;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CvService {
	// check if a file is image
		public boolean isImageValid(MultipartFile multipartFile) throws EOFException {
			try {
				Image image = ImageIO.read(this.convertToFile(multipartFile));
				if (image != null) {
					return true;
				}
				return false;
			} catch (Exception e) {
				System.out.println(e.toString());
				return false;
			}
		}

		// convert Multipart to File
		private File convertToFile(MultipartFile multipartFile) throws Exception {
			File file = new File(multipartFile.getOriginalFilename());
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(multipartFile.getBytes());
			fos.close();
			return file;
		}
}
