package com.java.validation;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Ashish Tripathi
 */
public class Member {

	private String lastName = null;
	private String firstName = null;
	private Gender gender = null;
	private String mEmailAddress = null;
	private Date dateOfBirth = null;

	public Member() {
	}

	@Size(max = 5, message = "Business name can not have more than 5 characters.")
	@NotNull(message = "First name is compulsory", groups=NameGroup.class)
	@NotBlank(message = "First name is compulsory")
	@Pattern(regexp = "[a-z-A-Z]*", message = "First name has invalid characters")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@NotNull(message = "Gender is compulsory")
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@NotNull(message = "Last name is compulsory",  groups=NameGroup.class)
	@NotBlank(message = "Last name is compulsory")
	// @Pattern(regexp = "[a-z-A-Z]*", message =
	// "Last name has invalid characters")
	@Pattern.List({
			@Pattern(regexp = "A.*", message = "Last name has invalid characters"),
			@Pattern(regexp = "T.*", message = "Last name has invalid characters") })
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Past(message = "Date of Birth must be the past")
	@NotNull
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Min(value = 18, message = "Age must be greater than or equal to 18")
	@Max(value = 150, message = "Age must be less than or equal to 150")
	public Integer getAge() {
		if (this.dateOfBirth != null) {
			// calculate age of member here
		}
		return null;
	}

	@NotNull(message = "Email Address is compulsory")
	@NotBlank(message = "Email Address is compulsory")
	@Email(message = "Email Address is not a valid format")
	public String getEmailAddress() {
		return mEmailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.mEmailAddress = emailAddress;
	}
}