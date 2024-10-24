package com.jobapp.firstjobapp.Company.Impl;

import com.jobapp.firstjobapp.Company.Company;
import com.jobapp.firstjobapp.Company.CompanyRespository;
import com.jobapp.firstjobapp.Company.CompanyService;
import com.jobapp.firstjobapp.job.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {


    private CompanyRespository companyRespository;

    public CompanyServiceImpl(CompanyRespository companyRespository) {
        this.companyRespository = companyRespository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRespository.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long id) {


        Optional<Company> companyOptional=companyRespository.findById(id);

        if (companyOptional.isPresent())
        {
            Company companyToUpdate=companyOptional.get();
            companyToUpdate.setDescription(company.getDescription());
            companyToUpdate.setName(company.getName());
            companyToUpdate.setJobs(company.getJobs());
            companyRespository.save(companyToUpdate);
            return true;
        }
else{
            return false;
        }

    }

    @Override
    public void createCompany(Company company) {
        companyRespository.save(company);
    }

    @Override
    public Company getcompanybyId(Long id) {
        return companyRespository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteCompanyById(Long id) {

        if(companyRespository.existsById(id))
        {

            companyRespository.deleteById(id);
            return true;
        }
        else{

            return false;
        }
    }


}
