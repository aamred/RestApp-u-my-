package ua.com.spring.FirstRestApp.controllers;

//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Anton Muzhytskyi
 */


@RestController          //  @RestController = @Controller + ResponseBody(nad kazhdim metodom)
@RequestMapping("api")
public class FirstRESTController {
	
	                   //@ResponseBody
	@GetMapping("/sayHello")
	public String sayHello() {
		return "Hello Mother Fucker";
	}

}
