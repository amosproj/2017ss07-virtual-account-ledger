package de.fau.amos.virtualledger.android.api.auth;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by sebastian on 14.05.17.
 */

public class MockedAuthenticationProvider implements AuthenticationProvider {

    private String token = "";

    @Override
    public Observable<String> register(String email, String password, String firstname, String lastname) {
        final PublishSubject observable = PublishSubject.create();
        observable.onNext("Registered (Dummy)");
        return observable;
    }

    @Override
    public Observable<String> login(String username, String password) {
        this.token = username+"_"+password;
        return Observable.just("Logged in (Dummy)");
    }

    @Override
    public Observable<String> logout() {
        this.token = "";
        return Observable.just("Logged out (Dummy)");
    }

    @Override
    public boolean isLoggedIn() {
        return this.token.length()==0;
    }

    @Override
    public Observable<String> getToken() {
        return Observable.just(this.token);
    }

    @Override
    public String getUserId() {
        return "test@test.de";
    }

    @Override
    public void persistLoginData(Context context) {

    }

    @Override
    public void deleteSavedLoginData(Context context) {

    }

    @Override
    public void tryLoadLoginData(Context context) {

    }

}
