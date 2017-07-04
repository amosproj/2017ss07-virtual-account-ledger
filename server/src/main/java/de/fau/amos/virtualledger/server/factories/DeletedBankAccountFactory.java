package de.fau.amos.virtualledger.server.factories;

import org.springframework.stereotype.Component;

import de.fau.amos.virtualledger.server.model.DeletedBankAccount;


@Component

public class DeletedBankAccountFactory {

    public DeletedBankAccount createDeletedBankAccount(String email, String bankAccessId, String bankAccountId)
    {
        DeletedBankAccount deletedBankAccount = new DeletedBankAccount();
        deletedBankAccount.setUserEmail(email);
        deletedBankAccount.setBankAccessId(bankAccessId);
        deletedBankAccount.setBankAccountId(bankAccountId);

        return deletedBankAccount;
    }
}
