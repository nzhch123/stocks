package com.invest.strategy;

import com.invest.strategy.Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Map;

@Configuration
public class StrategyClassGet {

    @Autowired
    ApplicationContext applicationContext;

    @Bean(name="GetStrategy")
    public Map<String, Strategy> GetStrategy() {
        return applicationContext.getBeansOfType(Strategy.class);
    }
}