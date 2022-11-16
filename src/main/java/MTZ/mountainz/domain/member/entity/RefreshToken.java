package MTZ.mountainz.domain.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class RefreshToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "refreshToken", nullable = false)
	private String refreshToken;

	@Column(name = "userEmail", nullable = false)
	private String userEmail;

	public RefreshToken(String token, String email) {
		this.refreshToken = token;
		this.userEmail = email;
	}

	public RefreshToken updateToken(String token) {
		this.refreshToken = token;
		return this;
	}
}
