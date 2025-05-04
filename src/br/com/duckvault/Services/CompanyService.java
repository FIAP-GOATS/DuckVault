package br.com.duckvault.Services;

import br.com.duckvault.Models.Company;
import br.com.duckvault.Models.User;

import java.util.List;

public class CompanyService {

    public static Company registerCompany(String name, List<User> owners){
        Company company = new Company(name, owners);
        //Future: save the company on db
        return company;
    }

    //Future: Security logic for registering a company

}
