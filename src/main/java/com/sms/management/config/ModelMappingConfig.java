package com.sms.management.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ModelMappingConfig {
  @Bean
  public ModelMapper getModelMapper() {
    return new ModelMapper();
  }
}
