package sayem.picoapps.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import sayem.picoapps.domains.Cv;
import sayem.picoapps.domains.User;
import sayem.picoapps.domains.embedded.EducationInfo;
import sayem.picoapps.domains.embedded.ExperienceInfo;
import sayem.picoapps.domains.embedded.Projects;
import sayem.picoapps.domains.embedded.References;
import sayem.picoapps.domains.embedded.TrainingInfo;
import sayem.picoapps.repositories.CvRepository;
import sayem.picoapps.services.CvService;

@Controller
@RequestMapping(value = "/cv")
public class CvController {
	@Autowired
	private CvRepository cvRepository;
	@Autowired
	private CvService cvService;
	@Autowired
	private ApplicationContext appContext;
	
	 @InitBinder
	 public void initBinder(WebDataBinder binder){
	 binder.registerCustomEditor( Date.class,
	 new CustomDateEditor(new SimpleDateFormat("yyyy-dd-MM"), true, 10));
	 }
	 
	 
	 @RequestMapping(value="/{userId}",method=RequestMethod.GET)
	 public String viewCv(@PathVariable("userId") Long userId,Model model){
		 model.addAttribute("cv", cvRepository.findCvByUserId(userId));
		 return "cv/view";
	 }
	 
	 @RequestMapping(value="/img/{userId}",method=RequestMethod.GET)
	 @ResponseBody
	 public byte[] image(@PathVariable("userId") Long userId){
		 return cvRepository.findCvByUserId(userId).getPersonalInfo().getProfilePhoto();
	 }
	 
//	// print jasper report of the cv
//	 @RequestMapping(value = "/report/{id}", method = RequestMethod.GET, produces = "application/pdf")
//	 public ModelAndView getPdf(@PathVariable("id") Long id) {
//	     final Map<String, Object> cvList = cvService.getCv(id);
//
//	     final JasperReportsPdfView view = new JasperReportsPdfView();
//	     view.setReportDataKey("cv");
//	     view.setUrl("classpath:/report/cv.jrxml");
//	     view.setApplicationContext(appContext);
//
////	     final Map<String, Object> params = new HashMap<>();
////	     params.put("users", cvList);
//
//	     return new ModelAndView(view, cvList);
//	 }

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createCvP1Page() {
		return "cv/createCvPageOne";
	}

	@RequestMapping(value = "/create/p1", method = RequestMethod.POST)
	public String createCvP1(@ModelAttribute Cv cv, BindingResult bindingResult,@RequestParam("personalInfo.profilePhoto") MultipartFile multipartFile, HttpSession httpSession) throws IOException {
		if (bindingResult.hasErrors()) {
			System.out.println("BINDING RESULT_P1: \n" + bindingResult.toString());
		}
		if (cvService.isImageValid(multipartFile)) {
			cv.getPersonalInfo().setProfilePhoto(multipartFile.getBytes());
		}
		httpSession.setAttribute("tempCv", cv);
		System.out.println(cv.toString());
		return "redirect:/cv/create/p2";
	}

	
	// PAGE 2
	@RequestMapping(value = "/create/p2", method = RequestMethod.GET)
	public String createCvP2Page() {
		return "cv/createCvPageTwo";
	}

	@RequestMapping(value = "/create/p2", method = RequestMethod.POST)
	public String createCvP2(@ModelAttribute EducationInfo educationInfo, BindingResult bindingResult,
			@RequestParam("submitButton") String submitButton, HttpSession httpSession) {
		Cv cv = (Cv) httpSession.getAttribute("tempCv");
		// check if add new button clicked
		if (submitButton.contentEquals("Add New")) {
			if (cv != null) { // if session is set then proceed to add page two
								// data
				cv.getEducationInfoList().add(educationInfo);
				httpSession.setAttribute("tempCv", cv);
				return "cv/createCvPageTwo";
			} else { // if session isn't set already then redirect to previous
						// page
				return "redirect:/cv/create";
			}
		} else if (submitButton.contentEquals("Save and Next")) { // check if
																	// next
																	// button
																	// clicked
			if (cv != null) { // if session is set then proceed to add page two
								// data
				cv.getEducationInfoList().add(educationInfo);
				httpSession.setAttribute("tempCv", cv);
			} else {
				return "redirect:/cv/create";
			}
		}

		return "redirect:/cv/create/p3";
	}

