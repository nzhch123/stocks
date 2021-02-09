package invest.strategy;

import invest.pojo.Mail;
import invest.strategy.impl.AbstractStrategy;
import invest.utils.MailUtil;
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
    Map<String, AbstractStrategy> maps;

    public void executeStrategy() throws Exception {
        if (!CollectionUtils.isEmpty(maps)) {
            for (String key :
                    maps.keySet()) {
                AbstractStrategy strategy;
                strategy = maps.get(key);
                if (strategy.setContext()) {
                    Mail mail=strategy.getMail();
                    MailUtil.sendMessage(mail.getSubject(), mail.getContent());
                }
            }
        }
    }

}
