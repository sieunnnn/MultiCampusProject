package multi.second.project.infra.config.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import multi.second.project.domain.member.UserPrincipal;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	
	//Authentication authentication == UsernamePasswordAuthenticationToken
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		log.info("{}", authentication);
		
		UserPrincipal userDetails = (UserPrincipal) userDetailsService.loadUserByUsername(authentication.getName());
		
		if(!passwordEncoder.matches((String)authentication.getCredentials(), userDetails.getPassword())) {
			throw new BadCredentialsException("비밀번호가 일치하지 않음");
		}
		
		UsernamePasswordAuthenticationToken token = 
				new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
		
		return token;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
	}

}
