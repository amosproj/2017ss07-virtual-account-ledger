package de.fau.amos.virtualledger.server.banking.api.bankAccessEndpoint;

import de.fau.amos.virtualledger.server.banking.api.BankingApiDummy;

import javax.enterprise.context.RequestScoped;

/**
 * Created by Georg on 18.05.2017.
 */
@RequestScoped @BankingApiDummy
public class DummyBankAccessEndpoint implements BankAccessEndpoint {

    @Override
    public String testEndpointMethod1() {
        // do here the dummy logic
        return "DummyBankAccessEndpoint";
    }
}