	@RequestMapping(value = "/create/p2/remove/{hashCode}", method = RequestMethod.GET)
	public String removeEducationObjectFromSession(@PathVariable("hashCode") int hashCode, HttpSession httpSession) {
		Cv cv = (Cv) httpSession.getAttribute("tempCv");
		try {
			for (EducationInfo educationInfo : cv.getEducationInfoList()) {
				if (educationInfo.hashCode() == hashCode) {
					cv.getEducationInfoList().remove(educationInfo);
				}
			}
		} catch (ConcurrentModificationException e) {
				// ignore this bullshit!
		}
		httpSession.setAttribute("tempCv", cv);
		return "redirect:/cv/create/p2";
	}
	// END PAGE 2
	
	// PAGE 3
	@RequestMapping(value = "/create/p3", method = RequestMethod.GET)
	public String createCvP3Page() {
		return "cv/createCvPageThree";
	}
	
	@RequestMapping(value = "/create/p3", method = RequestMethod.POST)
	public String createCvP3(@ModelAttribute ExperienceInfo experienceInfo, BindingResult bindingResult,
			@RequestParam("submitButton") String submitButton, HttpSession httpSession) {
		Cv cv = (Cv) httpSession.getAttribute("tempCv");
		// check if add new button clicked
		if (submitButton.contentEquals("Add New")) {
			if (cv != null) { // if session is set then proceed to add page two
								// data
				cv.getExperienceInfoList().add(experienceInfo);
				httpSession.setAttribute("tempCv", cv);
				return "cv/createCvPageThree";
			} else { // if session isn't set already then redirect to previous
						// page
				return "redirect:/cv/create";
			}
		} else if (submitButton.contentEquals("Save and Next")) { // check if
																	// next
																	// button
																	// clicked
			if (cv != null) { // if session is set then proceed to add page two
								// data
				cv.getExperienceInfoList().add(experienceInfo);
				httpSession.setAttribute("tempCv", cv);
			} else {
				return "redirect:/cv/create";
			}
		}

		return "redirect:/cv/create/p4";
	}
	@RequestMapping(value = "/create/p3/remove/{hashCode}", method = RequestMethod.GET)
	public String removeExperienceObjectFromSession(@PathVariable("hashCode") int hashCode, HttpSession httpSession) {
		Cv cv = (Cv) httpSession.getAttribute("tempCv");
		try {
			for (ExperienceInfo experienceInfo : cv.getExperienceInfoList()) {
				if (experienceInfo.hashCode() == hashCode) {
					cv.getExperienceInfoList().remove(experienceInfo);
				}
			}
		} catch (ConcurrentModificationException e) {
				// ignore this bullshit!
		}
		httpSession.setAttribute("tempCv", cv);
		return "redirect:/cv/create/p3";
	}
	
	// END PAGE 3
	
	// PAGE 4
	
	@RequestMapping(value = "/create/p4", method = RequestMethod.GET)
	public String createCvP4Page() {
		return "cv/createCvPageFour";
	}
	
	@RequestMapping(value = "/create/p4", method = RequestMethod.POST)
	public String createCvP4(@ModelAttribute Projects project, BindingResult bindingResult,
			@RequestParam("submitButton") String submitButton, HttpSession httpSession) {
		Cv cv = (Cv) httpSession.getAttribute("tempCv");
		// check if add new button clicked
		if (submitButton.contentEquals("Add New")) {
			if (cv != null) { // if session is set then proceed to add page two
								// data
				cv.getProjectsList().add(project);
				httpSession.setAttribute("tempCv", cv);
				return "cv/createCvPageFour";
			} else { // if session isn't set already then redirect to previous
						// page
				return "redirect:/cv/create";
			}
		} else if (submitButton.contentEquals("Save and Next")) { // check if
																	// next
																	// button
																	// clicked
			if (cv != null) { // if session is set then proceed to add page two
								// data
				cv.getProjectsList().add(project);
				httpSession.setAttribute("tempCv", cv);
			} else {
				return "redirect:/cv/create";
			}
		}

		return "redirect:/cv/create/p5";
	}
	@RequestMapping(value = "/create/p4/remove/{hashCode}", method = RequestMethod.GET)
	public String removeProjectObjectFromSession(@PathVariable("hashCode") int hashCode, HttpSession httpSession) {
		Cv cv = (Cv) httpSession.getAttribute("tempCv");
		try {
			for (Projects project : cv.getProjectsList()){
				if (project.hashCode() == hashCode) {
					cv.getProjectsList().remove(project);
				}
			}
		} catch (ConcurrentModificationException e) {
				// ignore this bullshit!
		}
		httpSession.setAttribute("tempCv", cv);
		return "redirect:/cv/create/p4";
	}
	// END PAGE 4
	
