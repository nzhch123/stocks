package com.invest;

import com.invest.utils.QuartzManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

@Slf4j
public class Start implements CommandLineRunner {
	public static String JOB_NAME = "invest_job";
	public static String TRIGGER_NAME = "invest_trigger";
	public static String JOB_GROUP_NAME = "invest_group";
	public static String TRIGGER_GROUP_NAME = "invest_trigger_group";

	@Override
	public void run(String... args) throws Exception {

		try {
			log.info("任务开始");
			QuartzManager.addJob(JOB_NAME, JOB_GROUP_NAME, TRIGGER_NAME, TRIGGER_GROUP_NAME, InvestSendMailJob.class, "0/1 * * * * ?");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
