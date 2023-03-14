package multi.second.project.infra.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import multi.second.project.infra.code.ErrorCode;
import multi.second.project.infra.exception.AuthException;



public class GalleryAuthInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		String[] uriArr = request.getRequestURI().split("/");
		
		switch (uriArr[2]) {
		case "form":
			if(session.getAttribute("auth") == null) throw new AuthException(ErrorCode.UNAUTHORIZED_REQUEST);
			break;
		
		case "upload":
			if(session.getAttribute("auth") == null) throw new AuthException(ErrorCode.UNAUTHORIZED_REQUEST);
			break;
			
		case "modify":
			if(session.getAttribute("auth") == null) throw new AuthException(ErrorCode.UNAUTHORIZED_REQUEST);
			break;
			
		default:
			break;
		}

		return true;
	}

	
	
	
	
	
	
	
}
