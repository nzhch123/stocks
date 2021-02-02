package com.invest;
import com.invest.strategy.Strategy;
import com.invest.strategy.StrategyFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;


public class InvestSendMailJob implements Job {
    @Autowired
    StrategyFactory strategyFactory;
    @lombok.SneakyThrows
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        strategyFactory.executeStrategy();
    }
}
