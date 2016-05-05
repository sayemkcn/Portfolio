package sayem.picoapps.services;

import java.awt.Image;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import sayem.picoapps.domains.Cv;
import sayem.picoapps.domains.User;
import sayem.picoapps.repositories.CvRepository;

@Service
public class CvService {
	@Autowired
	private CvRepository cvRepository;
	
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

		public Map<String, Object> getCv(Long id) {
			Cv cv = cvRepository.getOne(id);
			Map<String, Object> map = new HashMap<>();
			map.put("cv", cv);
			return map;
		}
		
		public Cv loadCvByUser(User user){
			return cvRepository.findByUser(user);
		}
}
