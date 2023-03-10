package com.kechen.seckill;

import com.kechen.seckill.services.RedisService;
import com.kechen.seckill.services.SeckillActivityService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;

@SpringBootTest
public class RedisServiceTest {
        @Resource
        private RedisService redisService;

        @Resource
        private SeckillActivityService seckillActivityService;

        @Test
        public void  stockTest(){
            redisService.setValue("stock:19",10L);
        }

        @Test
        public void getStockTest(){
            String stock =  redisService.getValue("stock:19");
            System.out.println(stock);
        }

        @Test
        public void stockDeductValidatorTest(){
            boolean result =  redisService.stockDeductValidator("stock:19");
            System.out.println("result:"+result);
            String stock =  redisService.getValue("stock:19");
            System.out.println("stock:"+stock);
        }

        @Test
        public void pushSeckillInfoToRedish(){
            seckillActivityService.pushSeckillInfoToRedis(19);
        }

}
