package sayem.picoapps.controllers;

import java.text.SimpleDateFormat;
import java.util.ConcurrentModificationException;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
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

import sayem.picoapps.domains.Cv;
import sayem.picoapps.domains.embedded.EducationInfo;
import sayem.picoapps.domains.embedded.ExperienceInfo;

@Controller
@RequestMapping(value = "/cv")
public class CvController {

	// @InitBinder
	// public void initBinder(WebDataBinder binder){
	// binder.registerCustomEditor( Date.class,
	// new CustomDateEditor(new SimpleDateFormat("yyyy-dd-MM"), true, 10));
	// }

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createCvP1Page() {
		return "cv/createCvPageOne";
	}

	@RequestMapping(value = "/create/p1", method = RequestMethod.POST)
	public String createCvP1(@ModelAttribute Cv cv, BindingResult bindingResult, HttpSession httpSession) {
		if (bindingResult.hasErrors()) {
			System.out.println("BINDING RESULT_P1: \n" + bindingResult.toString());
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
}
