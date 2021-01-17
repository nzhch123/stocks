package com.invest.strategy;

import com.invest.pojo.Mail;

public interface Strategy  {

    public void analyzeStrategy();

    public Mail sendMessage();
}
