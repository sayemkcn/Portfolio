package sayem.picoapps.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import sayem.picoapps.domains.User;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username " + username + " not found");
		}
		return new SecurityUser(user);
	}

	class SecurityUser extends User implements UserDetails {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private User user;

		public SecurityUser(User user) {
			this.user = user;
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			List<String> roles = user.getRoles();
			for (int i = 0; i < roles.size(); i++) {
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roles.get(i));
				authorities.add(authority);
			}
			return authorities;
		}

		@Override
		public String getPassword() {
			return user.getPassword();
		}

		@Override
		public String getUsername() {
			return user.getUsername();
		}

		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return true;
		}
	}
}
