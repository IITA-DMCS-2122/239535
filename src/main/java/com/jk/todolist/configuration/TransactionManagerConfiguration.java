package com.jk.todolist.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TransactionManagerConfiguration {
    @Bean
    @Primary
    public ChainedTransactionManager chainedTransactionManager(@Qualifier("primaryTransactionManager") PlatformTransactionManager primaryTransactionManager,
                                                               @Qualifier("analyticsTransactionManager") PlatformTransactionManager analyticsTransactionManager) {
        return new ChainedTransactionManager(primaryTransactionManager, analyticsTransactionManager);
    }
}
