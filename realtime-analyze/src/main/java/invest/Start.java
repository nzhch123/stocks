package invest;

import invest.utils.QuartzManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource({"config.properties"})
public class Start implements CommandLineRunner {
	private final String JOB_NAME = "invest_job";
    private final String TRIGGER_NAME = "invest_trigger";
    private final String JOB_GROUP_NAME = "invest_group";
    private final String TRIGGER_GROUP_NAME = "invest_trigger_group";
	@Value("${CRON}")
	private String cron;

	private static final Logger log = LoggerFactory.getLogger(Start.class);

	@Override
	public void run(String... args) throws Exception {

		try {
			log.info("任务开始");
			QuartzManager.addJob(JOB_NAME, JOB_GROUP_NAME, TRIGGER_NAME, TRIGGER_GROUP_NAME, InvestSendMailJob.class, cron);
		} catch (Exception e) {
			throw e;
		}
	}

}
