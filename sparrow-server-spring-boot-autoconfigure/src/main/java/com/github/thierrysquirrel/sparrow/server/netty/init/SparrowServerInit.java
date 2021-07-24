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
package com.github.thierrysquirrel.sparrow.server.netty.init;

import com.github.thierrysquirrel.sparrow.server.common.netty.utils.SocketAddressUtils;
import com.github.thierrysquirrel.sparrow.server.netty.handler.SparrowServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * ClassName: SparrowServerInit
 * Description:
 * date: 2020/12/7 1:40
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class SparrowServerInit {
    private SparrowServerInit() {
    }

    public static void init(String sparrowServerUrl) {
        new ServerBootstrap ()
                .group (new NioEventLoopGroup (), new NioEventLoopGroup ())
                .channel (NioServerSocketChannel.class)
                .childHandler (new SparrowServerInitializer ())
                .bind (SocketAddressUtils.getInetSocketAddress (sparrowServerUrl));
    }

}
