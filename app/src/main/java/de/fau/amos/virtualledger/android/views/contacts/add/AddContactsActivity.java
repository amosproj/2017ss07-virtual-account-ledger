package de.fau.amos.virtualledger.android.views.contacts.add;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.fau.amos.virtualledger.R;
import de.fau.amos.virtualledger.android.dagger.App;
import de.fau.amos.virtualledger.android.data.ContactsDataManager;
import de.fau.amos.virtualledger.android.views.menu.MainMenu;

/**
 * Created by Simon on 08.07.2017.
 */

public class AddContactsActivity extends AppCompatActivity {
   @SuppressWarnings("unused")
    private static final String tag = AddContactsActivity.class.getSimpleName();

    @Inject
    ContactsDataManager contactsDataManager;

    @BindView(R.id.email_addcontacts)
    EditText emailAdr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getApplication()).getNetComponent().inject(this);
        setContentView(R.layout.contacts_activity_add);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.contacts_add_button)
    void submit() {
        final String email = emailAdr.getText().toString();

        contactsDataManager.addContact(email);

        final Intent intent = new Intent(this, MainMenu.class);
        final Bundle bundle = new Bundle();
        bundle.putSerializable(MainMenu.EXTRA_STARTING_FRAGMENT, MainMenu.AppFragment.CONTACTS);
        startActivity(intent);

        finish();
    }

}
