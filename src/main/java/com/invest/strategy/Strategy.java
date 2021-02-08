package com.invest.strategy;

import java.io.IOException;
import java.text.ParseException;

public interface Strategy  {

     boolean setContext() throws ParseException, IOException;

     boolean analyzeStrategy() throws ParseException, IOException;


}
