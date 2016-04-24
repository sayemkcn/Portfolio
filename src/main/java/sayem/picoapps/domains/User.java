package sayem.picoapps.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
public class User extends BaseEntity {
	private String name;
	@Column(nullable = false, unique = true)
	@Email
	private String email;
	@Column(nullable = false, unique = true)
	@Size(min = 6, max = 20)
	private String username;
	@NotNull
	private String role;
	
	@OneToOne
	private Cv cv;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Cv getCv() {
		return cv;
	}

	public void setCv(Cv cv) {
		this.cv = cv;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", username=" + username + ", role=" + role + ", cv=" + cv
				+ "]";
	}

	

}
