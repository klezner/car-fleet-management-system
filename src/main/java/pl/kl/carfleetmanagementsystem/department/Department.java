package pl.kl.carfleetmanagementsystem.department;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.kl.carfleetmanagementsystem.status.Status;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank(message = "Department name cannot be blank")
    private String name;
    @Column(nullable = false)
    @NotBlank(message = "Department abbreviation cannot be blank")
    private String abbreviation;
    private String comment;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Department status cannot be blank")
    private Status status;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    public LocalDateTime created;
    @Column(nullable = false)
    @UpdateTimestamp
    public LocalDateTime modified;
}
