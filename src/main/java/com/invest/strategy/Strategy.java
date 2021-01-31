package com.invest.strategy;

import com.invest.pojo.Mail;

import java.text.ParseException;

public interface Strategy  {

     void setContext() throws ParseException;

     boolean analyzeStrategy() throws ParseException;


}
