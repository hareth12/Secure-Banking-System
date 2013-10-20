package asu.edu.sbs.web.corporatemanager;


import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import asu.edu.sbs.domain.SignUpEmployee;
import asu.edu.sbs.domain.Subscriber;
import asu.edu.sbs.web.trial.HomeController;

@Controller
public class CorporateController {

private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/corporate", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome to corporate page, locale is {}.", locale);
		
		return "corporate/corporate";
	}
	
	
	@RequestMapping(value="/corporate/op2",  method = RequestMethod.POST)
	public String getTest2(Locale locale, Model model) {
		logger.info("Welcome to corporate page, locale is {}.", locale);
		
		return "corporate/delete";
	}
	
	@RequestMapping(value="/corporate/op3",  method = RequestMethod.POST)
	public String getTest3(Locale locale, Model model) {
		logger.info("Welcome to corporate page, locale is {}.", locale);		
		return "corporate/transfer";
	}
	
	
	
}