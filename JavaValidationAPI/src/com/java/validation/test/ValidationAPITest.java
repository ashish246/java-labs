package com.java.validation.test;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.java.validation.Gender;
import com.java.validation.Member;
import com.java.validation.NameGroup;

public class ValidationAPITest {

	public static void main(String[] args) {

		Member member = new Member();
		member.setDateOfBirth(new Date());
		member.setEmailAddress("atripathi.intershop");
		member.setFirstName(null);
		//member.setFirstName("1Ashish");
		member.setLastName("Xripathi");
		member.setGender(Gender.MALE);

		// validate the input
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		/*Set<ConstraintViolation<Member>> violations = validator
				.validate(member);*/
		
		Set<ConstraintViolation<Member>> violations = validator
				.validate(member, NameGroup.class);

		for (ConstraintViolation<Member> violation : violations) {
			System.out.println("Error: "
					+ violation.getPropertyPath().toString() + " - "
					+ violation.getMessage());
		}
	}

}
