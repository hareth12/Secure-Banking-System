package asu.edu.sbs.web.sales;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.activity.InvalidActivityException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import asu.edu.sbs.domain.SignUpEmployee;
import asu.edu.sbs.domain.SignUpExternalEmployee;
import asu.edu.sbs.domain.User;
import asu.edu.sbs.hr.service.HrDeptManager;
import asu.edu.sbs.sales.service.SalesDeptManager;
import asu.edu.sbs.service.TrialUserManager;

@Controller
@RequestMapping(value= "/signupemployee")
public class SalesManagerController {

	@Autowired
	SalesDeptManager salesmanager;
	ModelAndView savedMav;
	ModelAndView savedMav1;
		
		@RequestMapping(value = "sales/salesmanager", method = RequestMethod.GET)
		public String addnewSalesEmployee(Locale locale, Model model) {
			System.out.println("Inside Sales manager Controller .............");				
			return "sales/manager/manager";
		} 
		
		/*@RequestMapping(value = "sales/salesmanager", method = RequestMethod.POST)
		public String addnewsalesEmployeePost(Locale locale, Model model) {
			System.out.println("Inside sales manager post Controller .............");				
			return "sales/manager/salesmanager";
		}*/
		
		
		@RequestMapping(value = "/newsalesemployee", method = RequestMethod.POST)
		public ModelAndView newSalesEmployeeGet(Locale locale, Model model) {
			System.out.println("Inside Sales manager get Controller .............");							
			savedMav = new ModelAndView("sales/newsalesemployee", "signupemployee", new SignUpEmployee());
			
			return savedMav;
		}
		
		@RequestMapping(value = "/newsalesexternalemployee", method = RequestMethod.POST)
		public ModelAndView newSalesExternalEmployeeGet(Locale locale, Model model) {
			System.out.println("Inside Sales employee get Controller .............");							
			savedMav1 = new ModelAndView("sales/newsalesexternalemployee", "signupexternalemployee", new SignUpExternalEmployee());
			
			return savedMav1;
		}
		
		@RequestMapping(value = "/newsalesemployee/op1", method = RequestMethod.POST)
		public ModelAndView newSalesEmployeePost(@ModelAttribute @Valid SignUpEmployee employee, BindingResult result, final RedirectAttributes attributes) {
			System.out.println("INSIDE Sales manager post Controller .............");
			String message ;
			ModelAndView mav = new ModelAndView();
			try{				
				System.out.println("\n Inside Employee signup post controller");
				if(result.hasErrors())
				{
					//return new ModelAndView("hr/newhremployee", "signupemployee",employee);
					//return savedMav;
					return new ModelAndView("sales/manager/manager","signupemployee",employee);
				}		 
						
				mav.setViewName("signup/saveData");
				message= "Your request has been submitted for approval";
				employee.setDepartment("Sales");
				employee.setPassword("temppassword");
				salesmanager.addNewSalesEmployee(employee);
				mav.addObject("message", message);				
				return mav;
			}
		 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(e instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException )
			{
				message = "Username already Exists.Choose a different username";
				mav.addObject("message", message);
				mav.setViewName("signup/saveData");		
				return mav;
			} else
			{
				message = "Error in saving your data.Please try again";
				mav.addObject("message", message);
				mav.setViewName("signup/saveData");		
				return mav;					
			}
		  } 
		}
		
		@RequestMapping(value = "/deletesalesemployee/op1" ,method = RequestMethod.POST)
		public String deleteEmployeeGet(Model model,HttpServletRequest request)
		{
			return  ("sales/deletesalesemployee");
		}
			
		
		@RequestMapping(value = "/deletesalesemployee" ,method = RequestMethod.POST)
		public String deleteEmployeePost(Model model,HttpServletRequest request)
		{
			System.out.println("\n Inside delete employee post controller");
			String message = null ,userName;
			userName = request.getParameter("userNametext");
			int status;
			try{				
				status = salesmanager.getDeleteApprovalStatus(userName, "Sales");
				if(status==1 )
				{					
					message = "Employee "+ userName+ " has been deleted after approval of corporate level manager";
					salesmanager.deleteSalesEmployee(userName);					
				} else  if (status==0){
					message= "Employee "+ userName+ " delete request has not beeen approved by corporate level manager yet";											
				} else if (status==-1)
				{
					message= "Employee "+ userName+ " delete request has beeen sent for approval to corporate level manager";						
					salesmanager.insertDeleteRequesttoCM(userName,"Sales",false);
				}
				model.addAttribute("message", message);							
				return ("signup/saveData");
				
			} catch (Exception e) {
				if(e instanceof InvalidActivityException )
				{
					e.printStackTrace();		
					message = "Error occured in deleting employee .Please use valid username";
					model.addAttribute("message", message);							
					return ("signup/saveData");
				} else {
				// TODO Auto-generated catch block
				e.printStackTrace();						
				message = "Error occured in sending delete request";
				model.addAttribute("message", message);				
				return ("signup/saveData");
				}
			 }		
		}
		
		
		@RequestMapping(value = "/transfersalesemployee" ,method = RequestMethod.POST)
		public ModelAndView transferEmployeeGet(Model model,HttpServletRequest request)
		{								
			Map <String,String> department = new LinkedHashMap<String,String>();			
			department.put("HR", "HR department");
			department.put("TM", "Transaction Management department");
			department.put("IT", "IT & Tech Support department");
			department.put("CM", "Company Managment department");
			model.addAttribute("departmentList", department);			
			return new ModelAndView("sales/transfersalesemployee", "signupemployee", new SignUpEmployee());
		}
		
		@RequestMapping(value = "/transfersalesemployee/op1" ,method = RequestMethod.POST)
		public String transferSalesEmployee( SignUpEmployee employee,Model model,HttpServletRequest request)
		{
			System.out.println("\n Inside delete empployee post controller");
			String message,department = null ;
									
			try{												
				message= "Employee "+ request.getParameter("userNametext")+ " has been transfered";					
				salesmanager.updateDepartmentOfEmployee(request.getParameter("userNametext"), employee.getDepartment());
				model.addAttribute("message", message);							
				return ("signup/saveData");
				
			} catch (Exception e) {
				
				if(e instanceof InvalidActivityException )
				{
					e.printStackTrace();		
					message = "Error occured in transferring employee .Please use valid username";
					model.addAttribute("message", message);							
					return ("signup/saveData");
				} else {
				// TODO Auto-generated catch block
				e.printStackTrace();						
				message = "Error occured in sending transfer request";
				model.addAttribute("message", message);				
				return ("signup/saveData");
				}
			 }		
		}
	
}
