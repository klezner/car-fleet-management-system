package pl.kl.carfleetmanagementsystem.company;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyMapper companyMapper;
    private final CompanyRepository companyRepository;

    @Transactional
    public void saveCompany(CompanyRequest companyRequest) {
        final Company company = companyMapper.mapCompanyRequestToCompany(companyRequest);
        companyRepository.save(company);
    }

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
