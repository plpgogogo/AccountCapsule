package com.acapsule.accountcapsule.childfrags;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acapsule.accountcapsule.R;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecordFragment extends Fragment {


    @Bind(R.id.tvMoney)
    TextView tvMoney;
    @Bind(R.id.tvDate)
    TextView tvDate;
    @Bind(R.id.capleft_money)
    ImageView capleftMoney;
    @Bind(R.id.signleft_money)
    ImageView signleftMoney;
    @Bind(R.id.arrowleft_money)
    ImageView arrowleftMoney;
    @Bind(R.id.capright_money)
    ImageView caprightMoney;
    @Bind(R.id.signright_money)
    ImageView signrightMoney;
    @Bind(R.id.arrowright_money)
    ImageView arrowrightMoney;
    @Bind(R.id.capleft_type)
    ImageView capleftType;
    @Bind(R.id.signleft_type)
    ImageView signleftType;
    @Bind(R.id.arrowleft_type)
    ImageView arrowleftType;
    @Bind(R.id.capright_type)
    ImageView caprightType;
    @Bind(R.id.signright_type)
    ImageView signrightType;
    @Bind(R.id.arrowright_type)
    ImageView arrowrightType;
    @Bind(R.id.capleft_date)
    ImageView capleftDate;
    @Bind(R.id.signleft_date)
    ImageView signleftDate;
    @Bind(R.id.arrowleft_date)
    ImageView arrowleftDate;
    @Bind(R.id.capright_date)
    ImageView caprightDate;
    @Bind(R.id.signright_date)
    ImageView signrightDate;
    @Bind(R.id.arrowright_date)
    ImageView arrowrightDate;
    @Bind(R.id.scrollTypes)
    LinearLayout scrollTypes;
    @Bind(R.id.imgType)
    ImageView imgType;
    private View mView;
    private int year;
    private int month;
    private int day;
    private Calendar calendar;


    public RecordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_record, container, false);
        ButterKnife.bind(this, mView);

        reset();
        return mView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void reset() {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        tvMoney.setText("0");
        tvDate.setText(year + "-" + (month + 1) + "-" + day);
    }

    private void showTypes(){
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator hide = ObjectAnimator.ofFloat(imgType, "alpha", 0.0f).setDuration(200);
        ObjectAnimator show = ObjectAnimator.ofFloat(scrollTypes, "alpha", 1.0f).setDuration(200);
        show.setStartDelay(200);
        set.playSequentially(hide, show);
        set.start();
    }

    private String getMoneyPlus() {
        String moneyString = tvMoney.getText().toString();
        if (moneyString.contains(".")) {
            double money = Double.parseDouble(moneyString);
            return String.valueOf(money + 1);
        } else {
            int money = Integer.parseInt(moneyString);
            return String.valueOf(money + 1);
        }

    }

    private String getMoneyMinus() {
        String moneyString = tvMoney.getText().toString();
        if (moneyString.contains(".")) {
            double money = Double.parseDouble(moneyString);
            return money - 1 <= 0 ? "0" : String.valueOf(money - 1);
        } else {
            int money = Integer.parseInt(moneyString);
            return money - 1 <= 0 ? "0" : String.valueOf(money - 1);
        }

    }

    private String getDayAfter() {
        String[] datepart = tvDate.getText().toString().split("-");
        int day = Integer.parseInt(datepart[2]);
        calendar.set(Calendar.DATE, day + 1);
        return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
    }

    private String getDayBefore() {
        String[] datepart = tvDate.getText().toString().split("-");
        int day = Integer.parseInt(datepart[2]);
        calendar.set(Calendar.DATE, day - 1);
        return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
    }

    @OnClick({R.id.tvDate, R.id.imgType, R.id.tvMoney, R.id.arrowleft_money, R.id.arrowright_money, R.id.arrowleft_type, R.id.arrowright_type, R.id.arrowleft_date, R.id.arrowright_date})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvMoney:

                break;
            case R.id.imgType:
                showTypes();
                break;
            case R.id.tvDate:
                DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tvDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                };
                new DatePickerDialog(getContext(), onDateSetListener, year, month, day).show();
                break;
            case R.id.arrowleft_money:
                tvMoney.setText(getMoneyMinus());
                break;
            case R.id.arrowright_money:
                tvMoney.setText(getMoneyPlus());
                break;
            case R.id.arrowleft_type:
                break;
            case R.id.arrowright_type:
                break;
            case R.id.arrowleft_date:
                tvDate.setText(getDayBefore());
                break;
            case R.id.arrowright_date:
                tvDate.setText(getDayAfter());
                break;
        }
    }
}
