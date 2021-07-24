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
package com.github.thierrysquirrel.sparrow.server.netty.core.factory;

import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequest;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: ModularMethodParameterFactory
 * Description:
 * date: 2020/12/7 2:04
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ModularMethodParameterFactory {
    private ModularMethodParameterFactory() {
    }

    public static Object[] getParameter(ChannelHandlerContext ctx, SparrowRequestContext msg) {
        List<Object> parameter = new ArrayList<> ();
        parameter.add (ctx);
        parameter.add (msg);
        SparrowRequest sparrowRequest = msg.getSparrowRequest ();

        Object[] parameters = sparrowRequest.getParameters ();
        parameter.addAll (Arrays.stream (parameters).
                collect (Collectors.toList ()));

        return parameter.toArray ();
    }
}
