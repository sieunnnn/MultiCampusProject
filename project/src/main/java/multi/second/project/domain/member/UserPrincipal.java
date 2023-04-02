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


	// User 클래스는 Spring Security에서 제공하는 UserDetails 인터페이스를 구현하고 있습니다.
	// Principal 클래스는 우리가 직접 만든 클래스이기 때문에 UserDetails 인터페이스를 구현하고 있지 않습니다.
	// 따라서 Principal을 UserDetails 인터페이스를 구현한 User 클래스로 감싸서 사용하기 위해서
	// UserPrincipal이라는 클래스를 만들어 사용하고 있는 것입니다.
	// 이렇게 함으로써 Principal 객체를 UserDetails 객체처럼 사용할 수 있게 되었습니다.


	
	
	
	
	
	
	
	
	
	
	
	

}
