package MTZ.mountainz.domain.quiz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "quiz_id", nullable = false)
	private Long id;

	@Column(name = "quiz_question", nullable = false)
	private Long quizQuestion;

	@Column(name = "quiz_answer", nullable = false)
	private String quizAnswer;

	public Quiz(Long quizId) {
		this.id = quizId;
	}
}
