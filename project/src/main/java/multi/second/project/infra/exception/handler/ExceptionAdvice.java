package multi.second.project.infra.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import multi.second.project.infra.exception.AuthException;
import multi.second.project.infra.exception.HandlableException;

@Component

// @ControllerAdvice:  
//		com.mc.mvc 패키지 아래에 존재하는 Controller의 공통 작업(예외처리, 데이터바인딩)을 모듈화한 객체
@ControllerAdvice(basePackages = "multi.second.project")
public class ExceptionAdvice {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 지정한 예외가 Controller에서 발생하면 어노테이션이 선언된 메서드가 호출 된다.
	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(HandlableException.class)
	public String handlingHandlableException(HandlableException e, Model model) {
		logger.error("{}", e);
		model.addAttribute("msg", e.error.msg);
		model.addAttribute("redirect", e.error.redirect);
		return "common/alert-message";
	}
	
	// 지정한 예외가 Controller에서 발생하면 어노테이션이 선언된 메서드가 호출 된다.
	@ResponseStatus(code=HttpStatus.FORBIDDEN)
	@ExceptionHandler(AuthException.class)
	public String handlingHandlableException(AuthException e, Model model) {
		
		model.addAttribute("msg", e.error.msg);
		model.addAttribute("redirect", e.error.redirect);
		return "common/alert-message";
	}
	
	//DataAccessException : 스프링은 SQLException이 발생할 경우 해당 예외를 runtimeException인 
	//DataAccessExceotion으로 wrapping 하여 서비스레이어에 전달
	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DataAccessException.class)
	public String handlingDataAccessExceotuin(DataAccessException e, Model model) {
		
		// {} : 객체
		// logger.error("test {}", member.getUserName()) => hello hmd
		// logger에 두번째 매개변수로 Exception 객체를 넘기면 stacktrace를 출력해준다.
		logger.error("{}", e);
		model.addAttribute("msg", "데이터베이스 접속에 문제가 생겼습니다.");
		model.addAttribute("redirect", "/");
		return "common/alert-message";
	}
	
	
	
	
	
	

}
