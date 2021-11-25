package pl.kl.carfleetmanagementsystem.company;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyMapper companyMapper;
    private final CompanyRepository companyRepository;

    public List<CompanyResponse> fetchAllCompaniesResponses() {
        final List<Company> companyEntities = fetchAllCompanies();
        return companyEntities.stream()
                .map(companyMapper::mapCompanyToCompanyResponse)
                .collect(Collectors.toList());
    }

    private List<Company> fetchAllCompanies() {
        return companyRepository.findAll();
    }
}
