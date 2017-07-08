package de.fau.amos.virtualledger.android.api.contacts;

import java.util.List;

import de.fau.amos.virtualledger.android.api.RestApi;
import de.fau.amos.virtualledger.android.api.shared.CallWithToken;
import de.fau.amos.virtualledger.android.api.shared.RetrofitCallback;
import de.fau.amos.virtualledger.android.api.shared.TokenCallback;
import de.fau.amos.virtualledger.dtos.Contact;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Simon on 01.07.2017.
 */

public class HTTPContactsProvider implements ContactsProvider{

    private static final String TAG = "HTTPContactsProvider";

    private RestApi restApi;
    private CallWithToken callWithToken;

    public HTTPContactsProvider(RestApi restApi, final CallWithToken callWithToken) {
        this.restApi = restApi;
        this.callWithToken = callWithToken;
    }

    @Override
    public Observable<List<Contact>> getContacts() {

        final PublishSubject<List<Contact>> observable = PublishSubject.create();

        callWithToken.callWithToken(observable, new TokenCallback() {
            @Override
            public void onReceiveToken(final String token) {
                // got token
                restApi.getContacts(token).enqueue(new RetrofitCallback<>(observable));
            }
        });

        return observable;
    }


    @Override
    public Observable<Void> addContacts(Contact savingsAccount) {
        return null;
    }
}
