package de.fau.amos.virtualledger.server.banking.adorsys.api.userEndpoint;

import de.fau.amos.virtualledger.server.banking.adorsys.api.BankingApiUrlProvider;
import de.fau.amos.virtualledger.server.banking.adorsys.api.json.CreateUserJSONBankingModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.invoke.MethodHandles;


@Component
@Qualifier("default")
@Scope("request")
public class HttpUserEndpoint implements UserEndpoint {
    private final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


    @Autowired
    BankingApiUrlProvider urlProvider;


    @Override
    public void createUser(String userId) {

        CreateUserJSONBankingModel postBody = new CreateUserJSONBankingModel();
        postBody.setId(userId);

        // Create Jersey client
        Client client = ClientBuilder.newClient();

        String url = urlProvider.getUserEndpointUrl();
        WebTarget webTarget = client.target(url);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON_TYPE);
        Response response = invocationBuilder.post(Entity.entity(postBody, MediaType.APPLICATION_JSON_TYPE));

        if (response.getStatus() != 201) {
        	logger.warn("No connection to Adorsys Server!");
            throw new WebApplicationException("No connection to Adorsys Server!");
        }
    }

}
