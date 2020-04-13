package com.example.batchprocessing;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BatchProcessingApplication {

    public static void main(String[] args) throws Exception {
//        SpringApplication.run(BatchProcessingApplication.class, args);
        SpringApplication app = new SpringApplication(BatchProcessingApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        
        ConfigurableApplicationContext ctx = app.run(args);
        JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);

        if (args.length > 0) {
//            JobParameters jobParameters = new JobParametersBuilder()
//                    .addDate("date", new Date())
//                    .toJobParameters(); 
            Job job = ctx.getBean(args[0], Job.class);
            jobLauncher.run(job, new JobParameters());
        } else {
            System.out.println("XXXXXXXXXXXX");
        }
    }
}
