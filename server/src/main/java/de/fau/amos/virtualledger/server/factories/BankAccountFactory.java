package de.fau.amos.virtualledger.server.factories;

import de.fau.amos.virtualledger.dtos.BankAccount;
import de.fau.amos.virtualledger.server.banking.model.BankAccountBankingModel;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Georg on 20.05.2017.
 */
@RequestScoped
public class BankAccountFactory {

    public BankAccount createBankAccount(BankAccountBankingModel bankingModel)
    {
        BankAccount bankAccount = new BankAccount(bankingModel.getId(), bankingModel.getNameHbciAccount(), bankingModel.getBankAccountBalance().getAvailableHbciBalance());
        return bankAccount;
    }

    public List<BankAccount> createBankAccounts(List<BankAccountBankingModel> bankingModelList)
    {
        List<BankAccount> bankAccountsResult = new ArrayList<BankAccount>();

        for (BankAccountBankingModel bankingModel: bankingModelList) {
            bankAccountsResult.add(this.createBankAccount(bankingModel));
        }
        return bankAccountsResult;
    }
}
