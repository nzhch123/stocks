package com.invest.strategy;

import org.quartz.Job;
import org.springframework.stereotype.Component;

public interface Strategy  {

    public void sendEmail();
}
