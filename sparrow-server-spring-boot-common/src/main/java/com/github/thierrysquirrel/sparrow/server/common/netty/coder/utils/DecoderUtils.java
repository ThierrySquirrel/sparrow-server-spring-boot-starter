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
package com.github.thierrysquirrel.sparrow.server.common.netty.coder.utils;

import com.github.thierrysquirrel.sparrow.server.common.netty.coder.constant.CoderConstant;
import com.github.thierrysquirrel.sparrow.server.common.netty.coder.constant.CoderIdentifyConstant;
import io.netty.buffer.ByteBuf;

import java.util.Arrays;

/**
 * ClassName: DecoderUtils
 * Description:
 * Date:2024/8/9
 *
 * @author ThierrySquirrel
 * @since JDK21
 **/
public class DecoderUtils {
    private DecoderUtils() {
    }

    public static boolean readSparrow(ByteBuf in) {
        byte[] value = CoderIdentifyConstant.SPARROW.getValue();
        int length = value.length;
        while (in.readableBytes() >= CoderConstant.MINIMUM_DECODING) {
            byte[] readSparrow = new byte[length];

            in.markReaderIndex();
            in.readBytes(readSparrow);
            if (Arrays.equals(value, readSparrow)) {
                return Boolean.TRUE;
            }

            in.resetReaderIndex();
            in.readByte();
        }
        return Boolean.FALSE;
    }
}
