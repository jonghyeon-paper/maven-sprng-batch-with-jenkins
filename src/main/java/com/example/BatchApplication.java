package com.example;

import java.util.Arrays;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BatchApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BatchApplication.class, args);
        // 내장 was 실행 x
//        SpringApplication app = new SpringApplication(BatchProcessingApplication.class);
//        app.setWebApplicationType(WebApplicationType.NONE);
//        ConfigurableApplicationContext ctx = app.run(args);
    }
    
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Let's inspect the beans provided by Spring Boot:");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println("- " + beanName);
            }
            
            JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);
            if (args.length > 0) {
//                JobParameters jobParameters = new JobParametersBuilder()
//                        .addDate("date", new Date())
//                        .toJobParameters(); 
                Job job = ctx.getBean(args[0], Job.class);
                jobLauncher.run(job, new JobParameters());
            } else {
                System.out.println("XXXXXXXXXXXX");
            }
        };
    }
}
