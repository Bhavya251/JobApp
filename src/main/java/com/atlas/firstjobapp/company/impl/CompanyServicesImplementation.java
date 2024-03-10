package com.atlas.firstjobapp.company.impl;

import com.atlas.firstjobapp.company.Company;
import com.atlas.firstjobapp.company.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServicesImplementation implements CompanyServices{

    CompanyRepository compRepo;

    public CompanyServicesImplementation(CompanyRepository compRepo) {
        this.compRepo = compRepo;
    }

    @Override
    public List<Company> getCompanies() {
        return compRepo.findAll();
    }

    @Override
    public void addCompany(Company company) {
        compRepo.save(company);
    }

    @Override
    public Company findCompanyID(Long cId) {
        return compRepo.findById(cId).orElse(null);
    }

    @Override
    public boolean deleteCompanyID(Long cId) {
        try {
            compRepo.deleteById(cId);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateCompany(Long cId, Company updatedComp) {
        Optional<Company> optionalCompany = compRepo.findById(cId);

        if (optionalCompany.isPresent()){
            Company company = optionalCompany.get();
            company.setName(updatedComp.getName());
            company.setCompDescription(updatedComp.getCompDescription());
            company.setJobs(updatedComp.getJobs());
            compRepo.save(company);
            return true;
        }
        return false;
    }
}
