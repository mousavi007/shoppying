package com.mousavi007.shop.Service;

import com.mousavi007.shop.Commands.CompanyCommands;
import com.mousavi007.shop.Domain.Company;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CompanyService {
    List<Company> CompanyList();
    Company getCompany(String name);
    Company addCompany(CompanyCommands companyCommands, Byte[] image);
}
