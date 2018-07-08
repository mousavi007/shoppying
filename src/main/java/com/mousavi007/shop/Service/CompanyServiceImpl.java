package com.mousavi007.shop.Service;

import com.mousavi007.shop.Commands.CompanyCommands;
import com.mousavi007.shop.Converter.CompanyConv;
import com.mousavi007.shop.Domain.Company;
import com.mousavi007.shop.Domain.Factor;
import com.mousavi007.shop.Repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> CompanyList() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompany(String name) {

        return companyRepository.findByCompanyName(name).get();
    }

    @Override
    public Company addCompany(CompanyCommands companyCommands, Byte[] image) {
       Company company=new Company();
        CompanyConv companyConv=new CompanyConv(company,companyCommands);
        company=companyConv.commandToCompany();
        company.setImage(image);
        return companyRepository.save(company);
    }
}
