package com.kechen.seckill.web;

import com.kechen.seckill.services.SeckillActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SeckillOverSellController {

    /**
    @Autowired
    private SeckillOverSellService seckillOverSellService;
     */

    @Autowired
    private SeckillActivityService seckillActivityService;

    /**
     * 简单 处理抢购请求
     * @param seckillActivityId
     * @return

    @ResponseBody
    //@RequestMapping("/seckill/{seckillActivityId}")
    public String  seckil(@PathVariable long seckillActivityId){
       return seckillOverSellService.processSeckill(seckillActivityId);
    }
     */


    /**
     * 使用 lua 脚本处理抢购请求
     * @param seckillActivityId
     * @return
     */
    @ResponseBody
    @RequestMapping("/seckill/{seckillActivityId}")
    public String seckillCommodity(@PathVariable long seckillActivityId) {
        boolean stockValidateResult = seckillActivityService.seckillStockValidator(seckillActivityId);
        return stockValidateResult ? "Congratulations on your successful seckill" : "The product is sold out, come again next time";
    }



}
