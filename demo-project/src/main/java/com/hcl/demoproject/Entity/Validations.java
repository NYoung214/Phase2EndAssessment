package com.hcl.demoproject.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

public class Validations {
	
	public static List<String> isValid(String[] fields) {
		
		// **********************************************************
		//			Password Regex
		// ^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$ 						<-- minimum 8, no max, 1 letter, 1 number
		// ********** Regex for username validation ******************
		// ^(?=.{5,40}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$	<-- No spaces, dashes in between min 5, max 40
		// ^(?=[a-zA-Z0-9]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$ 			(<---- use this if the above does not work)
		// ***********************************************************
		//		First and Last Name Regex between 2 and 25
		// ^(?=.{2,25}$)(?![_.])(?!.*[_.]{2})[a-zA-Z]+(?<![_.])$		<-- letters only min 2, max 25
		// ***********************************************************
		

		
		List<String> errors = new ArrayList<>();
		
		Pattern userPattern = Pattern.compile("^(?=[a-zA-Z0-9]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");		
		Pattern passPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
		Pattern namePattern = Pattern.compile("^(?=.{2,25}$)(?![_.])(?!.*[_.]{2})[a-zA-Z]+(?<![_.])$");
		Matcher matcher = null;
		
		//check for empty fields
		for(int i = 0; i < fields.length/2; i++) {
			if(fields[i] == null || fields[i].length() < 1) {
				errors.add("<span style=\"color:red\"> "+fields[i+5]+" cannot be empty</span>");
			}
		}
		
		//if errors are found return them and do not continue validations
		if(!(errors.isEmpty())) {
			return errors;
		}

		// check for matching username and password
		if(fields[0].equalsIgnoreCase(fields[1])) {
			errors.add("<span style=\"color:red;\">User Name and Password cannot match</span>");
			return errors;
		}
		
		String userNameCheck = fields[0].toLowerCase();
		
		//username cannot contain password
		String passwordCheck = fields[1].toLowerCase();
		if(userNameCheck.contains(passwordCheck) || passwordCheck.contains(userNameCheck)) {
			errors.add("<span style=\"color:red;\">User Name cannot contain password</span>");
		}
		
		//username cannot contain First Name
		String firstNameCheck = fields[2].toLowerCase();
		if(userNameCheck.contains(firstNameCheck)) {
			errors.add("<span style=\"color:red;\">User Name cannot contain First Name</span>");
		}
		
		//username cannot contain Last name
		String lastNameCheck = fields[3].toLowerCase();
		if(userNameCheck.contains(lastNameCheck)) {
			errors.add("<span style=\"color:red;\">User Name cannot contain Last Name</span>");
		}
		
		//if errors are found return them and do not continue validations
		if(!(errors.isEmpty())) {
			return errors;
		}
		
		//compare to regex patterns
		//compare username
		boolean match;
		for(int i=0; i < 4; i++) {
			switch(i) {
			case 0:	// username check
				matcher = userPattern.matcher(fields[i]);
				match = matcher.find();
				if(!match) {
					errors.add("<span style=\"color:red;\">"+fields[i+5]+" must be between 5 and 40 characters and contain only letters and numbers.</span>");
				}
				break;
			case 1:	// password check
				matcher = passPattern.matcher(fields[i]);
				match = matcher.find();
				if(!match) {
					errors.add("<span style=\"color:red;\">"+fields[i+5]+" must be at least 8 characters and must contain at least one letter and one number</span>");
				}
				break;
			case 2:	// first name check
			case 3:	// last name check
				matcher = namePattern.matcher(fields[i]);
				match = matcher.find();
				if(!match) {
					errors.add("<span style=\"color:red;\">"+fields[i+5]+" can only contain letters</span>");
				}
				break;
			default:
				break;
			}

		}

		return errors;
	}


	
}
