package multi.second.project.domain.member;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import multi.second.project.domain.member.dto.Principal;

@Getter
public class UserPrincipal extends User{
	
	private static final long serialVersionUID = 1L;
	
	private final Principal principal;

	public UserPrincipal(Principal principal) {
		super(	principal.getUserId(),
				principal.getPassword(),
				List.of(new SimpleGrantedAuthority(principal.getGrade())));
		
		this.principal = principal;
	}
	
	public static UserPrincipal getUserPrincipal() {
		return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	public String getUserId() {
		return principal.getUserId();
	}
	
	public String getPassword() {
		return principal.getPassword();
	}
	
	public String getEmail() {
		return principal.getEmail();
	}
	
	public String getGrade() {
		return principal.getGrade();
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	

}
