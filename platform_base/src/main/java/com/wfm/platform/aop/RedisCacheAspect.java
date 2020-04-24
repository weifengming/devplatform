package com.wfm.platform.aop;

import com.alibaba.fastjson.JSON;
import com.wfm.platform.query.FieldSort;
import com.wfm.platform.query.PageBean;
import com.wfm.platform.query.QueryField;
import com.wfm.platform.query.QueryFilter;
import com.wfm.platform.redis.util.JedisPoolUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;

/**
 * @author Weifengming
 * @description 缓存切面处理类
 * @date 2020/2/24
 */
//@Aspect
//@Component
public class RedisCacheAspect {

    /**
     * 开发环绕通知，处理数据访问缓存问题
     * 采用注解切入点表达式，对使用了自定义注解（com.wfm.platform.annotation.RedisCache）的方法进行缓存操作
     *
     * @return 目标方法的返回值
     */
    @Around("@annotation(com.wfm.platform.annotation.RedisCache)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        Jedis jedis = JedisPoolUtil.getJedis();
        Object result = null;

        String hashkey = hashkey(proceedingJoinPoint);//hash key
        String filed = getField(proceedingJoinPoint);//hash field

        //判断是否为第一次访问
        if (jedis.hexists(hashkey, filed)) {
            //存缓存取出数据
            String str = jedis.hget(hashkey, filed);
            //动态的解析JSON数据，使用fastJson
            MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
            result = JSON.parseObject(str, methodSignature.getMethod().getGenericReturnType());
        } else {
            try {
                result = proceedingJoinPoint.proceed();
                //向缓存中存储数据
                jedis.hset(hashkey, filed, JSON.toJSONString(result));
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 开发后置通知，解决修改、添加、删除后对缓存数据的维护
     */
    @After("within(com.wfm.platform.service.impl.*ServiceImpl)&&!@annotation(com.wfm.platform.annotation.RedisCache)")
    public void after(JoinPoint joinPoint) {
        Jedis jedis = JedisPoolUtil.getJedis();
        String hashkey = hashkey(joinPoint);
        if (jedis.exists(hashkey)) {
            jedis.del(hashkey);
        }
    }

    /**
     * 获取模块下方法抽象出来的filed
     *
     * @return 方法对应的filed
     */
    private String getField(ProceedingJoinPoint proceedingJoinPoint) {
        //拼接方法形参
        Object[] args = proceedingJoinPoint.getArgs();
        StringBuilder sb = new StringBuilder();
        for (Object arg : args) {
            if (arg instanceof QueryFilter) {
                QueryFilter queryFilter = (QueryFilter) arg;

                PageBean pageBean = queryFilter.getPageBean();
                Map<String, Object> params = queryFilter.getParams();
                Class<?> clazz = queryFilter.getClazz();
                List<QueryField> queryFields = queryFilter.getQuerys();
                List<FieldSort> fieldSorts = queryFilter.getSorter();

                sb.append(pageBean.getPage());
                sb.append(pageBean.getPageSize());
                sb.append(pageBean.getLimit());
                sb.append(pageBean.getOffset());
                if (!CollectionUtils.isEmpty(params)) {
                    sb.append(params.toString());
                }
                if (clazz != null) {
                    sb.append(clazz.toString());
                }
                if (!CollectionUtils.isEmpty(queryFields)) {
                    for (QueryField queryField : queryFields) {
                        sb.append(queryField.getGroup());
                        sb.append(queryField.getOperation());
                        sb.append(queryField.getProperty());
                        sb.append(queryField.getRelation());
                        sb.append(queryField.getHasInitValue());
                        sb.append(queryField.getValue());
                    }
                }
                if (!CollectionUtils.isEmpty(fieldSorts)) {
                    for (FieldSort fieldSort : fieldSorts) {
                        sb.append(fieldSort.getDirection().toString());
                        sb.append(fieldSort.getProperty());
                    }
                }
            } else {
                sb.append(arg);
            }
        }
        //方法签名+形参
        String strkey = proceedingJoinPoint.getSignature() + sb.toString();
        //md5加密处理
        String key = DigestUtils.md5DigestAsHex(strkey.getBytes());

        return key;
    }


    /**
     * 获取模块抽象出来的hash key
     */
    private String hashkey(JoinPoint joinPoint) {
        //类的全限定名
        String name = joinPoint.getTarget().getClass().getName();
        String hashkey = DigestUtils.md5DigestAsHex(name.getBytes());

        return hashkey;
    }
}