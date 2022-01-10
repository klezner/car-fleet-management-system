package pl.kl.carfleetmanagementsystem.department;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.kl.carfleetmanagementsystem.company.Company;
import pl.kl.carfleetmanagementsystem.status.Status;
import pl.kl.carfleetmanagementsystem.vehicle.Vehicle;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

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
    @JoinColumn(nullable = false)
    @NotNull(message = "Company cannot be blank")
    @ManyToOne(optional = false)
    private Company company;
    @OneToMany(mappedBy = "department")
    private Set<Vehicle> vehicle;
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

    public void setActive() {
        this.status = Status.ACTIVE;
    }

    public void setInactive() {
        this.status = Status.INACTIVE;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
