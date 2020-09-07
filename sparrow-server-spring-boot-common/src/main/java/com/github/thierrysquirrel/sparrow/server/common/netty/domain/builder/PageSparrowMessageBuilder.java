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
package com.github.thierrysquirrel.sparrow.server.common.netty.domain.builder;

import com.github.thierrysquirrel.sparrow.server.common.netty.domain.PageSparrowMessage;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowMessage;

import java.util.List;

/**
 * ClassName: PageSparrowMessageBuilder
 * Description:
 * date: 2020/6/10 5:19
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class PageSparrowMessageBuilder {
    private PageSparrowMessageBuilder() {
    }

    private static PageSparrowMessage createPageSparrowMessage() {
        return new PageSparrowMessage ();
    }

    public static PageSparrowMessage builderPullMessageResponse(String topic, int pageIndex, int pageTotal, List<SparrowMessage> sparrowMessageList) {
        PageSparrowMessage pageSparrowMessage = createPageSparrowMessage ();
        pageSparrowMessage.setTopic (topic);
        pageSparrowMessage.setPageIndex (pageIndex);
        pageSparrowMessage.setPageTotal (pageTotal);
        pageSparrowMessage.setSparrowMessageList (sparrowMessageList);
        return pageSparrowMessage;
    }
}
