/**
 * Copyright 2024/8/9 ThierrySquirrel
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
 **/
package com.github.thierrysquirrel.sparrow.server.init.core.factory;

import com.github.thierrysquirrel.sparrow.server.annotation.SparrowServerModular;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.constant.Event;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.constant.Modular;
import com.github.thierrysquirrel.sparrow.server.netty.core.container.ModularMethodContainer;
import com.github.thierrysquirrel.sparrow.server.netty.core.domain.ModularMethod;
import com.github.thierrysquirrel.sparrow.server.netty.core.domain.builder.ModularMethodBuilder;

import java.lang.reflect.Method;

/**
 * ClassName: ModularMethodInitFactory
 * Description:
 * Date:2024/8/9
 *
 * @author ThierrySquirrel
 * @since JDK21
 **/
public class ModularMethodInitFactory {
    private ModularMethodInitFactory() {
    }

    public static void putModularMethod(Object bean, Method method, Event event) {
        Modular modular = bean.getClass().getAnnotation(SparrowServerModular.class).modular();
        ModularMethod modularMethod = ModularMethodBuilder.builderModularMethod(bean, method);
        ModularMethodContainer.putMethodDomain(modular, event, modularMethod);
    }
}
