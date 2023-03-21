package multi.second.project.domain.member;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import multi.second.project.domain.member.dto.Principal;
import multi.second.project.domain.member.dto.request.LoginRequest;
import multi.second.project.domain.member.dto.request.SignUpRequest;
import multi.second.project.domain.member.validator.SignUpValidator;
import multi.second.project.infra.code.ErrorCode;
import multi.second.project.infra.code.Role;
import multi.second.project.infra.exception.HandlableException;

@Controller
@RequestMapping("member")
@AllArgsConstructor
public class MemberController {
	
	private final SignUpValidator signUpValidator;
	private final MemberService memberService;
	
	@InitBinder("signUpRequest")
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(signUpValidator);
		System.out.println("@InitBinder(\"signUpRequest\") signUpValidator :  " +signUpValidator);
	}
	
	@GetMapping("signup")
	public void signUp(Model model) {
		model.addAttribute("signUpRequest", new SignUpRequest());
		
		System.out.println("@GetMapping(\"signup\") new SignUpRequest() :  " +new SignUpRequest());
		System.out.println("@GetMapping(\"signup\") model :  "+model);
	}
	
	@GetMapping("checkId")
	@ResponseBody
	public Map<String,Boolean> checkId(String userId) {
		System.out.println("@GetMapping(\"checkId\") memberService.existUser(userId) :  "+memberService.existUser(userId));
		return Map.of("exist", memberService.existUser(userId));
		
	}
	
	@PostMapping("signup")
	public String authentocateEmail(@Valid SignUpRequest form
								, Errors error
								, Model model
								, HttpSession session) {
		
		if(error.hasErrors()) {
			return "/member/signup";
		}
		
		session.setAttribute("signupForm", form);
		System.out.println("@PostMapping(\"signup\") form :  "+form);
		
		String authToken = UUID.randomUUID().toString();
		session.setAttribute("authToken", authToken);
		System.out.println("@PostMapping(\"signup\") authToken :  "+authToken);
		
		memberService.authenticateEmail(form, authToken);
		return "redirect:/member/signup";
	}
	
	@GetMapping("signupimpl/{authToken}")
	public String signUpImpl(
				HttpSession session,
				@PathVariable String authToken,
				@SessionAttribute(name = "authToken", required = false) String sessionToken,
				@SessionAttribute(name="signupForm", required = false) SignUpRequest form,
				Model model
			) {
			
		if(!authToken.equals(sessionToken)) throw new HandlableException(ErrorCode.EXPRIATION_SIGNUP_TOKEN);
		
		form.setGrade(Role.USER.desc());
		memberService.registNewMember(form);
		
		session.removeAttribute("authToken");
		session.removeAttribute("signupForm");
		
		return "redirect:/member/login";
	}
	
	@GetMapping("login")
	public void login(Model model) {
		model.addAttribute("loginRequest", new LoginRequest());
		System.out.println("@GetMapping(\"login\") new LoginRequest() :  "+new LoginRequest());
		System.out.println("@GetMapping(\"login\") model :  "+model);
			
	};
	
//	@PostMapping("login")
//	public String loginImpl(@Valid LoginRequest loginRequest
//							, Errors error
//							, HttpSession session
//							, RedirectAttributes redirectAttributes ) {
//		
//		if(error.hasErrors()) {
//			return "/member/login";
//		}
//
//		System.out.println("@PostMapping(\"login\") loginRequest :  "+loginRequest);
//		Principal principal = memberService.authenticateUser(loginRequest);
//		
//		if(principal == null) {
//			redirectAttributes.addFlashAttribute("msg", "아이디나 비밀번호가 일치하지 않습니다.");
//			return "redirect:/member/login";
//		}
//		System.out.println("@PostMapping(\"login\") principal :  "+principal);
//		session.setAttribute("auth", principal);
//		return "redirect:/dashboard";
//	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
