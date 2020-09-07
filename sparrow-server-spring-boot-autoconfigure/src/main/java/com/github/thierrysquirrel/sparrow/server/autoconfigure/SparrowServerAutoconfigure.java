/**
 * Copyright 2020 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.thierrysquirrel.sparrow.server.autoconfigure;

import com.github.thierrysquirrel.sparrow.server.autoconfigure.constant.ComponentScanConstant;
import com.github.thierrysquirrel.sparrow.server.init.*;
import com.github.thierrysquirrel.sparrow.server.mapper.builder.CacheTemplateBuilder;
import com.github.thierrysquirrel.sparrow.server.mapper.template.SparrowTopicEntityCacheTemplate;
import com.github.thierrysquirrel.sparrow.server.service.AdministrationService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: SparrowServerAutoconfigure
 * Description:
 * date: 2020/6/8 17:30
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Configuration
@EnableConfigurationProperties(SparrowServerProperties.class)
@ComponentScan(basePackages = ComponentScanConstant.DEFAULT_SCAN)
@MapperScan(basePackages = ComponentScanConstant.MAPPER_SCAN)
public class SparrowServerAutoconfigure {

    @Bean
    @ConditionalOnMissingBean(SparrowServerEventInit.class)
    public SparrowServerEventInit sparrowServerEventInit() {
        return new SparrowServerEventInit ();
    }

    @Bean
    @ConditionalOnMissingBean(SparrowServerInit.class)
    public SparrowServerInit sparrowServerInit() {
        return new SparrowServerInit ();
    }

    @Bean
    @ConditionalOnMissingBean(SparrowServerDatabaseInit.class)
    public SparrowServerDatabaseInit sparrowServerDatabaseInit() {
        return new SparrowServerDatabaseInit ();
    }

    @Bean
    @ConditionalOnMissingBean(AdministrationService.class)
    public AdministrationService administrationService() {
        return new AdministrationService ();
    }

    @Bean
    @ConditionalOnMissingBean(SparrowTopicEntityCacheTemplate.class)
    public SparrowTopicEntityCacheTemplate sparrowTopicEntityCacheTemplate() {
        return CacheTemplateBuilder.builderSparrowTopicEntityCacheTemplate ();
    }

    @Bean
    @ConditionalOnMissingBean(RemoveExpiredDataInit.class)
    public RemoveExpiredDataInit removeExpiredDataInit() {
        return new RemoveExpiredDataInit ();
    }

    @Bean
    @ConditionalOnMissingBean(FlushConstantInit.class)
    public FlushConstantInit flushConstantInit(){
        return new FlushConstantInit ();
    }
}
