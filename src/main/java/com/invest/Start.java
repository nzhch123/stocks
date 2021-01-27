package com.invest;

import com.invest.utils.QuartzManager;
import org.springframework.boot.CommandLineRunner;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Start implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
//        ScheduledExecutorService ses = Executors.newScheduledThreadPool(4);
//        ses.scheduleAtFixedRate(new Thread_timing(), 0, 30, TimeUnit.SECONDS);


       // QuartzManager.startJobs();
    }
}
