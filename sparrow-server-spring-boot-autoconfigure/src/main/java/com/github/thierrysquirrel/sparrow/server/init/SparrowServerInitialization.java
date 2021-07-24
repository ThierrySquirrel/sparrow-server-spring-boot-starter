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
package com.github.thierrysquirrel.sparrow.server.init;

import com.github.thierrysquirrel.sparrow.server.autoconfigure.SparrowServerProperties;
import com.github.thierrysquirrel.sparrow.server.init.core.factory.execution.SparrowServerInitializationExecution;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Resource;

/**
 * ClassName: SparrowServerInitialization
 * Description:
 * date: 2020/12/7 3:44
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class SparrowServerInitialization implements InitializingBean {
    @Resource
    private SparrowServerProperties sparrowServerProperties;

    @Override
    public void afterPropertiesSet() {
        SparrowServerInitializationExecution.initialization (sparrowServerProperties.getUrl ());
    }
}
