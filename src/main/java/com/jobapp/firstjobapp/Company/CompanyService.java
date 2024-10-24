package com.jobapp.firstjobapp.Company;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();
    boolean updateCompany(Company company, Long id);
    void createCompany(Company company);
  Company getcompanybyId(Long id);
    boolean deleteCompanyById(Long id);
}
