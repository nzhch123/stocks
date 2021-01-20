package com.invest.strategy;

import com.invest.pojo.Mail;

public interface Strategy  {

     void setContext();

     void analyzeStrategy();

     Mail sendMessage();

}
