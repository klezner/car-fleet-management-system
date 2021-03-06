package pl.kl.carfleetmanagementsystem.workshop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.kl.carfleetmanagementsystem.repair.Repair;
import pl.kl.carfleetmanagementsystem.status.Status;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Workshop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank(message = "Workshop name cannot be blank")
    private String name;
    @Column(nullable = false)
    @NotBlank(message = "Zip code cannot be blank")
    @Pattern(regexp = "[0-9]{2}-[0-9]{3}", message = "Incorrect format of zip code. Correct format is: XX-XXX")
    private String zipCode;
    @Column(nullable = false)
    @NotBlank(message = "City cannot be blank")
    private String city;
    @Column(nullable = false)
    @NotBlank(message = "Street name cannot be blank")
    private String street;
    @Column(nullable = false)
    @NotBlank(message = "Number of building cannot be blank")
    private String number;
    @OneToMany(mappedBy = "workshop")
    private Set<Repair> repairs;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Workshop status cannot be blank")
    private Status status;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    public LocalDateTime created;
    @Column(nullable = false)
    @UpdateTimestamp
    public LocalDateTime modified;

    protected void setActive() {
        status = Status.ACTIVE;
    }

    protected void setInactive() {
        status = Status.INACTIVE;
    }
}
