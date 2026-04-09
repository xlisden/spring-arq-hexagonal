package com.davinchicoder.spring_data_jpa_cero_a_experto.common.infrastructure.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
//@EnableScheduling
@EnableCaching
@EnableJpaAuditing
public class ApplicationConfig {
}
