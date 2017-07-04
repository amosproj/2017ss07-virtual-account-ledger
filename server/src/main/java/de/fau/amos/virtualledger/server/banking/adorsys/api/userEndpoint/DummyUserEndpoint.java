package de.fau.amos.virtualledger.server.banking.adorsys.api.userEndpoint;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component

@Qualifier("dummy")
public class DummyUserEndpoint implements UserEndpoint {

    @Override
    public void createUser(String userId) {
        // nothing to do here
    }
}
