import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Entity
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable=false)
    private User user;

    private String mealName;

    @Enumerated(new EnumType(STRING))
    private String mealType;

    private localDate date;

    private Integer calories;
    private Float protein;
    private Float carbs;
    private Float fats;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum MealType {
        BREAKFAST,
        LUNCH,
        DINNER,
        SNACK
    }

}