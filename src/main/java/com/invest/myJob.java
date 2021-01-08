package com.invest;
import com.invest.strategy.Strategy;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class myJob implements Job,ApplicationContextAware {

    private Map<String, Strategy> map;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Strategy> map = applicationContext.getBeansOfType(Strategy.class);
    }
    public Map<String, Strategy> getMap() {
        return map;
    }
}
