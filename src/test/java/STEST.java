import com.invest.strategy.Strategy;
import com.invest.strategy.impl.AbstractStrategy;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;


public class STEST  {

    @Autowired
    ApplicationContext applicationContext;
    //@Autowired
   // @Qualifier("GetStrategy")
    Map<String, AbstractStrategy> maps;

    @Test
    public void test() {
       // Map<String, Strategy> map=applicationContext.getBeansOfType(Strategy.class);
        maps=applicationContext.getBeansOfType(AbstractStrategy.class);
        System.out.println(1);
        System.out.println(maps.toString());
    }
    @Test
    //@Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        maps=applicationContext.getBeansOfType(AbstractStrategy.class);
        System.out.println(1);
    }
}
