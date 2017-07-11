package de.fau.amos.virtualledger.android.views.savings.add;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.fau.amos.virtualledger.R;
import de.fau.amos.virtualledger.android.model.SavingsAccount;

public class AddSavingsAccountFinalDateMoneyUsedFragment extends AddSavingsAccountPage {
    @SuppressWarnings("unused")
    private static final String TAG = AddSavingsAccountFinalDateMoneyUsedFragment.class.getSimpleName();

    @BindView(R.id.add_savings_account_date_picker_enter_final_date)
    DatePicker datePicker;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.saving_accounts_add_fragment_final_date, container, false);
        TextView textView = (TextView) view.findViewById(R.id.add_savings_account_text_view_enter_final_date);
        textView.setText(R.string.add_savings_account_choose_finish_goal_date);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void fillInData(final SavingsAccount savingsAccount) {
        final int day = datePicker.getDayOfMonth();
        final int month = datePicker.getMonth();
        final int year = datePicker.getYear();
        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        savingsAccount.setFinalGoalFinishedDate(calendar.getTime());
    }
}
