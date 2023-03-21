package multi.second.project.infra.code;

public enum Code {
	
	DOMAIN("http://localhost:8080"),
	SMTP_FROM("@naver.com"),
	
	// 배포시 servlet-context.xml의 resources 경로도 함께 수정
	STORAGE_PATH("C:\\post\\postImg\\");

	public String desc;
	
	Code(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return desc;
	}
	
}
