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
package com.github.thierrysquirrel.sparrow.server.common.netty.client.core.factory;

import com.github.thierrysquirrel.sparrow.server.common.netty.client.core.constant.ConsumerConstant;
import com.github.thierrysquirrel.sparrow.server.common.netty.client.core.constant.ConsumerState;
import com.github.thierrysquirrel.sparrow.server.common.netty.client.listener.ConsumerListener;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.*;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.builder.SparrowRequestContextBuilder;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.constant.ResponseDomainConstant;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: ConsumeHandlerFactory
 * Description:
 * date: 2020/6/11 6:18
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ConsumeHandlerFactory {
    private ConsumeHandlerFactory() {
    }

    public static boolean closeChannel(SparrowRequestContext msg) {
        SparrowResponse sparrowResponse = msg.getSparrowResponse ();
        ResponseDomain responseDomain = (ResponseDomain) sparrowResponse.getData ();
        if (ResponseDomainConstant.FAILED_CODE == responseDomain.getCode ()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static void pushMessage(ConsumerListener consumerListener, ChannelHandlerContext ctx, SparrowRequestContext msg) {
        ResponseDomain responseDomain = (ResponseDomain) msg.getSparrowResponse ().getData ();
        SparrowMessage sparrowMessage = (SparrowMessage) responseDomain.getData ();
        Long id = consumer (consumerListener, sparrowMessage);
        if (null != id) {
            SparrowRequestContext sparrowRequestContext = SparrowRequestContextBuilder.builderConfirmConsumption (id);
            ctx.writeAndFlush (sparrowRequestContext);
        }
    }

    public static void pullMessage(ConsumerListener consumerListener, ChannelHandlerContext ctx, SparrowRequestContext msg) {
        ResponseDomain responseDomain = (ResponseDomain) msg.getSparrowResponse ().getData ();
        PageSparrowMessage pageSparrowMessage = (PageSparrowMessage) responseDomain.getData ();
        List<SparrowMessage> sparrowMessageList = pageSparrowMessage.getSparrowMessageList ();
        if (null == sparrowMessageList || sparrowMessageList.isEmpty ()) {
            return;
        }
        String topic = null;
        List<Long> idList = new ArrayList<> ();
        for (SparrowMessage sparrowMessage : sparrowMessageList) {
            Long id = consumer (consumerListener, sparrowMessage);
            if (null != id) {
                idList.add (id);
            }
            if (null == topic) {
                topic = sparrowMessage.getTopic ();
            }
        }

        int pageIndex = pageSparrowMessage.getPageIndex ();
        ++pageIndex;
        if (pageIndex < pageSparrowMessage.getPageTotal ()) {
            SparrowRequestContext sparrowRequestContext = SparrowRequestContextBuilder.builderPullMessage (topic, pageIndex, ConsumerConstant.PULL_SIZE);
            ctx.writeAndFlush (sparrowRequestContext);
        }
        SparrowRequestContext sparrowRequestContext = SparrowRequestContextBuilder.builderBatchConfirmConsumption (idList);
        ctx.writeAndFlush (sparrowRequestContext);
    }

    private static Long consumer(ConsumerListener consumerListener, SparrowMessage sparrowMessage) {
        ConsumerState consumerState = consumerListener.consumer (sparrowMessage.getMessage ());
        if (ConsumerState.SUCCESS == consumerState) {
            return sparrowMessage.getId ();
        }
        return null;
    }
}
