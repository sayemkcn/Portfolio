package sayem.picoapps.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import sayem.picoapps.domains.Portfolio;
import sayem.picoapps.repositories.PortfolioRepository;
import sayem.picoapps.services.PortfolioService;

@Controller
@RequestMapping(value = "/portfolio")
public class PortfolioController {
	@Autowired
	@Qualifier(value = "portfolioService")
	private PortfolioService portfolioService;
	@Autowired
	private PortfolioRepository portfolioRepository;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String showProjects(Model model) {
		model.addAttribute("portfolioList", portfolioRepository.findAll());
		return "portfolio/showProjectList";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String newProject() {
		return "portfolio/addNewPortfolioProject";
	}

	@RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
	@ResponseBody
	public byte[] getImage(@PathVariable("id") Long id) {
		return portfolioRepository.getOne(id).getImage();
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String showSingleProject(@PathVariable("id") Long id, Model model) {
		model.addAttribute("portfolio", portfolioRepository.getOne(id));
		return "portfolio/showProject";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createProject(@ModelAttribute Portfolio portfolio, BindingResult bindingResult,
			@RequestParam("image") MultipartFile multipartFile, Model model) throws IOException {
		if (bindingResult.hasErrors()) {
			System.out.println("BINDING ERROR: " + bindingResult.toString());
		}
		if (portfolioService.isImageValid(multipartFile)) {
			portfolio.setImage(multipartFile.getBytes());
		} else {
			model.addAttribute("message", "Invalid Image!");
		}
		// check if user provided a valid link
		if (!(portfolio.getUrl().startsWith("http://") || portfolio.getUrl().startsWith("https://"))) {
			portfolio.setUrl("http://" + portfolio.getUrl());
		}
		portfolioRepository.saveAndFlush(portfolio);
		model.addAttribute("message", "Successfully saved project!");

		return "portfolio/addNewPortfolioProject";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editProjectPage(@PathVariable("id") Long id, Model model) {
		model.addAttribute("editObject", portfolioRepository.getOne(id));
		return "portfolio/addNewPortfolioProject";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String editProject(@ModelAttribute Portfolio portfolio, BindingResult bindingResult,
			@RequestParam("image") MultipartFile multipartFile, @PathVariable("id") Long id, Model model)
			throws IOException {
		if (bindingResult.hasErrors()) {
			System.out.println("BINDING ERROR-EDIT: " + bindingResult.toString());
		}
		if (portfolioService.isImageValid(multipartFile)) {
			portfolio.setImage(multipartFile.getBytes());
			portfolio.setId(id);
			// check if user provided a valid link
			if (!(portfolio.getUrl().startsWith("http://") || portfolio.getUrl().startsWith("https://"))) {
				portfolio.setUrl("http://" + portfolio.getUrl());
			}
			portfolioRepository.saveAndFlush(portfolio);
			return "redirect:/portfolio/" + id + "?message=Successfully%20Updated!";
		} else {
			model.addAttribute("message", "Invalid Image!");
		}
		return "portfolio/addNewPortfolioProject";
	}

}
