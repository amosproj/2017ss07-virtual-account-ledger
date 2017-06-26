package de.fau.amos.virtualledger.android.api.auth;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * Created by Simon on 26.06.2017.
 */

public class MockedAuthenticationProviderTest {

    MockedAuthenticationProvider testProvider;

    @Before
    public void setUp() {
        testProvider = new MockedAuthenticationProvider();
    }

    @Test
    public void registerTest() {
        Observable<String> testObservable = testProvider.register("testEmail", "testpw", "testfirstname", "testlastname");
        Assertions.assertThat(testObservable).isNotNull();
    }



}