package de.fau.amos.virtualledger.server.banking.api;

/**
 *
 * Created by Georg on 18.05.2017.
 */

import de.fau.amos.virtualledger.server.banking.api.userEndpoint.UserEndpoint;
import de.fau.amos.virtualledger.server.banking.model.BankAccessBankingModel;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Class that can be used in other controllers to do interaction with banking api.
 */
@ApplicationScoped
public class BankingApiFacade {


    // injected by setter
    BankingApiBinder binder;

    public String getTest()
    {
        UserEndpoint endpoint = binder.getUserEndpoint();
        return endpoint.testEndpointMethod1();
    }

    public List<BankAccessBankingModel> getBankAccesses(String userId)
    {
        return binder.getBankAccessEndpoint().getBankAccesses(userId);
    }


    @Inject
    public void setBinder(BankingApiBinder binder) {
        this.binder = binder;
    }
}
