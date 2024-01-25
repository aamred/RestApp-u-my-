package ua.com.spring.FirstRestApp.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import ua.com.spring.FirstRestApp.Services.PeopleService;
import ua.com.spring.FirstRestApp.dto.PersonDTO;
import ua.com.spring.FirstRestApp.models.Person;
import ua.com.spring.FirstRestApp.util.PersonErrorResponse;
import ua.com.spring.FirstRestApp.util.PersonNotCreatedException;
import ua.com.spring.FirstRestApp.util.PersonNotFoundException;

/**
 * @author Anton Muzhytskyi
 */

@RestController
@RequestMapping("/people")
public class PeopleController  {
	
	private final PeopleService peopleService;
	private final ModelMapper modelMapper;

	@Autowired
	public PeopleController(PeopleService peopleService, ModelMapper modelMapper) {
		this.peopleService = peopleService;
		this.modelMapper = modelMapper;
	}
	
	@GetMapping()
	public List<PersonDTO> getPeople(){
		return peopleService.findAll().stream().map(this::convertToPersonDTO)
				.collect(Collectors.toList());          //jackson   convertiruet v json  
	}
	
	@GetMapping("/{id}")
	public PersonDTO getPerson(@PathVariable("id") int id) {   //200 status
		return convertToPersonDTO(peopleService.findOne(id));        //jackson convertiruet v json
	}
	
	@PostMapping    
	public ResponseEntity<HttpStatus> create(@RequestBody  @Valid PersonDTO personDTO, 
			BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			
			StringBuilder errorMsg = new StringBuilder();
			
			List<FieldError> errors = bindingResult.getFieldErrors();
			for(FieldError error : errors) {
				errorMsg.append(error.getField())
					.append(" - ").append(error.getDefaultMessage()).append(" ; ");
			}
			throw new PersonNotCreatedException(errorMsg.toString());
		}
		
		peopleService.save(convertToPerson(personDTO));
		
		// send  responsse with empty body and status 200
		return ResponseEntity.ok(HttpStatus.OK);	
	}
	
	
	
	@ExceptionHandler       // eta anotaciya dlya otlovli isklyucheniy
	private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e){
		PersonErrorResponse response = new PersonErrorResponse(
				"Person with this id was not found",
				System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);   //not found = 404 status	
	}
	
	@ExceptionHandler       
	private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException e){
		PersonErrorResponse response = new PersonErrorResponse(
				e.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);   //not found = 404 status		
	}
	
	private Person convertToPerson(PersonDTO personDTO) {
		return modelMapper.map(personDTO, Person.class);
	}
	
	private PersonDTO convertToPersonDTO(Person person) {
		return modelMapper.map(person, PersonDTO.class);
	}
	
}
