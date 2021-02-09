package invest.strategy;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import invest.strategy.impl.AbstractStrategy;

import java.util.Map;

@Configuration
public class StrategyClassGet {

    @Autowired
    ApplicationContext applicationContext;

    @Bean(name = "GetStrategy")
    public Map<String, AbstractStrategy> GetStrategy() {
        return applicationContext.getBeansOfType(AbstractStrategy.class);
    }
}

