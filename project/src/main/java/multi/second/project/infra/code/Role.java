package multi.second.project.infra.code;

public enum Role {
	
	USER("ROLE_USER"),
	ADMIN("ROLE_ADMIN");	

	private String grade;
	
	Role(String grade) {
		this.grade = grade;
	}
	
	public String desc() {
		return this.grade;
	}

}
