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
package com.github.thierrysquirrel.sparrow.server.common.netty.handler;

import com.github.thierrysquirrel.sparrow.server.common.netty.coder.SparrowDecoder;
import com.github.thierrysquirrel.sparrow.server.common.netty.coder.SparrowEncoder;
import com.github.thierrysquirrel.sparrow.server.common.netty.handler.constant.IdleStateHandlerConstant;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: AbstractInitializer
 * Description:
 * date: 2020/12/7 1:11
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public abstract class AbstractInitializer extends ChannelInitializer<SocketChannel> {
    private final int readerIdleTime;
    private final int writerIdleTime;

    protected AbstractInitializer(int readerIdleTime, int writerIdleTime) {
        this.readerIdleTime = readerIdleTime;
        this.writerIdleTime = writerIdleTime;
    }

    /**
     * initHandler
     *
     * @param ch ch
     */
    protected abstract void initHandler(SocketChannel ch);

    @Override
    protected void initChannel(SocketChannel ch) {
        ch.pipeline ()
                .addLast (new IdleStateHandler (readerIdleTime, writerIdleTime, IdleStateHandlerConstant.OTHER_TIMEOUT, TimeUnit.MILLISECONDS))
                .addLast (new SparrowDecoder ())
                .addLast (new SparrowEncoder ());
        initHandler (ch);
    }

}

