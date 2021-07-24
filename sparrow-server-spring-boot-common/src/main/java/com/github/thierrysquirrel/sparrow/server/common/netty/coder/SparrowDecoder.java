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
package com.github.thierrysquirrel.sparrow.server.common.netty.coder;

import com.github.thierrysquirrel.sparrow.server.common.netty.coder.constant.CoderConstant;
import com.github.thierrysquirrel.sparrow.server.common.netty.coder.utils.DecoderUtils;
import com.github.thierrysquirrel.sparrow.server.common.netty.coder.utils.SerializerUtils;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * ClassName: SparrowDecoder
 * Description:
 * date: 2020/12/7 0:52
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class SparrowDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        while (in.readableBytes () >= CoderConstant.MINIMUM_DECODING) {
            int readerIndex = in.readerIndex ();

            if (!DecoderUtils.readSparrow (in)) {
                in.readerIndex (readerIndex);
                return;
            }
            int dataLength = in.readInt ();
            if (in.readableBytes () < dataLength) {
                in.readerIndex (readerIndex);
                return;
            }
            byte[] data = new byte[dataLength];
            in.readBytes (data);
            SparrowRequestContext sparrowRequestContext = SerializerUtils.deSerialize (data, SparrowRequestContext.class);
            out.add (sparrowRequestContext);
        }
    }
}
