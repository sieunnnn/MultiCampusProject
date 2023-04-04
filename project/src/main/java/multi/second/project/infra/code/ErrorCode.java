package multi.second.project.infra.code;

public enum ErrorCode {
	
	EXPRIATION_SIGNUP_TOKEN("이미 만료된 토큰 입니다.", "/member/signup"),
	FAILED_SEND_EMAIL("이메일 전송에 실패했습니다."),
	UNAUTHORIZED_REQUEST("권한이 없습니다.","/member/login"),
	HOST_UNAUTHORIZED_REQUEST("권한이 없습니다.","/planner/list"),
	GROUP_UNAUTHORIZED_REQUEST("권한이 없습니다.","/planner/list"),
	FAILED_UPLOAD_FILE("파일 업로드 작업 중 에러가 발생했습니다"),
	NOT_EXISTS("존재하지 않습니다."),
	FAILED_DELETE_FILE("파일 삭제 작업 중 문제가 발생하였습니다.");

	public String msg;
	public String redirect;
	
	private ErrorCode(String msg) {
		this.msg = msg;
		this.redirect = "/";
	}
	
	ErrorCode(String msg, String redirect) {
		this.msg = msg;
		this.redirect = redirect;
	}
	

}
