package com.acapsule.accountcapsule.childfrags;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    @Bind(R.id.frame_date1)
    LinearLayout frameDate1;
    @Bind(R.id.dateFrame)
    RelativeLayout dateFrame;
    private View mView;
    private int colorNOFocus;
    private int colorFocusedSign;
    private int colorFocusedBtn;
    private boolean isSignMoneyFocused;
    private boolean isSignTypeFocused;
    private boolean isSignDateFocused;
    private float addDateFirstPlace;

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
        addDateFirstPlace = addDate.getY();
        initForDateFrameTransition();
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
                if(isSignDateFocused){
                    frameDate2.setVisibility(View.GONE);
                    isSignDateFocused = false;
                }else {
                    frameDate2.setVisibility(View.VISIBLE);
                    isSignDateFocused = true;
                }
                break;
        }
    }

    private void initForDateFrameTransition(){
        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.setDuration(300);
        layoutTransition.addTransitionListener(new LayoutTransition.TransitionListener() {
            @Override
            public void startTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                if (transitionType == LayoutTransition.APPEARING) {
                    tvDate1.setHint("");
                    AnimatorSet set = new AnimatorSet();
                    ObjectAnimator rotation = ObjectAnimator.ofFloat(addDate, "rotation", 45.0f).setDuration(300);
                    ObjectAnimator translation = ObjectAnimator.ofFloat(addDate, "translationY", frameDate1.getHeight()).setDuration(300);
                    ObjectAnimator gradient = ObjectAnimator.ofArgb(addDate.getDrawable().mutate(), "tint", colorNOFocus, colorFocusedBtn).setDuration(300);
                    ObjectAnimator gradientSign = ObjectAnimator.ofArgb(signDate.getDrawable().mutate(), "tint", colorNOFocus, colorFocusedSign).setDuration(300);
                    set.playTogether(rotation, translation, gradient, gradientSign);
                    set.start();
                } else if(transitionType == LayoutTransition.DISAPPEARING){
                    AnimatorSet set = new AnimatorSet();
                    ObjectAnimator rotation = ObjectAnimator.ofFloat(addDate, "rotation", 0.0f).setDuration(300);
                    ObjectAnimator translation = ObjectAnimator.ofFloat(addDate, "translationY", addDateFirstPlace).setDuration(300);
                    ObjectAnimator gradient = ObjectAnimator.ofArgb(addDate.getDrawable().mutate(), "tint", colorFocusedBtn, colorNOFocus).setDuration(300);
                    ObjectAnimator gradientSign = ObjectAnimator.ofArgb(signDate.getDrawable().mutate(), "tint", colorFocusedSign, colorNOFocus).setDuration(300);
                    set.playTogether(rotation, translation, gradient, gradientSign);
                    set.start();
                    set.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            clearBar(tvDate1);
                        }
                    });
                }
            }

            @Override
            public void endTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {

            }
        });
        dateFrame.setLayoutTransition(layoutTransition);
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
                break;
        }
    }

    private void barToFocused(final View viewBtn, View viewSign) {
        ObjectAnimator rotation = ObjectAnimator.ofFloat(viewBtn, "rotation", 45f).setDuration(300);
        ObjectAnimator gradient = ObjectAnimator.ofArgb(((ImageView) viewBtn).getDrawable().mutate(), "tint", colorNOFocus, colorFocusedBtn).setDuration(300);
        ObjectAnimator gradientSign = ObjectAnimator.ofArgb(((ImageView) viewSign).getDrawable().mutate(), "tint", colorNOFocus, colorFocusedSign).setDuration(300);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(rotation, gradient, gradientSign);
        set.start();
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                viewBtn.setEnabled(true);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                viewBtn.setEnabled(false);
            }
        });
    }

    private void barToUnFocused(final View viewBtn, View viewSign) {
        ObjectAnimator rotation = ObjectAnimator.ofFloat(viewBtn, "rotation", 0f).setDuration(300);
        ObjectAnimator gradient = ObjectAnimator.ofArgb(((ImageView) viewBtn).getDrawable().mutate(), "tint", colorFocusedBtn, colorNOFocus).setDuration(300);
        ObjectAnimator gradientSign = ObjectAnimator.ofArgb(((ImageView) viewSign).getDrawable().mutate(), "tint", colorFocusedSign, colorNOFocus).setDuration(300);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(rotation, gradient, gradientSign);
        set.start();
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                viewBtn.setEnabled(true);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                viewBtn.setEnabled(false);
            }
        });
    }

}
