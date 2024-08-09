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
package com.github.thierrysquirrel.sparrow.server.common.netty.coder;

import com.github.thierrysquirrel.sparrow.server.common.netty.coder.constant.CoderIdentifyConstant;
import com.github.thierrysquirrel.sparrow.server.common.netty.coder.utils.SerializerUtils;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * ClassName: SparrowEncoder
 * Description:
 * Date:2024/8/9
 *
 * @author ThierrySquirrel
 * @since JDK21
 **/
public class SparrowEncoder extends MessageToByteEncoder<SparrowRequestContext> {

    @Override
    protected void encode(ChannelHandlerContext ctx, SparrowRequestContext msg, ByteBuf out) {
        out.writeBytes(CoderIdentifyConstant.SPARROW.getValue());
        byte[] serialize = SerializerUtils.serialize(msg);
        out.writeInt(serialize.length);
        out.writeBytes(serialize);
    }
}
