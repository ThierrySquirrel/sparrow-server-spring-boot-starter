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

import com.github.thierrysquirrel.sparrow.server.init.core.factory.execution.ModularMethodInitFactoryExecution;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;

/**
 * ClassName: ModularMethodInit
 * Description:
 * date: 2020/12/7 2:14
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ModularMethodInit implements ApplicationContextAware {
    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        ModularMethodInitFactoryExecution.init (applicationContext);
    }
}