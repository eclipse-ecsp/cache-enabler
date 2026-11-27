/*
 * *******************************************************************************
 *
 *  Copyright (c) 2023-24 Harman International
 *
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *
 *  you may not use this file except in compliance with the License.
 *
 *  You may obtain a copy of the License at
 *
 *
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *       
 *
 *  Unless required by applicable law or agreed to in writing, software
 *
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 *  See the License for the specific language governing permissions and
 *
 *  limitations under the License.
 *
 *
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  *******************************************************************************
 */

package org.eclipse.ecsp.cache.redis;

/**
 * Redis Properties.
 */
public abstract class RedisProperty {

    /**
     * Instantiates a new redis property.
     */
    private RedisProperty() {
        throw new IllegalStateException("Utility class");
    }

    /** The Constant REDIS_ADDRESS. */
    public static final String REDIS_ADDRESS = "cache.address";
    
    /** The Constant REDIS_SENTINELS. */
    public static final String REDIS_SENTINELS = "cache.sentinels";
    
    /** The Constant REDIS_MASTER_NAME. */
    public static final String REDIS_MASTER_NAME = "cache.master.name";
    
    /** The Constant REDIS_READ_MODE. */
    public static final String REDIS_READ_MODE = "cache.read.mode";
    
    /** The Constant REDIS_SUBSCRIPTION_MODE. */
    public static final String REDIS_SUBSCRIPTION_MODE = "cache.subscription.mode";
    
    /** The Constant REDIS_SUBSCRIPTION_CONN_MIN_IDLE_SIZE. */
    public static final String REDIS_SUBSCRIPTION_CONN_MIN_IDLE_SIZE = "cache.subscription.conn.min.idle.size";
    
    /** The Constant REDIS_SUBSCRIPTION_CONN_POOL_SIZE. */
    public static final String REDIS_SUBSCRIPTION_CONN_POOL_SIZE = "cache.subscription.conn.pool.size";
    
    /** The Constant REDIS_SLAVE_CONN_MIN_IDLE_SIZE. */
    public static final String REDIS_SLAVE_CONN_MIN_IDLE_SIZE = "cache.slave.conn.min.idle.size";
    
    /** The Constant REDIS_SLAVE_POOL_SIZE. */
    public static final String REDIS_SLAVE_POOL_SIZE = "cache.slave.pool.size";
    
    /** The Constant REDIS_MASTER_CONN_MIN_IDLE_SIZE. */
    public static final String REDIS_MASTER_CONN_MIN_IDLE_SIZE = "cache.master.conn.min.idle.size";
    
    /** The Constant REDIS_MASTER_CONN_POOL_SIZE. */
    public static final String REDIS_MASTER_CONN_POOL_SIZE = "cache.master.conn.pool.size";
    
    /** The Constant REDIS_IDLE_CONN_TIMEOUT. */
    public static final String REDIS_IDLE_CONN_TIMEOUT = "cache.idle.conn.timeout";
    
    /** The Constant REDIS_CONN_TIMEOUT. */
    public static final String REDIS_CONN_TIMEOUT = "cache.conn.timeout";
    
    /** The Constant REDIS_TIMEOUT. */
    public static final String REDIS_TIMEOUT = "cache.timeout";
    
    /** The Constant REDIS_RETRY_ATTEMPTS. */
    public static final String REDIS_RETRY_ATTEMPTS = "cache.retry.attempts";
    
    /** The Constant REDIS_RETRY_INTERVAL. */
    public static final String REDIS_RETRY_INTERVAL = "cache.retry.interval";
    
    /** The Constant REDIS_RECONNECTION_TIMEOUT. */
    public static final String REDIS_RECONNECTION_TIMEOUT = "cache.reconnection.timeout";
    
    /** The Constant REDIS_DATABASE. */
    public static final String REDIS_DATABASE = "cache.database";
    
    /** The Constant REDIS_PASSWORD. */
    public static final String REDIS_PASSWORD = "cache.password";

    /** The Constant REDIS_SSL_ENABLED. */
    public static final String REDIS_SSL_ENABLED = "cache.ssl.enabled";
    
    /** The Constant REDIS_SUBSCRIPTION_PER_CONN. */
    public static final String REDIS_SUBSCRIPTION_PER_CONN = "cache.subscriptions.per.conn";
    
    /** The Constant REDIS_CLIENT_NAME. */
    public static final String REDIS_CLIENT_NAME = "cache.client.name";
    
    /** The Constant REDIS_CONN_MIN_IDLE_SIZE. */
    public static final String REDIS_CONN_MIN_IDLE_SIZE = "cache.conn.min.idle.size";
    
    /** The Constant REDIS_CONN_POOL_SIZE. */
    public static final String REDIS_CONN_POOL_SIZE = "cache.conn.pool.size";
    
    /** The Constant REDIS_CLUSTER_MASTERS. */
    public static final String REDIS_CLUSTER_MASTERS = "cache.cluster.masters";
    
    /** The Constant REDIS_SCAN_INTERVAL. */
    public static final String REDIS_SCAN_INTERVAL = "cache.scan.interval";
    
    /** The Constant REDIS_NETTY_THREADS. */
    public static final String REDIS_NETTY_THREADS = "cache.netty.threads";
    
    /** The Constant REDIS_EXECUTOR_THREADS. */
    public static final String REDIS_EXECUTOR_THREADS = "cache.executor.threads";
    
    /** The Constant REDIS_KEEP_ALIVE. */
    public static final String REDIS_KEEP_ALIVE = "cache.keep.alive";
    
    /** The Constant REDIS_PING_CONNECTION_INTERVAL. */
    public static final String REDIS_PING_CONNECTION_INTERVAL = "cache.ping.connection.interval";
    
    /** The Constant REDIS_TCP_NO_DELAY. */
    public static final String REDIS_TCP_NO_DELAY = "cache.tcp.no.delay";
    
    /** The Constant REDIS_TRANSPORT_MODE. */
    public static final String REDIS_TRANSPORT_MODE = "cache.transport.mode";
    
    /** The Constant REDIS_HEALTH_MONITOR_ENABLED. */
    public static final String REDIS_HEALTH_MONITOR_ENABLED = "health.cache.monitor.enabled";
    
    /** The Constant REDIS_NEEDS_RESTART_ON_FAILURE. */
    public static final String REDIS_NEEDS_RESTART_ON_FAILURE = "health.cache.needs.restart.on.failure";
    
    /** The Constant REDIS_KEY_NAMESPACE_DELIMETER. */
    public static final String REDIS_KEY_NAMESPACE_DELIMETER = ":";
    
    /** The Constant REDIS_CHECK_SLOTS_COVERAGE. */
    public static final String REDIS_CHECK_SLOTS_COVERAGE = "cache.check.slots.coverage";

    /** The Constant REDIS_SLAVE_FAILS_INTERVAL. */
    public static final String REDIS_SLAVE_FAILS_INTERVAL = "cache.slave.fails.interval";

}