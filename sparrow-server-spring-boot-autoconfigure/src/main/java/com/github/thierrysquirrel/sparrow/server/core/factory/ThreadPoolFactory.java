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
package com.github.thierrysquirrel.sparrow.server.core.factory;

import com.github.thierrysquirrel.sparrow.server.core.factory.constant.ThreadPoolConstant;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * ClassName: ThreadPoolFactory
 * Description:
 * date: 2020/6/8 18:44
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ThreadPoolFactory {
    private ThreadPoolFactory() {
    }

    public static ThreadPoolExecutor createSparrowServerInitThreadPool() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder ()
                .setNameFormat (ThreadPoolConstant.SPARROW_SERVER_INIT).build ();
        return new ThreadPoolExecutor (ThreadPoolConstant.SPARROW_SERVER_INIT_CORE_POOL_SIZE,
                ThreadPoolConstant.SPARROW_SERVER_INIT_MAXIMUM_POOL_SIZE,
                ThreadPoolConstant.SPARROW_SERVER_INIT_KEEP_ALIVE_TIME,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<> (),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy ()
        );
    }

    public static ThreadPoolExecutor createSparrowServerBusinessThreadPool() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder ()
                .setNameFormat (ThreadPoolConstant.SPARROW_SERVER_BUSINESS).build ();
        return new ThreadPoolExecutor (ThreadPoolConstant.SPARROW_SERVER_BUSINESS_CORE_POOL_SIZE,
                ThreadPoolConstant.SPARROW_SERVER_BUSINESS_MAXIMUM_POOL_SIZE,
                ThreadPoolConstant.SPARROW_SERVER_BUSINESS_KEEP_ALIVE_TIME,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<> (),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy ()
        );
    }

    public static ThreadPoolExecutor createSynchronousClusterTopicCacheThreadPool() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder ()
                .setNameFormat (ThreadPoolConstant.SYNCHRONOUS_CLUSTER_TOPIC_CACHE).build ();
        return new ThreadPoolExecutor (ThreadPoolConstant.SYNCHRONOUS_CLUSTER_TOPIC_CACHE_CORE_POOL_SIZE,
                ThreadPoolConstant.SYNCHRONOUS_CLUSTER_TOPIC_CACHE_MAXIMUM_POOL_SIZE,
                ThreadPoolConstant.SYNCHRONOUS_CLUSTER_TOPIC_CACHE_KEEP_ALIVE_TIME,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<> (),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy ()
        );
    }

    public static ScheduledThreadPoolExecutor createRemoveExpiredDataThreadPool() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder ()
                .setNameFormat (ThreadPoolConstant.REMOVE_EXPIRED_DATA).build ();
        return new ScheduledThreadPoolExecutor (ThreadPoolConstant.REMOVE_EXPIRED_DATA_CORE_POOL_SIZE, threadFactory);
    }

    public static ThreadPoolExecutor createPushMessageThreadPool() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder ()
                .setNameFormat (ThreadPoolConstant.PUSH_MESSAGE).build ();
        return new ThreadPoolExecutor (ThreadPoolConstant.PUSH_MESSAGE_CORE_POOL_SIZE,
                ThreadPoolConstant.PUSH_MESSAGE_MAXIMUM_POOL_SIZE,
                ThreadPoolConstant.PUSH_MESSAGE_KEEP_ALIVE_TIME,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<> (),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy ()
        );
    }

    public static ScheduledThreadPoolExecutor createFlushConstantThreadPool() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder ()
                .setNameFormat (ThreadPoolConstant.FLUSH_CONSTANT).build ();
        return new ScheduledThreadPoolExecutor (ThreadPoolConstant.FLUSH_CONSTANT_CORE_POOL_SIZE, threadFactory);
    }

}
