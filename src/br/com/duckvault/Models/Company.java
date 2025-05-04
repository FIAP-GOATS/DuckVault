package br.com.duckvault.Models;


import br.com.duckvault.Exceptions.InvalidCompanyOperations.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company {
    private String id;
    private String name;
    private List<User> owners = new ArrayList<>();
    private List<CryptoAccount> cryptoAccounts = new ArrayList<>();

    public Company(){}

    public Company(String name, List<User> owners){
        this.name = name;
        this.owners = new ArrayList<>(owners);
    }


    public void addOwner(User user) throws UserAlreadyOwnsThisCompany {
        if (!owners.contains(user)) {
            owners.add(user);
        } else {
            throw new UserAlreadyOwnsThisCompany("user is already in the ownership group of this company");
        }
    }

    public void addOwner(List<User> users) throws UserAlreadyOwnsThisCompany {
        for (User user : users) {
            addOwner(user); // Overload
        }
    }

    public void removeOwner(User user) throws UserNotInOwnershipGroup {
        if (owners.contains(user)) {
            owners.remove(user);
        } else {
            throw new UserNotInOwnershipGroup("User is not an owner of this company.");
        }
    }

    public void removeCriticalOwner(User user) throws UserNotInOwnershipGroup {
        if (!owners.contains(user)) {
            throw new CriticalUserNotInOwnershipGroup("Usuário importante não está no grupo de donos.");
        }
        owners.remove(user);
    }

    //Future: logic for the critical owner, the major admin of the company

    public void addCryptoAccount(CryptoAccount account) throws CryptoAccountAlreadyExists {
        if (!cryptoAccounts.contains(account)) {
            cryptoAccounts.add(account);
        } else {
            throw new CryptoAccountAlreadyExists("This crypto account is already associated with the company.");
        }
    }

    public void removeCryptoAccount(CryptoAccount account) throws CryptoAccountNotFound {
        if (cryptoAccounts.contains(account)) {
            cryptoAccounts.remove(account);
        } else {
            throw new CryptoAccountNotFound("This crypto account is not associated with the company.");
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getOwners() {
        return new ArrayList<>(owners); // cópia defensiva
    }

    public void setOwners(List<User> owners) {
        this.owners = owners;
    }

    public List<CryptoAccount> getCryptoAccounts() {
        return new ArrayList<>(cryptoAccounts); //  cópia defensiva
    }
}
