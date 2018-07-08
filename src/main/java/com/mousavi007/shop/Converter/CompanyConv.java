package com.mousavi007.shop.Converter;

import com.mousavi007.shop.Commands.CompanyCommands;
import com.mousavi007.shop.Domain.Company;

public class CompanyConv {

    final Company company;
    final CompanyCommands companyCommands;

    public CompanyConv(Company company, CompanyCommands companyCommands) {
        this.company = company;
        this.companyCommands = companyCommands;
    }

    public Company commandToCompany()
    {
        company.setId(companyCommands.getId());
        company.setCompanyName(companyCommands.getCompanyName());
        return company;
    }
}
