package com.invest.strategy;

import com.invest.strategy.Strategy;
import com.invest.strategy.impl.AbstractStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Map;
import java.util.Set;

@Configuration
public class StrategyClassGet {

    @Autowired
    ApplicationContext applicationContext;

    @Bean(name = "GetStrategy")
    public Map<String, AbstractStrategy> GetStrategy() {
        return applicationContext.getBeansOfType(AbstractStrategy.class);
    }
}

