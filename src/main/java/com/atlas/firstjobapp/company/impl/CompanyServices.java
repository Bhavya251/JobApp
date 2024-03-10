package com.atlas.firstjobapp.company.impl;

import com.atlas.firstjobapp.company.Company;

import java.util.List;

public interface CompanyServices {
    List<Company> getCompanies();
    void addCompany(Company company);
    Company findCompanyID(Long cId);
    boolean deleteCompanyID(Long cId);
    boolean updateCompany(Long cId, Company updatedComp);
}
