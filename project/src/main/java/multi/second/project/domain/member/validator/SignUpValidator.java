package multi.second.project.domain.member.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.AllArgsConstructor;
import multi.second.project.domain.member.dto.request.SignUpRequest;
import multi.second.project.domain.member.repository.MemberRepository;

@Component
@AllArgsConstructor
public class SignUpValidator implements Validator{
	
	private MemberRepository memberRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return SignUpRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		SignUpRequest form = (SignUpRequest) target;
		
		if(memberRepository.existsById(form.getUserId())) {
			errors.rejectValue("userId", "error.userId","이미 존재하는 아이디 입니다.");
		}
		
		//비밀번호가 8글자 이상의 숫자, 영문자, 특수문자 조합인지 확인
		if(!Pattern.compile("(?!.*[ㄱ-힣])(?=.*\\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9ㄱ-힣])(?=.{8,})").matcher(form.getPassword()).find()) {
			errors.rejectValue("password", "password.format", "비밀번호는 영문, 숫자, 특수문자 조합의 8자리 이상의 문자열입니다.");
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
