package com.acapsule.accountcapsule.childfrags;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acapsule.accountcapsule.DB.DBData;
import com.acapsule.accountcapsule.R;
import com.acapsule.accountcapsule.TAPUtils;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemLongClick;
import butterknife.OnLongClick;
import butterknife.OnTouch;

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
    @Bind(R.id.imgType)
    ImageView imgType;
    @Bind(R.id.typeEarnBorrow)
    ImageView typeEarnBorrow;
    @Bind(R.id.typeEarnBonus)
    ImageView typeEarnBonus;
    @Bind(R.id.typeEarnExtraincom)
    ImageView typeEarnExtraincom;
    @Bind(R.id.typeEarnHongbao)
    ImageView typeEarnHongbao;
    @Bind(R.id.typeEarnSalary)
    ImageView typeEarnSalary;
    @Bind(R.id.typeEarnPocketMoney)
    ImageView typeEarnPocketMoney;
    @Bind(R.id.typeCostCatering)
    ImageView typeCostCatering;
    @Bind(R.id.typeCostTraffic)
    ImageView typeCostTraffic;
    @Bind(R.id.typeCostTrifles)
    ImageView typeCostTrifles;
    @Bind(R.id.typeCostEntertain)
    ImageView typeCostEntertain;
    @Bind(R.id.typeCostShopping)
    ImageView typeCostShopping;
    @Bind(R.id.typeCostLend)
    ImageView typeCostLend;
    @Bind(R.id.scrollTypes)
    RelativeLayout scrollTypes;
    private View mView;
    private int year;
    private int month;
    private int day;
    private Calendar calendar;
    private DBData dbData;


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
        dbData = new DBData();
        dbData.setMtype(-1); // 为了getTypeAfter/Before
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        tvMoney.setText("0");
        scrollTypes.setAlpha(0.0f);
        imgType.setAlpha(1.0f);
        imgType.setImageDrawable(getResources().getDrawable(R.drawable.whattype,null));
        imgType.setVisibility(View.VISIBLE);
        tvDate.setText(year + "-" + (month + 1) + "-" + day);
    }

    private void changeMoneyColorByPatten(){
        if(dbData.getPatten() == TAPUtils.FLOWIN){
            tvMoney.setTextColor(getResources().getColor(R.color.colorFlowIn));
        }
        else if(dbData.getPatten() == TAPUtils.FLOWOUT){
            tvMoney.setTextColor(getResources().getColor(R.color.colorFlowOut));
        }
    }

    private void showTypes() {
        scrollTypes.setVisibility(View.VISIBLE);
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator hide = ObjectAnimator.ofFloat(imgType, "alpha", 0.0f).setDuration(500);
        ObjectAnimator show = ObjectAnimator.ofFloat(scrollTypes, "alpha", 1.0f).setDuration(500);
        set.playTogether(hide, show);
        set.start();
        imgType.setVisibility(View.GONE);
    }

    private void showTypeChoosed(int typeid){
        imgType.setVisibility(View.VISIBLE);
        setTypeDrawableByTypeid(typeid);
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator hide = ObjectAnimator.ofFloat(scrollTypes, "alpha", 0.0f).setDuration(500);
        ObjectAnimator show = ObjectAnimator.ofFloat(imgType, "alpha", 1.0f).setDuration(500);
        set.playTogether(hide, show);
        set.start();
        scrollTypes.setVisibility(View.GONE);
    }

    private void setTypeDrawableByTypeid(int typeid){
        switch (typeid){
            case TAPUtils.BORROW:
                imgType.setImageDrawable(getResources().getDrawable(R.drawable.type_earn_borrow, null));
                break;
            case TAPUtils.BONUS:
                imgType.setImageDrawable(getResources().getDrawable(R.drawable.type_earn_bonus, null));
                break;
            case TAPUtils.EXTRAINCOME:
                imgType.setImageDrawable(getResources().getDrawable(R.drawable.type_earn_extraincom, null));
                break;
            case TAPUtils.HONGBAO:
                imgType.setImageDrawable(getResources().getDrawable(R.drawable.type_earn_hongbao, null));
                break;
            case TAPUtils.SALARY:
                imgType.setImageDrawable(getResources().getDrawable(R.drawable.type_earn_salary, null));
                break;
            case TAPUtils.POCKETMONEY:
                imgType.setImageDrawable(getResources().getDrawable(R.drawable.type_earn_pocketmoney, null));
                break;
            case TAPUtils.CATERING:
                imgType.setImageDrawable(getResources().getDrawable(R.drawable.type_cost_catering, null));
                break;
            case TAPUtils.TRAFFIC:
                imgType.setImageDrawable(getResources().getDrawable(R.drawable.type_cost_traffic, null));
                break;
            case TAPUtils.TRIFLES:
                imgType.setImageDrawable(getResources().getDrawable(R.drawable.type_cost_trifles, null));
                break;
            case TAPUtils.ENTERTAIN:
                imgType.setImageDrawable(getResources().getDrawable(R.drawable.type_cost_entertain, null));
                break;
            case TAPUtils.SHOPPING:
                imgType.setImageDrawable(getResources().getDrawable(R.drawable.type_cost_shopping, null));
                break;
            case TAPUtils.LEND:
                imgType.setImageDrawable(getResources().getDrawable(R.drawable.type_cost_lend, null));
                break;
        }
        dbData.setMtype(typeid);
        dbData.setPatten(typeid/100 == 1? TAPUtils.FLOWIN : TAPUtils.FLOWOUT);
        changeMoneyColorByPatten();
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

    private void getTypeAfter(){
        int typeAfter;
        if(dbData.getMtype() == -1){
            typeAfter = TAPUtils.FIRSTTYPE;
        }else if(dbData.getMtype() == TAPUtils.LASTTYPE){
            return;
        }else{
            typeAfter = TAPUtils.ALL.get(TAPUtils.ALL.indexOf(dbData.getMtype()) + 1);
        }
        setTypeDrawableByTypeid(typeAfter);
    }

    private void getTypeBefore(){
        int typeBefore;
        if(dbData.getMtype() == -1){
            typeBefore = TAPUtils.LASTTYPE;
        }
        else if(dbData.getMtype() == TAPUtils.FIRSTTYPE) {
            return;
        }
        else{
            typeBefore = TAPUtils.ALL.get(TAPUtils.ALL.indexOf(dbData.getMtype()) - 1);
        }
        setTypeDrawableByTypeid(typeBefore);
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

    @OnClick({R.id.tvDate, R.id.imgType, R.id.tvMoney,
            R.id.typeEarnBorrow, R.id.typeEarnBonus, R.id.typeEarnExtraincom, R.id.typeEarnHongbao, R.id.typeEarnSalary, R.id.typeEarnPocketMoney,
            R.id.typeCostCatering, R.id.typeCostTraffic, R.id.typeCostTrifles, R.id.typeCostEntertain, R.id.typeCostShopping, R.id.typeCostLend,
            R.id.arrowleft_money, R.id.arrowright_money, R.id.arrowleft_type, R.id.arrowright_type, R.id.arrowleft_date, R.id.arrowright_date})
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
            case R.id.typeEarnBorrow:
                showTypeChoosed(TAPUtils.BORROW);
                break;
            case R.id.typeEarnBonus:
                showTypeChoosed(TAPUtils.BONUS);
                break;
            case R.id.typeEarnExtraincom:
                showTypeChoosed(TAPUtils.EXTRAINCOME);
                break;
            case R.id.typeEarnHongbao:
                showTypeChoosed(TAPUtils.HONGBAO);
                break;
            case R.id.typeEarnSalary:
                showTypeChoosed(TAPUtils.SALARY);
                break;
            case R.id.typeEarnPocketMoney:
                showTypeChoosed(TAPUtils.POCKETMONEY);
                break;
            case R.id.typeCostCatering:
                showTypeChoosed(TAPUtils.CATERING);
                break;
            case R.id.typeCostTraffic:
                showTypeChoosed(TAPUtils.TRAFFIC);
                break;
            case R.id.typeCostTrifles:
                showTypeChoosed(TAPUtils.TRIFLES);
                break;
            case R.id.typeCostEntertain:
                showTypeChoosed(TAPUtils.ENTERTAIN);
                break;
            case R.id.typeCostShopping:
                showTypeChoosed(TAPUtils.SHOPPING);
                break;
            case R.id.typeCostLend:
                showTypeChoosed(TAPUtils.LEND);
                break;
            case R.id.arrowleft_money:
                tvMoney.setText(getMoneyMinus());
                break;
            case R.id.arrowright_money:
                tvMoney.setText(getMoneyPlus());
                break;
            case R.id.arrowleft_type:
                getTypeBefore();
                break;
            case R.id.arrowright_type:
                getTypeAfter();
                break;
            case R.id.arrowleft_date:
                tvDate.setText(getDayBefore());
                break;
            case R.id.arrowright_date:
                tvDate.setText(getDayAfter());
                break;
        }
    }


//    @OnTouch({R.id.typeEarnBorrow, R.id.typeEarnBonus, R.id.typeEarnExtraincom, R.id.typeEarnHongbao, R.id.typeEarnSalary, R.id.typeEarnPocketMoney,
//            R.id.typeCostCatering, R.id.typeCostTraffic, R.id.typeCostTrifles, R.id.typeCostEntertain, R.id.typeCostShopping, R.id.typeCostLend})
//    public boolean onTouch(View v, MotionEvent event) {
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                RelativeLayout.LayoutParams tmpParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
//                tmpParams.height = tmpParams.height + 10;
//                tmpParams.width = tmpParams.width + 10;
//                v.setLayoutParams(tmpParams);
//                return true;
//            case MotionEvent.ACTION_MOVE:
//                return true;
//            case MotionEvent.ACTION_UP:
//                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) v.getLayoutParams();
//                params.height = params.height -10;
//                params.width = params.width - 10;
//                v.setLayoutParams(params);
//                return true;
//        }
//        return true;
//    }
}
