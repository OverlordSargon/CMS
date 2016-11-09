package com.zaico.cms.controllers.order;

/**
 * Created by nzaitsev on 09.11.2016.
 */
public class OrderDates {

    String orderDay;
    String orgerBeginHour;
    String orgerEndHour;

    public String getOrderDay() {
        return orderDay;
    }

    public void setOrderDay(String orderDay) {
        this.orderDay = orderDay;
    }

    public String getOrgerBeginHour() {
        return orgerBeginHour;
    }

    public void setOrgerBeginHour(String orgerBeginHour) {
        this.orgerBeginHour = orgerBeginHour;
    }

    public String getOrgerEndHour() {
        return orgerEndHour;
    }

    public void setOrgerEndHour(String orgerEndHour) {
        this.orgerEndHour = orgerEndHour;
    }
}
