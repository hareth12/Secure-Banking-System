package asu.edu.sbs.web.trial;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import asu.edu.sbs.domain.SignUpUser;

@Controller
public class SignUpController {
	@Autowired SignUpUser user;
	
	@RequestMapping(value = "/signup" ,method = RequestMethod.GET)
	public ModelAndView getData(Locale locale , Model model)
	{
		System.out.println("\n Inside signup controller");		
		return new ModelAndView("signup", "command", new SignUpUser());
	}
	
	@RequestMapping(value = "/signup" ,method = RequestMethod.POST)
	public String getDataPost(@Valid SignUpUser user,BindingResult result, Model model)
	{
		System.out.println("\n Inside signup post controller");
		if (result.hasErrors())
		{
			return "signup";
		}
		model.addAttribute("message", "Successfully saved signup request");
		return "saveData";
	}
	
	@RequestMapping(value = "/saveData" ,method = RequestMethod.POST)
	public String saveData(Locale locale , Model model)
	{
		System.out.println("\n Inside savedata post controller");
		return "saveData";
	}
	
	@RequestMapping(value = "/saveData" ,method = RequestMethod.GET)
	public String saveDataPost(Locale locale , Model model)
	{
		System.out.println("\n Inside savedata Get controller");
		return "saveData";
	}

}
