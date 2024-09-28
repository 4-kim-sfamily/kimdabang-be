package com.kimdabang.kdbserver.batch.config;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public Job aggregateDataJob(JobRepository jobRepository, Step aggregateDataStep) {
        return new JobBuilder("aggregateDataJob", jobRepository)
                .start(aggregateDataStep)
                .build();
    }

    @Bean
    public Step aggregateDataStep(JobRepository jobRepository, Tasklet aggregateDataTasklet, PlatformTransactionManager transactionManager) {
        return new StepBuilder("aggregateDataStep", jobRepository)
                .tasklet(aggregateDataTasklet, transactionManager)
                .build();
    }
}