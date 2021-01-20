import com.invest.strategy.Strategy;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;


public class STEST implements ApplicationContextAware {

    @Autowired
    ApplicationContext applicationContext;
    //@Autowired
   // @Qualifier("GetStrategy")
    Map<String, Strategy> maps;

    @Test
    public void test() {
       // Map<String, Strategy> map=applicationContext.getBeansOfType(Strategy.class);
        System.out.println(maps.toString());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        maps=applicationContext.getBeansOfType(Strategy.class);
    }
}
