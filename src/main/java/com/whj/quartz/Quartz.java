package com.whj.quartz;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configurable
@EnableScheduling
public class Quartz {
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job jobMethod;

    @Scheduled(fixedRate = 20000)
    public void run() {
        try {
            jobLauncher.run(jobMethod, new JobParameters());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
