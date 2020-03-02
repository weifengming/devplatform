package com.wfm.platform.redis.service.impl;

import com.wfm.platform.redis.service.RedisService;
import com.wfm.platform.redis.util.JedisPoolUtil;
import com.wfm.platform.redis.util.SerializeUtil;
import com.wfm.platform.util.BeanUtils;
import com.wfm.platform.util.StringHelper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.io.Serializable;
import java.util.*;

/**
 * @author Weifengming
 * @description 服务接口
 * @date 2020/2/23
 */
@Primary
@Service
public class RedisServiceImpl<T extends Serializable>
        implements RedisService<T> {
    public void add(String key, T obj, int timeout) {
        if ((StringHelper.isEmpty(key)) || (BeanUtils.isEmpty(obj))) return;
        Jedis jedis = JedisPoolUtil.getJedis();
        try {
            byte[] serialize = SerializeUtil.serialize(obj);
            jedis.set(key.getBytes(), serialize);
            if (timeout > 0)
                jedis.expire(key, timeout);
        } catch (Exception ex) {
            throw ex;
        } finally {
            JedisPoolUtil.close(jedis);
        }
    }

    public void add(String key, T obj) {
        add(key, obj, 0);
    }

    public void delByKey(String key) {
        if (StringHelper.isEmpty(key)) return;
        Jedis jedis = JedisPoolUtil.getJedis();
        try {
            if (jedis.exists(key).booleanValue())
                jedis.del(key);
        } catch (Exception ex) {
            throw ex;
        } finally {
            JedisPoolUtil.close(jedis);
        }
    }

    public void clearAll() {
        Jedis jedis = JedisPoolUtil.getJedis();
        try {
            jedis.flushAll();
        } catch (Exception ex) {
            throw ex;
        } finally {
            JedisPoolUtil.close(jedis);
        }
    }

    public T getByKey(String key) {
        if (StringHelper.isEmpty(key)) return null;
        Jedis jedis = JedisPoolUtil.getJedis();
        byte[] byt = null;
        try {
            if (jedis.exists(key).booleanValue()) {
                byt = jedis.get(key.getBytes());
                Serializable localSerializable = (Serializable) SerializeUtil.unserialize(byt);
                return (T) localSerializable;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            JedisPoolUtil.close(jedis);
        }
        return null;
    }

    public boolean containKey(String key) {
        if (StringHelper.isEmpty(key)) return false;
        Jedis jedis = JedisPoolUtil.getJedis();
        try {
            if (jedis.exists(key).booleanValue()) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            throw ex;
        } finally {
            JedisPoolUtil.close(jedis);
        }
    }

    public void batchAdd(Map<String, Map<String, String>> map) {
        if (BeanUtils.isEmpty(map)) return;
        Jedis jedis = JedisPoolUtil.getJedis();
        try {
            Pipeline p = jedis.pipelined();
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry next = (Map.Entry) it.next();
                String key = (String) next.getKey();
                Map value = (Map) next.getValue();
                p.hmset(key, value);
            }
            p.sync();
        } catch (Exception ex) {
            throw ex;
        } finally {
            JedisPoolUtil.close(jedis);
        }
    }

    public Map<String, String> batchGet(List<String> keys, String type) {
        Map map = new HashMap();
        if (BeanUtils.isEmpty(keys)) return map;
        Jedis jedis = JedisPoolUtil.getJedis();
        try {
            Pipeline p = jedis.pipelined();

            HashMap newMap = new HashMap();
            for (Object localObject1 = keys.iterator(); ((Iterator) localObject1).hasNext(); ) {
                String key = (String) ((Iterator) localObject1).next();
                newMap.put(key, p.hmget(key, new String[]{type}));
            }
            p.sync();

            for (Iterator localObject1 = newMap.entrySet().iterator(); ((Iterator) localObject1).hasNext(); ) {
                Map.Entry entry = (Map.Entry) ((Iterator) localObject1).next();
                String key = (String) entry.getKey();
                Response value = (Response) entry.getValue();
                List list = (List) value.get();
                if ((BeanUtils.isNotEmpty(list)) && (StringHelper.isNotEmpty((String) list.get(0)))) {
                    map.put(key, list.get(0));
                } else {
                    map.put(key, key);
                }
            }
            return map;
        } catch (Exception ex) {
            throw ex;
        } finally {
            JedisPoolUtil.close(jedis);
        }
    }

    public void removeAllBatch() {
        Jedis jedis = JedisPoolUtil.getJedis();
        try {
            jedis.flushAll();
        } catch (Exception ex) {
            throw ex;
        } finally {
            JedisPoolUtil.close(jedis);
        }
    }

    public void hdel(String key, String field) {
        Jedis jedis = JedisPoolUtil.getJedis();
        try {
            jedis.hdel(key, new String[]{field});
        } catch (Exception e) {
            throw e;
        } finally {
            JedisPoolUtil.close(jedis);
        }
    }

    public void delByStartKey(String key) {
        Jedis jedis = JedisPoolUtil.getJedis();
        Set keys = jedis.keys(key + "*");
        try {
            for (Object _key : keys)
                jedis.del(String.valueOf(_key));
        } catch (Exception e) {
            throw e;
        } finally {
            JedisPoolUtil.close(jedis);
        }
    }
}
