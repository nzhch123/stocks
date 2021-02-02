package com.invest;

import com.invest.utils.QuartzManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

@Slf4j
public class Start implements CommandLineRunner {
	private final String JOB_NAME = "invest_job";
    private final String TRIGGER_NAME = "invest_trigger";
    private final String JOB_GROUP_NAME = "invest_group";
    private final String TRIGGER_GROUP_NAME = "invest_trigger_group";
    private final String cron = "0 0/1 9-15 ? * 2-6\n";

	@Override
	public void run(String... args) throws Exception {

		try {
			log.info("任务开始");
			QuartzManager.addJob(JOB_NAME, JOB_GROUP_NAME, TRIGGER_NAME, TRIGGER_GROUP_NAME, InvestSendMailJob.class, cron);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
