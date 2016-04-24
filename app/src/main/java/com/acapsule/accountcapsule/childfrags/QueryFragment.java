package com.acapsule.accountcapsule.childfrags;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acapsule.accountcapsule.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class QueryFragment extends Fragment {

    @Bind(R.id.cardviewQueryFrame)
    CardView cardviewQueryFrame;
    @Bind(R.id.signMoney)
    ImageView signMoney;
    @Bind(R.id.addMoney)
    ImageView addMoney;
    @Bind(R.id.signType)
    ImageView signType;
    @Bind(R.id.addType)
    ImageView addType;
    @Bind(R.id.signDate)
    ImageView signDate;
    @Bind(R.id.addDate)
    ImageView addDate;
    @Bind(R.id.frame_date2)
    LinearLayout frameDate2;
    @Bind(R.id.etMoney)
    TextInputEditText etMoney;
    @Bind(R.id.tvDate1)
    TextView tvDate1;
    @Bind(R.id.tvDate2)
    TextView tvDate2;
    @Bind(R.id.removeDate)
    ImageView removeDate;
    @Bind(R.id.tvType)
    TextView tvType;
    @Bind(R.id.cardFrame)
    LinearLayout cardFrame;
    @Bind(R.id.scrollType)
    HorizontalScrollView scrollType;
    private View mView;
    private int colorNOFocus;
    private int colorFocusedSign;
    private int colorFocusedBtn;
    private boolean isSignMoneyFocused;
    private boolean isSignTypeFocused;

    public QueryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_query, container, false);
        ButterKnife.bind(this, mView);
        colorNOFocus = getResources().getColor(R.color.colorDivider);
        colorFocusedSign = getResources().getColor(R.color.colorAccent);
        colorFocusedBtn = getResources().getColor(R.color.colorFocusedBtn);
        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.addMoney, R.id.addType, R.id.addDate, R.id.removeDate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addMoney:
                if (isSignMoneyFocused) {
                    barToUnFocused(view, signMoney);
                    clearBar(etMoney);
                    isSignMoneyFocused = false;
                } else {
                    barToFocused(view, signMoney);
                    etMoney.setHint("");
                    etMoney.setEnabled(true);
                    isSignMoneyFocused = true;
                }
                break;
            case R.id.addType:
                if (isSignTypeFocused) {
                    barToUnFocused(view, signType);
                    clearBar(tvType);
                    isSignTypeFocused = false;
                } else {
                    barToFocused(view, signType);
                    isSignTypeFocused = true;
                }
                break;
            case R.id.addDate:
                barToFocused(removeDate, signDate);
                addDate.setAlpha(0.0f);
                addDate.setRotation(45f);
                tvDate1.setHint("");
                addDate.setEnabled(false);
                frameDate2.setVisibility(View.VISIBLE);
                break;
            case R.id.removeDate:
                barToUnFocused(removeDate, signDate);
                AnimatorSet set = new AnimatorSet();
                set.playTogether(
                        ObjectAnimator.ofFloat(addDate, "alpha", 0.0f, 1.0f).setDuration(200),
                        ObjectAnimator.ofFloat(addDate, "rotation", -45.0f).setDuration(200)
                );
                set.start();
                addDate.setEnabled(true);
                frameDate2.setVisibility(View.GONE);
                clearBar(tvDate1);
                break;
        }
    }

    private void clearBar(View view) {
        switch (view.getId()) {
            case R.id.etMoney:
                etMoney.setText("");
                etMoney.setHint("金      额");
                etMoney.setEnabled(false);
                break;
            case R.id.tvType:
                tvType.setVisibility(View.GONE);
                scrollType.setVisibility(View.VISIBLE);
                break;
            case R.id.tvDate1:
                tvDate1.setText("");
                tvDate1.setHint("日      期");
                tvDate2.setText("");
                frameDate2.setVisibility(View.GONE);
                break;
        }
    }

    private void barToFocused(View viewBtn, View viewSign) {
        viewBtn.animate().rotationBy(45F).setDuration(200).start();
        ObjectAnimator.ofArgb(((ImageView) viewBtn).getDrawable().mutate(), "tint", colorNOFocus, colorFocusedBtn).setDuration(200).start();
        ObjectAnimator.ofArgb(((ImageView) viewSign).getDrawable().mutate(), "tint", colorNOFocus, colorFocusedSign).setDuration(200).start();
    }

    private void barToUnFocused(View viewBtn, View viewSign) {
        viewBtn.animate().rotationBy(-45F).setDuration(200).start();
        ObjectAnimator.ofArgb(((ImageView) viewBtn).getDrawable().mutate(), "tint", colorFocusedBtn, colorNOFocus).setDuration(200).start();
        ObjectAnimator.ofArgb(((ImageView) viewSign).getDrawable().mutate(), "tint", colorFocusedSign, colorNOFocus).setDuration(200).start();
    }

}