	// PAGE 5
	
		@RequestMapping(value = "/create/p5", method = RequestMethod.GET)
		public String createCvP5Page() {
			return "cv/createCvPageFive";
		}
		
		@RequestMapping(value = "/create/p5", method = RequestMethod.POST)
		public String createCvP5(@ModelAttribute TrainingInfo training, BindingResult bindingResult,
				@RequestParam("submitButton") String submitButton, HttpSession httpSession) {
			Cv cv = (Cv) httpSession.getAttribute("tempCv");
			// check if add new button clicked
			if (submitButton.contentEquals("Add New")) {
				if (cv != null) { // if session is set then proceed to add page two
									// data
					cv.getTrainingInfoList().add(training);
					httpSession.setAttribute("tempCv", cv);
					return "cv/createCvPageFive";
				} else { // if session isn't set already then redirect to previous
							// page
					return "redirect:/cv/create";
				}
			} else if (submitButton.contentEquals("Save and Next")) { // check if
																		// next
																		// button
																		// clicked
				if (cv != null) { // if session is set then proceed to add page two
									// data
					cv.getTrainingInfoList().add(training);
					httpSession.setAttribute("tempCv", cv);
				} else {
					return "redirect:/cv/create";
				}
			}

			return "redirect:/cv/create/p6";
		}
		@RequestMapping(value = "/create/p5/remove/{hashCode}", method = RequestMethod.GET)
		public String removeTrainingObjectFromSession(@PathVariable("hashCode") int hashCode, HttpSession httpSession) {
			Cv cv = (Cv) httpSession.getAttribute("tempCv");
			try {
				for (TrainingInfo training: cv.getTrainingInfoList()){
					if (training.hashCode() == hashCode) {
						cv.getTrainingInfoList().remove(training);
					}
				}
			} catch (ConcurrentModificationException e) {
					// ignore this bullshit!
			}
			httpSession.setAttribute("tempCv", cv);
			return "redirect:/cv/create/p5";
		}
		// END PAGE 5
		
		// PAGE 6
		
			@RequestMapping(value = "/create/p6", method = RequestMethod.GET)
			public String createCvP6Page() {
				return "cv/createCvPageSix";
			}
			
			@RequestMapping(value = "/create/p6", method = RequestMethod.POST)
			public String createCvP6(@ModelAttribute References reference, BindingResult bindingResult,
					@RequestParam("submitButton") String submitButton, HttpSession httpSession) {
				Cv cv = (Cv) httpSession.getAttribute("tempCv");
				// check if add new button clicked
				if (submitButton.contentEquals("Add New")) {
					if (cv != null) { // if session is set then proceed to add page two
										// data
						cv.getReferenceList().add(reference);
						httpSession.setAttribute("tempCv", cv);
						return "cv/createCvPageSix";
					} else { // if session isn't set already then redirect to previous
								// page
						return "redirect:/cv/create";
					}
				} else if (submitButton.contentEquals("Save and Next")) { // check if
																			// next
																			// button
																			// clicked
					if (cv != null) { // if session is set then proceed to add page two
										// data
						cv.getReferenceList().add(reference);
					} else {
						return "redirect:/cv/create";
					}
				}
				
				// save this complete cv object to database
				try {
					cv.setUser((User) httpSession.getAttribute("user"));
					cvRepository.saveAndFlush(cv);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}finally {
					httpSession.removeAttribute("tempCv");
				}

				return "redirect:/cv/create/p6?message=Successfully%20saved!";
			}
			@RequestMapping(value = "/create/p6/remove/{hashCode}", method = RequestMethod.GET)
			public String removeReferenceObjectFromSession(@PathVariable("hashCode") int hashCode, HttpSession httpSession) {
				Cv cv = (Cv) httpSession.getAttribute("tempCv");
				try {
					for (References reference: cv.getReferenceList()){
						if (reference.hashCode() == hashCode) {
							cv.getReferenceList().remove(reference);
						}
					}
				} catch (ConcurrentModificationException e) {
						// ignore this bullshit!
				}
				httpSession.setAttribute("tempCv", cv);
				return "redirect:/cv/create/p6";
			}
			// END PAGE 6	
}
