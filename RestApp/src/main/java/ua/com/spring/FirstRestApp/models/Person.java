package ua.com.spring.FirstRestApp.models;

import java.time.LocalDateTime;
import org.hibernate.validator.constraints.NotEmpty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * @author Anton Muzhytskyi
 */

@Entity
@Table(name="Person")
public class Person {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	@NotEmpty(message="Name should not be empty")
	@Size(min=2, max=30, message = "Name should be between 2 to 30 characters")
	private String name;
	
	@Column(name="age")
	@Min(value = 0, message = "Age should be greater then 0")
	private int age;
	
	@Column(name="email")
	@Email
	@NotEmpty(message = "Email should not be empty")
	private String email;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Column(name = "update_at")
	private LocalDateTime updatedAt;
	
	@Column(name = "created_who")
	@NotEmpty
	private String createdWho;
	
	
	
	public Person () {}
	
	public Person( String name, int age, String email) {
		this.name = name;
		this.age = age;
		this.email = email;
	}


	public int getId() {return id;}
	public void setId(int id) {this.id = id;}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public int getAge() {return age;}
	public void setAge(int age) {this.age = age;}

	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	
	public LocalDateTime getCreatedAt() {return createdAt;}
	public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}

	public LocalDateTime getUpdatedAt() {return updatedAt;}
	public void setUpdatedAt(LocalDateTime updatedAt) {this.updatedAt = updatedAt;}

	public String getCreatedWho() {return createdWho;}
	public void setCreatedWho(String createdWho) {this.createdWho = createdWho;}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + ", email=" + email + "]";
	}

}
