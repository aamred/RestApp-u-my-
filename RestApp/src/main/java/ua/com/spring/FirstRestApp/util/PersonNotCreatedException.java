package ua.com.spring.FirstRestApp.util;

/**
 * @author Anton Muzhytskyi
 */

public class PersonNotCreatedException extends RuntimeException{
	
	public PersonNotCreatedException(String msg) {
		super(msg);
	}
}
