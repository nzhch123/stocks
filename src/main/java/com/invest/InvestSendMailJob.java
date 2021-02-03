package com.invest;

import com.invest.strategy.StrategyFactory;
import com.invest.utils.SpringContextJobUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class InvestSendMailJob implements Job {

    @lombok.SneakyThrows
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        StrategyFactory strategyFactory = (StrategyFactory) SpringContextJobUtil.getBean("strategyFactory");
        strategyFactory.executeStrategy();
    }
}
