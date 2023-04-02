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


	
	//Spring Security에서는 인증된 사용자와 관련된 정보를 저장하고 관리하는 SecurityContext라는 객체를 제공합니다.
	// SecurityContext는 ThreadLocal을 이용하여 현재 스레드와 관련된 보안 정보를 저장합니다.
	// SecurityContextHolder는 이러한 SecurityContext 객체를 제공하는 유틸리티 클래스입니다.
	//
	//예를 들어, 사용자가 로그인하여 인증되면 SecurityContext 객체가 생성됩니다.
	// 이 객체는 인증된 사용자 정보를 담고 있으며, 요청이 처리되는 동안 해당 정보를 유지하고 관리합니다.
	// 따라서, 다른 클래스에서 현재 인증된 사용자 정보에 접근해야 할 때, SecurityContextHolder를 사용하여 해당 정보를 가져올 수 있습니다.
	
	
	
	
	
	
	
	
	
	

}
