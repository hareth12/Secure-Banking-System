package asu.edu.sbs.web.trial;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import asu.edu.sbs.domain.SignUpEmployee;
import asu.edu.sbs.domain.SignUpUser;
import asu.edu.sbs.service.SignUpValidator;

@Controller
@RequestMapping(value= "/signupuser")
public class SignUpController {

	
	@Autowired
	private SignUpValidator validator ;
	
	private void initBinder(WebDataBinder binder)
	{
		binder.setValidator(validator);		
	}
	
	@RequestMapping(value = "/signup" ,method = RequestMethod.GET)
	public ModelAndView getData( )
	{
		System.out.println("\n Inside signup controller");		
		return new ModelAndView("signup", "signupuser", new SignUpUser());		
	}
	
	@RequestMapping(value = "/signupPost" ,method = RequestMethod.POST)
	public ModelAndView getDataPost(@ModelAttribute @Valid SignUpUser user, BindingResult result, final RedirectAttributes attributes)
	{
		System.out.println("\n Inside signup post controller");
		if(result.hasErrors())
		{
			return new ModelAndView("signup", "signupuser",user);
		}		 
		
		ModelAndView mav = new ModelAndView();
		String message = "Submitted for approval";
		mav.setViewName("saveData");
		
		attributes.addFlashAttribute("message", message);	
		return mav;
				
	}

	
	@RequestMapping(value = "/SignupEmployee" ,method = RequestMethod.GET)
	public ModelAndView getDataEmployee(Locale locale , Model model)
	{
		System.out.println("\n Inside Employee signup controller");		
		
		Map <String,String> department = new LinkedHashMap<String,String>();
		department.put("HR", "HR department");
		department.put("sales", "Sales department");
		department.put("TM", "Transaction Management department");
		department.put("IT", "IT & Tech Support department");
		department.put("CM", "Company Managment department");
		model.addAttribute("departmentList", department);
		return new ModelAndView("SignupEmployee", "signupuser", new SignUpEmployee());
	}
	
	@RequestMapping(value = "/SignupEmployeePost" ,method = RequestMethod.POST)
	public ModelAndView postDataEmployee(@ModelAttribute @Valid SignUpEmployee employee, BindingResult result, final RedirectAttributes attributes)
	{
		System.out.println("\n Inside Employee signup post controller");
		if(result.hasErrors())
		{
			return new ModelAndView("SignupEmployee", "signupuser",employee);
		}		 
		
		ModelAndView mav = new ModelAndView();
		String message = "Submitted for approval";
		mav.setViewName("saveData");
		
		attributes.addFlashAttribute("message", message);	
		return mav;
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