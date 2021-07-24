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
package com.github.thierrysquirrel.sparrow.server.netty.core.factory.execution;

import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import com.github.thierrysquirrel.sparrow.server.netty.core.container.ModularMethodContainer;
import com.github.thierrysquirrel.sparrow.server.netty.core.domain.ModularMethod;
import com.github.thierrysquirrel.sparrow.server.netty.core.factory.ModularMethodParameterFactory;
import io.netty.channel.ChannelHandlerContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ClassName: ModularMethodExecution
 * Description:
 * date: 2020/12/7 2:08
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ModularMethodExecution {
    private ModularMethodExecution() {
    }

    public static void invoke(ChannelHandlerContext ctx, SparrowRequestContext msg) throws InvocationTargetException, IllegalAccessException {
        Object[] eventParameter = ModularMethodParameterFactory.getParameter (ctx, msg);
        ModularMethod modularMethod = ModularMethodContainer.getMethodDomain (msg.getModular (), msg.getEvent ());
        Method method = modularMethod.getMethod ();
        Object object = modularMethod.getBean ();
        method.invoke (object, eventParameter);
    }
}
