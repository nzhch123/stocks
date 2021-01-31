package com.invest.strategy;

import com.invest.pojo.Mail;
import com.invest.strategy.impl.AbstractStrategy;
import com.invest.utils.MailUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MailedRecord implements ApplicationContextAware {
    @Autowired
    @Qualifier("GetStrategy")
    Map<String, AbstractStrategy> maps;
    public static Map<Class<? extends AbstractStrategy>, Map<String, Date>> classMap;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (!CollectionUtils.isEmpty(maps)) {
            for (String key :
                    maps.keySet()) {
                Map<String, Date> map = new HashMap<>();
                AbstractStrategy strategy = null;
                strategy = maps.get(key);
                classMap.put(strategy.getClass(), map);

            }
        }
    }
}
