package pl.kl.carfleetmanagementsystem.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.kl.carfleetmanagementsystem.status.Status;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank(message = "Company name cannot be blank")
    private String name;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    public LocalDateTime created;
    @Column(nullable = false)
    @UpdateTimestamp
    public LocalDateTime modified;
}
