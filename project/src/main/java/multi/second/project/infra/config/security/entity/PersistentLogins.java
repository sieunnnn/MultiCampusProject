package multi.second.project.infra.config.security.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PersistentLogins {
	
	@Id
	@Column(columnDefinition = "varchar(64) not null")
	private String series;
	
	@Column(columnDefinition = "varchar(64) not null")
	private String username;
	
	@Column(columnDefinition = "varchar(64) not null")
	private String token;
	
	@Column(columnDefinition = "timestamp not null")
	private LocalDateTime lastUsed;


}
