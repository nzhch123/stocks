package invest.strategy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import invest.strategy.impl.AbstractStrategy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Component
public class MailedRecord implements ApplicationContextAware {
    @Autowired
    @Qualifier("GetStrategy")
    Map<String, AbstractStrategy> maps;
    public static Map<Class<? extends AbstractStrategy>, Map<String, Date>> classMap;

    @Override
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
