package com.kechen.seckill.services;

import com.kechen.seckill.db.dao.SeckillActivityDao;
import com.kechen.seckill.db.dao.SeckillCommodityDao;
import com.kechen.seckill.db.po.SeckillActivity;
import com.kechen.seckill.db.po.SeckillCommodity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ActivityHtmlPageService {

    @Resource
    private TemplateEngine templateEngine;

    @Resource
    private SeckillActivityDao seckillActivityDao;

    @Resource
    private SeckillCommodityDao seckillCommodityDao;

    /**
     * create html page 创建html页面
     *
     * @throws Exception
     */
    public void createActivityHtml(long seckillActivityId) {

        PrintWriter writer = null;
        try {
            SeckillActivity seckillActivity = seckillActivityDao.querySeckillActivityById(seckillActivityId);
            SeckillCommodity seckillCommodity = seckillCommodityDao.querySeckillCommodityById(seckillActivity.getCommodityId());
            // 获取页面数据
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("seckillActivity", seckillActivity);
            resultMap.put("seckillCommodity", seckillCommodity);
            resultMap.put("seckillPrice", seckillActivity.getSeckillPrice());
            resultMap.put("oldPrice", seckillActivity.getOldPrice());
            resultMap.put("commodityId", seckillActivity.getCommodityId());
            resultMap.put("commodityName", seckillCommodity.getCommodityName());
            resultMap.put("commodityDesc", seckillCommodity.getCommodityDesc());

            // Create thymeleaf context object 创建thymeleaf上下文对象
            Context context = new Context();
            // Put data into the context object 把数据放入上下文对象
            context.setVariables(resultMap);

            // Create output stream 创建输出流
            File file = new File("src/main/resources/templates/" + "seckill_item_" + seckillActivityId + ".html");
            writer = new PrintWriter(file);
            // Execute page static method 执行页面静态化方法
            templateEngine.process("seckill_item", context, writer);
        } catch (Exception e) {
            log.error(e.toString());
            log.error("static page error：" + seckillActivityId);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}