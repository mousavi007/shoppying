package com.mousavi007.shop.Repository;


import com.mousavi007.shop.Domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CompanyRepository extends JpaRepository<Company,Long>{


    List<Company> findAll();
    Optional<Company> findByCompanyName(String name);
}
