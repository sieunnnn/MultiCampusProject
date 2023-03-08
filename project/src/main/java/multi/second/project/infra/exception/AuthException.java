package multi.second.project.infra.exception;

import multi.second.project.infra.code.ErrorCode;

public class AuthException extends HandlableException{
	
	private static final long serialVersionUID = -5765315660840876940L;

	public AuthException(ErrorCode error, Exception e) {
		super(error, e);
	}

	public AuthException(ErrorCode error) {
		super(error);
	}
	
	

}
