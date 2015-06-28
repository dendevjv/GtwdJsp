package ch5.task5;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

import shared.PersistentBase;

@Entity
public class Person extends PersistentBase {
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    
    @Pattern(regexp = "[A-Z][a-z]+", 
            message = "must start with capital letter and be 2 or more letters long")
    @NotNull
    public final String getFirstName() {
        return firstName;
    }
    public final void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    @Pattern(regexp = "[A-Z][a-z]+", 
            message = "must start with capital letter and be 2 or more letters long")
    public final String getLastName() {
        return lastName;
    }
    public final void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    @Min(value = 15, message = "cannot be less than 15")
    @Max(value = 115, message = "cannot be greater than 115")
    public final int getAge() {
        return age;
    }
    public final void setAge(int age) {
        this.age = age;
    }
    
    @Email(message = "must be valid email")
    public final String getEmail() {
        return email;
    }
    public final void setEmail(String email) {
        this.email = email;
    }
    
    
}
