package br.com.duckvault.Exceptions.InvalidCompanyOperations;

public class CriticalUserNotInOwnershipGroup extends UserNotInOwnershipGroup {
    public CriticalUserNotInOwnershipGroup(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "[CRÍTICO] " + super.getMessage();
    }
}

