import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

public class STEST {
    public void go() throws Exception {
        // 首先，必需要取得一个Scheduler的引用
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        String time="0 51 11 ? * *";
        // jobs可以在scheduled的sched.start()方法前被调用

        // job 1将每隔20秒执行一次
        JobDetail job = new JobDetailImpl("job1", "group1", myJob.class);
        CronTrigger trigger = new CronTriggerImpl("trigger1", "group1");
        ((CronTriggerImpl) trigger).setCronExpression(time);
        Date ft = sched.scheduleJob(job, trigger);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        System.out.println(
                job.getKey() + " 已被安排执行于: " + sdf.format(ft) + "，并且以如下重复规则重复执行: " + trigger.getCronExpression());

        // job 2将每2分钟执行一次（在该分钟的第15秒)
        job = new JobDetailImpl("job2", "group1",myJob.class);
        trigger = new CronTriggerImpl("trigger2", "group1");
        ((CronTriggerImpl) trigger).setCronExpression(time);
        ft = sched.scheduleJob(job, trigger);
        System.out.println(
                job.getKey() + " 已被安排执行于: " + sdf.format(ft) + "，并且以如下重复规则重复执行: " + trigger.getCronExpression());

        // 开始执行，start()方法被调用后，计时器就开始工作，计时调度中允许放入N个Job
        sched.start();
        try {
            // 主线程等待一分钟
            Thread.sleep(60L * 1000L);
        } catch (Exception e) {
        }
        // 关闭定时调度，定时器不再工作
        sched.shutdown(true);
    }

    public static void main(String[] args) throws Exception {

        STEST test = new STEST();
        test.go();
    }

}
