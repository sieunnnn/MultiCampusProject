package multi.second.project.infra.code;

public enum Code {
	
	DOMAIN("http://localhost:8080"),
	SMTP_FROM(""),
	
	// 배포시 servlet-context.xml의 resources 경로도 함께 수정
	STORAGE_PATH("C:\\post\\postImg\\"),
//	PROFILE_STORAGE_PATH("C:\\Users\\junji\\joonhyung2\\MultiCampusProject\\project\\src\\main\\resources\\static\\img\\image\\");
	PROFILE_STORAGE_PATH("C:\\profile\\profileImg\\");

	public String desc;
	
	Code(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return desc;
	}

	// 위 코드에서 DOMAIN과 SMTP_FROM은 열거형 상수입니다.
	// DOMAIN 상수는 "http://localhost:8080" 문자열을,
	// SMTP_FROM 상수는 "example@example.com" 문자열을 desc 필드에 할당합니다.
	//
	//즉, 열거형 객체를 생성할 때 생성자를 통해 전달받은 값(desc)을 각 열거형 상수의 desc 필드에 할당하게 됩니다.
	// 그리고 이렇게 초기화된 desc 값을 toString() 메서드에서 반환하면, 해당 열거형 상수의 설명(desc)을 반환하게 됩니다.
	//
	//따라서, 위 예시에서 Code.DOMAIN.toString()을 호출하면 "http://localhost:8080" 문자열이 반환되고,
	// Code.SMTP_FROM.toString()을 호출하면 "example@example.com" 문자열이 반환됩니다.
}

