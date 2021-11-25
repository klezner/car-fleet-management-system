package pl.kl.carfleetmanagementsystem.company;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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

    public CompanyRequest fetchCompanyRequest(Long id) {
        final Company company = fetchCompanyById(id);
        return companyMapper.mapCompanyToCompanyRequest(company);
    }

    private Company fetchCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company with id: " + id + " not found!"));
    }

    public CompanyResponse fetchCompanyResponse(Long id) {
        final Company company = fetchCompanyById(id);
        return companyMapper.mapCompanyToCompanyResponse(company);
    }

    public void setActive(Long id) {
        final Company company = fetchCompanyById(id);
        company.setActive();
        companyRepository.save(company);
    }

    public void setInactive(Long id) {
        final Company company = fetchCompanyById(id);
        company.setInactive();
        companyRepository.save(company);
    }
}
