package it.menu.rava.MenuRava.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.menu.rava.MenuRava.model.IpAddress;
import it.menu.rava.MenuRava.model.Panino;
import it.menu.rava.MenuRava.service.IpAddressService;
import it.menu.rava.MenuRava.service.PaninoService;

@Controller
public class MainController {
	
	@Autowired
	private PaninoService paninoService;
	
	@Autowired
	private IpAddressService ipService;

	  @RequestMapping(value={"/home","/"}, method=RequestMethod.GET)
	  public String goHome(Model model, HttpServletRequest request) {
		  if(this.ipService.findByAddress(request.getRemoteAddr())!=null) {
		  IpAddress ip = this.ipService.findByAddress(request.getRemoteAddr());
          model.addAttribute("paniniConsigliatiDaIp", this.paninoService.containsPanino(ip));
		  }
		  else {
			  List<Long> tempo = new ArrayList<Long>();
		      tempo.add((long) 0);
	          model.addAttribute("paniniConsigliatiDaIp",tempo);
		  }
          model.addAttribute("panini", this.paninoService.all());
		  model.addAttribute("numeroPanini", this.paninoService.count());
		  return "index.html";
	  }
	  
	 @RequestMapping(value={"/incrementa/{id}"}, method=RequestMethod.GET)
	  public String incrementa(Model model,@PathVariable Long id, HttpServletRequest request) {
		  Panino panino = this.paninoService.findById(id);
		  if(this.ipService.findByAddress(request.getRemoteAddr()) == null) {
			  Long temp = panino.getNumeroConsigliati();
				 panino.setNumeroConsigliati(temp+1);
			     IpAddress nuovo = new IpAddress();
			     nuovo.setIpaddress(request.getRemoteAddr());
			     nuovo.addPanino(panino);
			     panino.add(nuovo);
			     this.ipService.save(nuovo);
			     this.paninoService.save(panino);
		  }
		  else {
		  IpAddress ipadd = this.ipService.findByAddress(request.getRemoteAddr());
		  if(!this.ipService.exists(ipadd.getId(),panino.getId())) {
			  Long temp = panino.getNumeroConsigliati();
				 panino.setNumeroConsigliati(temp+1);
			     ipadd.setIpaddress(request.getRemoteAddr());
			     ipadd.addPanino(panino);
			     panino.add(ipadd);
			     this.ipService.save(ipadd);
			     this.paninoService.save(panino);
		  }
		  }
		 
		  IpAddress ip = this.ipService.findByAddress(request.getRemoteAddr());
          model.addAttribute("panini", this.paninoService.all());
          model.addAttribute("paniniConsigliatiDaIp", this.paninoService.containsPanino(ip));
		  model.addAttribute("numeroPanini", this.paninoService.count());
		  return "index.html";
	  }
	 
	 
	 @RequestMapping(value={"/decrementa/{id}"}, method=RequestMethod.GET)
	  public String decrementa(Model model,@PathVariable Long id, HttpServletRequest request) {
		 IpAddress ipadd = this.ipService.findByAddress(request.getRemoteAddr());
		 Panino panino = this.paninoService.findById(id);
		 Long temp = panino.getNumeroConsigliati();
		 temp = temp-1;
		 panino.remove(ipadd);
		 panino.setNumeroConsigliati(temp);
		 ipadd.removePanino(panino);
		 this.ipService.save(ipadd);
		 this.paninoService.save(panino);
		
         model.addAttribute("panini", this.paninoService.all());
         model.addAttribute("paniniConsigliatiDaIp", this.paninoService.containsPanino(ipadd));
		 model.addAttribute("numeroPanini", this.paninoService.count());
		  return "index.html";
	 }
	 
	 

	
}
