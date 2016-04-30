package sayem.picoapps.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sayem.picoapps.domains.User;
import sayem.picoapps.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public boolean isUserRegistered(String username) {
		if (userRepository.findUserByUsername(username) != null) {
			return true;
		}
		return false;
	}

	public boolean isPasswordValidForUser(String username, String password) {
		User user = userRepository.findUserByUsername(username);
		if (user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}
}
