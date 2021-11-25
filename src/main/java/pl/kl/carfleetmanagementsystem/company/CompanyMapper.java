package pl.kl.carfleetmanagementsystem.company;

import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public CompanyResponse mapCompanyToCompanyResponse(Company company) {
        return CompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .status(company.getStatus())
                .build();
    }
}
