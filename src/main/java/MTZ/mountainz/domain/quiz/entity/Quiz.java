package MTZ.mountainz.domain.quiz.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
