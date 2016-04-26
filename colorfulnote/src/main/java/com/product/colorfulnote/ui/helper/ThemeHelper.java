package com.product.colorfulnote.ui.helper;

import com.product.colorfulnote.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/3/22 0022.
 * <p/>
 * From:色系表 http://www.mydiyclub.com/color/2_2.htm
 */
public class ThemeHelper {
    private static ThemeHelper ourInstance = new ThemeHelper();

    public static ThemeHelper getInstance() {
        return ourInstance;
    }

    private ThemeHelper() {
    }

    /**
     * 得到当前日期是星期几。
     *
     * @return 当为周日时，返回0，当为周一至周六时，则返回对应的1-6。
     */
    private int getCurrentDayOfWeek() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
    }

    private int getCurrentDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }


    /**
     * 获取星期几
     *
     * @return
     */
    public String getWeekly(Date date) {
        int dayOfWeek = getCurrentDayOfWeek(date);

        switch (dayOfWeek) {
            case 0:
                return String.format("星期%s", "天");
            case 1:
                return String.format("星期%s", "一");
            case 2:
                return String.format("星期%s", "二");
            case 3:
                return String.format("星期%s", "三");
            case 4:
                return String.format("星期%s", "四");
            case 5:
                return String.format("星期%s", "五");
            case 6:
                return String.format("星期%s", "六");
            default:
                break;
        }
        return String.format("星期%s", "天");
    }

    /**
     * 根据星期返回颜色
     *
     * @return
     */
    public int getTitleBgColor() {
        int dayOfWeek = getCurrentDayOfWeek();

        switch (dayOfWeek) {
            case 0:
                return R.color.red_200;
            case 1:
                return R.color.orange_200;
            case 2:
                return R.color.yellow_200;
            case 3:
                return R.color.green_200;
            case 4:
                return R.color.grass_200;
            case 5:
                return R.color.blue_200;
            case 6:
                return R.color.purple_200;
            default:
                break;
        }
        return R.color.red_200;
    }

    /**
     * 根据时光轴标题颜色
     *
     * @return
     */
    public int getItemBgColor() {
        int dayOfWeek = getCurrentDayOfWeek();

        switch (dayOfWeek) {
            case 0:
                return R.color.red_100;
            case 1:
                return R.color.orange_100;
            case 2:
                return R.color.yellow_100;
            case 3:
                return R.color.green_100;
            case 4:
                return R.color.grass_100;
            case 5:
                return R.color.blue_100;
            case 6:
                return R.color.purple_100;
            default:
                break;
        }
        return R.color.red_100;
    }

    /**
     * 根据时光轴标题背景色
     *
     * @return
     */
    public int getGroupIconBg() {
        int dayOfWeek = getCurrentDayOfWeek();

        switch (dayOfWeek) {
            case 0:
                return R.drawable.design_point_red;
            case 1:
                return R.drawable.design_point_orange;
            case 2:
                return R.drawable.design_point_yellow;
            case 3:
                return R.drawable.design_point_green;
            case 4:
                return R.drawable.design_point_grass;
            case 5:
                return R.drawable.design_point_blue;
            case 6:
                return R.drawable.design_point_purple;
            default:
                break;
        }
        return R.drawable.design_point_red;
    }

    /**
     * 获取按钮背景
     *
     * @return
     */
    public int getBtnColor() {
        int dayOfWeek = getCurrentDayOfWeek();

        switch (dayOfWeek) {
            case 0:
                return R.drawable.selector_btn_red;
            case 1:
                return R.drawable.selector_btn_orange;
            case 2:
                return R.drawable.selector_btn_yellow;
            case 3:
                return R.drawable.selector_btn_green;
            case 4:
                return R.drawable.selector_btn_grass;
            case 5:
                return R.drawable.selector_btn_blue;
            case 6:
                return R.drawable.selector_btn_purple;
            default:
                break;
        }
        return R.drawable.selector_btn_red;
    }
}
