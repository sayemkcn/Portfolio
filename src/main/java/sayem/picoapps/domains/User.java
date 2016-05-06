package sayem.picoapps.domains;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles = new ArrayList<>();
	@NotNull
	@Column(length = 300)
	private String password;

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

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", username=" + username + ", roles=" + roles + ", password="
				+ password + "]";
	}

}
