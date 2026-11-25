/*
 ********************************************************************************
 * COPYRIGHT (c) 2024 Harman International Industries, Inc                      *
 *                                                                              *
 * All rights reserved                                                          *
 *                                                                              *
 * This software embodies materials and concepts which are                      *
 * confidential to Harman International Industries, Inc. and is                 *
 * made available solely pursuant to the terms of a written license             *
 * agreement with Harman International Industries, Inc.                         *
 *                                                                              *
 * Designed and Developed by Harman International Industries, Inc.              *
 *------------------------------------------------------------------------------*
 * MODULE OR UNIT: ignite-cache                                                 *
 ********************************************************************************
 */

package org.eclipse.ecsp.cache.redis;

import org.eclipse.ecsp.cache.PutStringRequest;
import org.eclipse.ecsp.utils.logger.IgniteLogger;
import org.eclipse.ecsp.utils.logger.IgniteLoggerFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.redisson.api.RedissonClient;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to test the creation of RedissonClient.
 * It also tests the cache operations with the created RedissonClient.
 */
public class RedissonClientCreationTest {

    /** The Constant LOGGER. */
    private static final IgniteLogger LOGGER = IgniteLoggerFactory.getLogger(RedissonClientCreationTest.class);
    
    /** The embedded redis server. */
    @ClassRule
    public static EmbeddedRedisServer embeddedRedisServer = new EmbeddedRedisServer();
    
    /** The redisson client. */
    private RedissonClient redissonClient;
    
    /** The ignite cache redis impl. */
    private IgniteCacheRedisImpl igniteCacheRedisImpl;

    /**
     * This method is used to set up the test environment.
     */
    @Before
    public void setup() {
        LOGGER.info("Initializing redisson client...");
        Map<String, String> props = getPropertiesMap();
        RedisConfig redisConfig = new RedisConfig();
        igniteCacheRedisImpl = new IgniteCacheRedisImpl();
        redissonClient = redisConfig.builder().build(props);
        igniteCacheRedisImpl.setRedissonClient(redissonClient);
    }

    /**
     * Stop.
     */
    @After
    public void stop() {
        LOGGER.info("Shutting down Redisson Client...");
        redissonClient.shutdown();
    }

    /**
     * In this test making sure redisson client is formed properly.
     */
    @Test
    public void testRedissonClientCreation() {
        Assert.assertNotNull(redissonClient);
    }

    /**
     * In this test we are doing cache operations with the previously created redissonclient.
     */
    @Test
    public void testPutGetString() {
        Assert.assertNotNull(igniteCacheRedisImpl);
        igniteCacheRedisImpl.putString(new PutStringRequest().withKey("hello").withValue("world"));
        String value = igniteCacheRedisImpl.getString("hello");
        Assert.assertEquals("world", value);
    }

    /**
     * Gets the properties map.
     *
     * @return the properties map
     */
    private Map<String, String> getPropertiesMap() {
        Map<String, String> props = new HashMap<>();

        props.put("cache.address", "127.0.0.1:" + embeddedRedisServer.getMappedPort());
        props.put("cache.sentinels", "");
        props.put("cache.master.name", "");
        props.put("cache.dns.monitoring.interval", "5000");
        props.put("cache.read.mode", "SLAVE");
        props.put("cache.subscription.mode", "SLAVE");
        props.put("cache.subscription.conn.min.idle.size", "1");
        props.put("cache.subscription.conn.pool.size", "50");
        props.put("cache.slave.conn.min.idle.size", "32");
        props.put("cache.slave.pool.size", "64");
        props.put("cache.master.conn.min.idle.size", "32");
        props.put("cache.master.conn.pool.size", "64");
        props.put("cache.idle.conn.timeout", "10000");
        props.put("cache.conn.timeout", "10000");
        props.put("cache.timeout", "3000");
        props.put("cache.retry.attempts", "3");
        props.put("cache.retry.interval", "1500");
        props.put("cache.reconnection.timeout", "3000");
        props.put("cache.failed.attempts", "3");
        props.put("cache.database", "0");
        props.put("cache.password", "");
        props.put("cache.subscriptions.per.conn", "5");
        props.put("cache.client.name", "yellow");
        props.put("cache.conn.min.idle.size", "32");
        props.put("cache.conn.pool.size", "64");
        props.put("cache.cluster.masters", "");
        props.put("cache.scan.interval", "1000");
        props.put("cache.scan.limit", "10");
        props.put("cache.regex.scan.filename", "scanregex.txt");
        props.put("cache.netty.threads", "0");
        props.put("cache.decode.in.executor", "false");
        props.put("cache.check.slots.coverage", "false");
        props.put("cache.executor.threads", "32");
        props.put("cache.keep.alive", "true");
        props.put("cache.ping.connection.interval", "60000");
        props.put("cache.tcp.no.delay", "true");
        props.put("cache.transport.mode", "NIO");
        props.put("cache.slave.fails.interval", "180000");
        return props;
    }
}
