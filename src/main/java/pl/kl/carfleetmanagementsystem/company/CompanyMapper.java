package pl.kl.carfleetmanagementsystem.company;

import org.springframework.stereotype.Component;
import pl.kl.carfleetmanagementsystem.status.Status;

@Component
public class CompanyMapper {

    public Company mapCompanyRequestToCompany(CompanyRequest companyRequest) {
        return Company.builder()
                .id(companyRequest.getId())
                .name(companyRequest.getName())
                .status(setCompanyStatus(companyRequest.getStatus()))
                .build();
    }

    private Status setCompanyStatus(Status status) {
        if (status == Status.INACTIVE) {
            return Status.INACTIVE;
        } else {
            return Status.ACTIVE;
        }
    }

    public CompanyResponse mapCompanyToCompanyResponse(Company company) {
        return CompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .status(company.getStatus())
                .build();
    }
}
