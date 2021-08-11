package com.wenjing.seckill.services;

import com.wenjing.seckill.db.dao.SeckillActivityDao;
import com.wenjing.seckill.db.dao.SeckillCommodityDao;
import com.wenjing.seckill.db.po.SeckillActivity;
import com.wenjing.seckill.db.po.SeckillCommodity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ActivityHtmlPageService {
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private SeckillActivityDao seckillActivityDao;
    @Autowired
    private SeckillCommodityDao seckillCommodityDao;
    /**
     * 创建html⻚⾯
     *
     * @throws Exception
     */
    public void createActivityHtml(long seckillActivityId) {
        PrintWriter writer = null;
        try {
            SeckillActivity seckillActivity =
                    seckillActivityDao.querySeckillActivityById(seckillActivityId);
            SeckillCommodity seckillCommodity =
                    seckillCommodityDao.querySeckillCommodityById(seckillActivity.getCommodityId(
                    ));
// 获取⻚⾯数据
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("seckillActivity", seckillActivity);
            resultMap.put("seckillCommodity", seckillCommodity);
            resultMap.put("seckillPrice", seckillActivity.getSeckillPrice());
            resultMap.put("oldPrice", seckillActivity.getOldPrice());
            resultMap.put("commodityId", seckillActivity.getCommodityId());
            resultMap.put("commodityName",
                    seckillCommodity.getName());
            resultMap.put("commodityDesc",
                    seckillCommodity.getDescription());
            // 创建thymeleaf上下⽂对象
            Context context = new Context();
            // 把数据放⼊上下⽂对象
            context.setVariables(resultMap);
            // 创建输出流
            File file = new File("src/main/resources/templates/"+ "seckill_item_" + seckillActivityId + ".html");
            // 执⾏⻚⾯静态化⽅法
            templateEngine.process("seckill_item_", context, writer);
        } catch (Exception e) {
            log.error(e.toString());
            log.error("⻚⾯静态化出错：" + seckillActivityId);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}