package com.acapsule.accountcapsule;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

/**
 * Created by 大舅哥 on 2016/4/25.
 */
public class TAPUtils {
    public static final int FLOWIN = 1;
    public static final int FLOWOUT = 2;
    public static final int BORROW = 101;
    public static final int FIRSTTYPE = BORROW;
    public static final int BONUS = 102;
    public static final int EXTRAINCOME = 103;
    public static final int HONGBAO = 104;
    public static final int SALARY = 105;
    public static final int POCKETMONEY = 106;
    public static final int CATERING = 201;
    public static final int TRAFFIC = 202;
    public static final int TRIFLES = 203;
    public static final int ENTERTAIN = 204;
    public static final int SHOPPING = 205;
    public static final int LEND = 206;
    public static final int LASTTYPE = LEND;
    public static final ArrayList<Integer> ALL = new ArrayList<Integer>(){
        {
            add(1);
            add(2);
            add(101);
            add(102);
            add(103);
            add(104);
            add(105);
            add(106);
            add(201);
            add(202);
            add(203);
            add(204);
            add(205);
            add(206);
        }
    };
}
