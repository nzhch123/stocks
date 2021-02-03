package com.invest.strategy;

import java.text.ParseException;

public interface Strategy  {

     boolean setContext() throws ParseException;

     boolean analyzeStrategy() throws ParseException;


}
