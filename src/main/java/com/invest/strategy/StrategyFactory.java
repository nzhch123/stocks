package com.invest.strategy;

import com.invest.pojo.Mail;
import com.invest.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;

@Component
public class StrategyFactory {

    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    @Qualifier("GetStrategy")
    Map<String, Strategy> maps;

    public void executeStrategy() throws Exception {
        if (!CollectionUtils.isEmpty(maps)) {
            for (String key :
                    maps.keySet()) {

                Strategy strategy=maps.get(key);
                strategy.analyzeStrategy();

                Mail mail=strategy.sendMessage();
                MailUtil.sendMessage(mail.subject,mail.content);
            }
        }
    }

}
