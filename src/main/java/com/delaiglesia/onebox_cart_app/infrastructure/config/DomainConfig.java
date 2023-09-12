package com.delaiglesia.onebox_cart_app.infrastructure.config;

import com.delaiglesia.onebox_cart_app.domain.services.Service;
import com.delaiglesia.onebox_cart_app.domain.usecases.UseCase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
    basePackages = "com.delaiglesia.onebox_cart_app.domain",
    includeFilters = {
      @ComponentScan.Filter(type = FilterType.ANNOTATION, value = UseCase.class),
      @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Service.class)
    })
public class DomainConfig {}
