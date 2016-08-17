package com.uc.redis;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import com.alibaba.fastjson.JSON;

/**
 * redis cas乐观锁
 * RedisCasLockTest 
 * @author yangxinyan
 * @date 2016年8月17日 下午6:49:56
 *
 */
public class RedisCasLockTest {

	public static void main(String[] args) {
		Transaction tx = null;
		List<Object> list = null;
		while (list == null) {
			try (Jedis jedis = new Jedis("localhost");) {
				jedis.watch("user");
				tx = jedis.multi();
				tx.lpush("user", "333");
				list = tx.exec();
				System.out.println(JSON.toJSONString(list));
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
