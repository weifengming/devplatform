package com.wfm.platform.redis.util;

import com.wfm.platform.util.StringHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Weifengming
 * @description jedisPool 工具类
 * @date 2020/2/23
 */

@Component
public class JedisPoolUtil {

    private static JedisPool pool;
    private static String host;
    private static Integer port;
    private static String password;
    private static int database;

    @Value("${redis.host}")
    public void setHost(String host) {
        JedisPoolUtil.host = host;
    }

    @Value("${redis.port}")
    public void setPort(Integer port) {
        JedisPoolUtil.port = port;
    }

    @Value("${redis.password}")
    public void setPassword(String password) {
        JedisPoolUtil.password = password;
    }

    @Value("${redis.database}")
    public void setDatabase(int database) {
        JedisPoolUtil.database = database;
    }

    private static void createJedisPool() {
        if (StringHelper.isEmpty(password))
            pool = new JedisPool(new JedisPoolConfig(), host, port.intValue());
        else
            pool = new JedisPool(new JedisPoolConfig(), host, port.intValue(), 2000, password);
    }

    private static synchronized void poolInit() {
        if (pool == null)
            createJedisPool();
    }

    public static Jedis getJedis() {
        if (pool == null) {
            poolInit();
        }
        Jedis resource = pool.getResource();
        resource.select(database);
        return resource;
    }

    public static void close(Jedis jedis) {
        if (jedis != null)
            jedis.close();
    }
}
