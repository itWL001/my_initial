package org.initial.heart.simulate100000.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
//
//@RestController
//@RequestMapping("jedis")
//public class JedisController {
//    @Autowired
//    Jedis jedis;
//    @GetMapping("/setnx/{key}/{val}")
//    public boolean setnx(@PathVariable String key, @PathVariable String val) {
//        Long setnx = jedis.setnx(key, val);
//        return true;
//    }
//    @GetMapping("/delnx/{key}/{val}")
//    public Long delnx(@PathVariable String key, @PathVariable String val) {
//        Long del = jedis.del(key, val);
//        return del;
//
//    }
//}
