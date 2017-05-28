package de.fau.amos.virtualledger.android.bankingOverview.addBankAccess;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.fau.amos.virtualledger.R;
import de.fau.amos.virtualledger.android.api.auth.AuthenticationProvider;
import de.fau.amos.virtualledger.android.api.banking.BankingProvider;
import de.fau.amos.virtualledger.android.bankingOverview.localStorage.BankAccessCredentialDB;
import de.fau.amos.virtualledger.android.dagger.App;
import de.fau.amos.virtualledger.android.menu.MainMenu;
import de.fau.amos.virtualledger.dtos.BankAccessCredential;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddBankAccessActivity extends AppCompatActivity {

    private static final String TAG = "AddBankAccessActivity";

    @Inject
    BankingProvider bankingProvider;
    @Inject
    AuthenticationProvider authenticationProvider;

    @BindView(R.id.editText_addBankAccess_blz)
    EditText blzEditText;
    @BindView(R.id.editText_addBankAccess_loginName)
    EditText loginNameEditText;

    @BindView(R.id.editText_addBankAccess_pin)
    EditText pinEditText;

    @OnClick(R.id.button_addBankAccess_submit)
    void submit(View view) {
        final BankAccessCredential bankAccessCredential = new BankAccessCredential();
        final String bankCode = blzEditText.getText().toString();
        final String bankLogin = loginNameEditText.getText().toString();
        final String pin = pinEditText.getText().toString();
        bankAccessCredential.setBankcode(bankCode);
        bankAccessCredential.setBanklogin(bankLogin);
        bankAccessCredential.setPin(pin);

        bankingProvider.addBankAccess(bankAccessCredential)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String message) {
                        final AddBankAccessActivity context = AddBankAccessActivity.this;
                        Toast.makeText(context, "Access added successfully", Toast.LENGTH_SHORT).show();
                        new BankAccessCredentialDB(context).persist(authenticationProvider.getEmail(), bankCode, pin);
                        Intent intent = new Intent(context, MainMenu.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "Error occurred adding bank account.");
                        Toast.makeText(AddBankAccessActivity.this, "Access could not be added", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ((App) getApplication()).getNetComponent().inject(this);

        setContentView(R.layout.banking_overview_activity_add_bank_access);
        ButterKnife.bind(this);
    }
}