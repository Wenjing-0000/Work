package com.wenjing.seckill.controller;

import com.alibaba.fastjson.JSON;
import com.wenjing.seckill.db.dao.SeckillActivityDao;
import com.wenjing.seckill.db.dao.SeckillCommodityDao;
import com.wenjing.seckill.db.dao.SeckillOrderDao;
import com.wenjing.seckill.db.po.SeckillActivity;
import com.wenjing.seckill.db.po.SeckillCommodity;
import com.wenjing.seckill.db.po.SeckillOrder;
import com.wenjing.seckill.services.RedisService;
import com.wenjing.seckill.services.SeckillActivityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Slf4j
@Controller
public class SeckillActivityController {

    @Autowired
    private SeckillActivityDao seckillActivityDao;

    @Autowired
    private SeckillActivityService seckillActivityService;

    @Autowired
    private SeckillOrderDao orderDao;

    @Autowired
    RedisService redisService;

    @Autowired
    private SeckillCommodityDao seckillCommodityDao;


    @RequestMapping("/addSeckillActivity")
    public String addSeckillActivity(){
        return "add_activity";
    }

    @RequestMapping("/addSeckillActivityAction")
    public String addSeckillActivityAction(HttpServletRequest request) throws ParseException {

        String name = request.getParameter("name");

        String commodityId = request.getParameter("commodityId");

//        BigDecimal oldPrice = new BigDecimal(request.getParameter("oldPrice"));
//
//        BigDecimal seckillPrice = new BigDecimal(request.getParameter("seckillPrice"));
//
//        Long seckillNumber = Long.valueOf(request.getParameter("totalStock"));

        String oldPrice = request.getParameter("oldPrice");

        String seckillPrice = request.getParameter("seckillPrice");

        String seckillNumber = request.getParameter("seckillNumber");

        String startTime = request.getParameter("startTime");

        String endTime = request.getParameter("endTime");

        System.out.println(commodityId);
        Long commodityIdL = Long.valueOf(commodityId);
        BigDecimal oldPriceBD = new BigDecimal(oldPrice);
        BigDecimal seckillPriceBD = new BigDecimal(seckillPrice);
        Long seckillNumberL = Long.valueOf(seckillNumber);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddhh:mm");

        System.out.println(name);
        System.out.println(commodityIdL);
        System.out.println(oldPriceBD);
        System.out.println(seckillPriceBD);
        System.out.println(seckillNumberL);
        System.out.println(format.parse(startTime.substring(0, 10) +  startTime.substring(11)));
        System.out.println(format.parse(endTime.substring(0, 10) +  endTime.substring(11)));

        SeckillActivity seckillActivity = new SeckillActivity();
        seckillActivity.setName(name);
        seckillActivity.setCommodityId(Long.valueOf(commodityId));
        seckillActivity.setOldPrice(new BigDecimal(oldPrice));
        seckillActivity.setSeckillPrice(new BigDecimal(seckillPrice));
        seckillActivity.setTotalStock(Long.valueOf(seckillNumber));
        seckillActivity.setActivityStatus(1);
        seckillActivity.setAvailableStock(Integer.valueOf("" + Long.valueOf(seckillNumber)));
        seckillActivity.setLockStock(0L);
        seckillActivity.setStartTime(format.parse(startTime.substring(0, 10) +  startTime.substring(11)));
        seckillActivity.setEndTime(format.parse(endTime.substring(0, 10) +  endTime.substring(11)));
        seckillActivityDao.insertSeckillActivity(seckillActivity);
        return "addSeckillActivityAction";
    }



    @RequestMapping("/register")
    public String register(HttpServletRequest request) {
        String name = request.getParameter("name"); //从页面获取输入的username

        if (name != null) {
            System.out.println(name);
        }

        return "register";
    }

    @ResponseBody
    @RequestMapping("/seckill/{seckillActivityId}")
    public String seckillCommodity(@PathVariable long seckillActivityId) {
        boolean stockValidateResult =
                seckillActivityService.seckillStockValidator(seckillActivityId);
        return stockValidateResult ? "恭喜你秒杀成功" : "商品已经售完，下次再来";
    }

    @RequestMapping("/seckill/orderQuery/{userId}/{orderNo}")
    public ModelAndView orderQuery(@PathVariable String orderNo,
                                   @PathVariable String userId) {
        log.info("订单查询，订单号：" + orderNo);
        SeckillOrder order = orderDao.queryOrder(orderNo);
        ModelAndView modelAndView = new ModelAndView();
        if (order != null) {
            modelAndView.setViewName("order");
            modelAndView.addObject("order", order);
            SeckillActivity seckillActivity =
                    seckillActivityDao.querySeckillActivityById(order.getSeckillActivityId());
            modelAndView.addObject("seckillActivity", seckillActivity);
        } else {
            modelAndView.setViewName("order_wait");
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/seckill/buy/{userId}/{seckillActivityId}")
    public ModelAndView seckillCommodity(@PathVariable long userId,
                                         @PathVariable long seckillActivityId) {
        boolean stockValidateResult = false;
        ModelAndView modelAndView = new ModelAndView();
        try {
            /*
             * 判断⽤户是否在已购名单中
             */
            if (redisService.isInLimitMember(seckillActivityId, userId)) {
                //提示⽤户已经在限购名单中，返回结果
                modelAndView.addObject("resultInfo", "对不起，您已经在限购名单中");
                        modelAndView.setViewName("seckill_result");
                return modelAndView;
            }
            /*
             * 确认是否能够进⾏秒杀
             */
            stockValidateResult =
                    seckillActivityService.seckillStockValidator(seckillActivityId);
            if (stockValidateResult) {
                SeckillOrder order =
                        seckillActivityService.createOrder(seckillActivityId, userId);
                modelAndView.addObject("resultInfo", "秒杀成功，订单创建中，订单 ID："
                        + order.getCommodityId());
                        modelAndView.addObject("orderNo", order.getCommodityId());
                //添加⽤户到已购名单中
                redisService.addLimitMember(seckillActivityId, userId);
            } else {
                modelAndView.addObject("resultInfo", "对不起，商品库存不⾜");
            }
        } catch (Exception e) {
            log.error("秒杀系统异常" + e.toString());
            modelAndView.addObject("resultInfo", "秒杀失败");
        }
        modelAndView.setViewName("seckill_result");
        return modelAndView;
    }

    /**
     * 秒杀商品详情
     *
     * @param resultMap
     * @param seckillActivityId
     * @return
     */
    @RequestMapping("/seckill/detail/{seckillActivityId}")
    public String itemPage(Map<String, Object> resultMap, @PathVariable long
            seckillActivityId) {
        SeckillActivity seckillActivity;
        SeckillCommodity seckillCommodity;
        String seckillActivityInfo = redisService.getValue("seckillActivity:"
                + seckillActivityId);
        if (StringUtils.isNotEmpty(seckillActivityInfo)) {
            log.info("redis缓存数据:" + seckillActivityInfo);
            seckillActivity = JSON.parseObject(seckillActivityInfo,
                    SeckillActivity.class);
        } else {
            seckillActivity =
                    seckillActivityDao.querySeckillActivityById(seckillActivityId);
        }
        String seckillCommodityInfo =
                redisService.getValue("seckillCommodity:" +
                        seckillActivity.getCommodityId());
        if (StringUtils.isNotEmpty(seckillCommodityInfo)) {
            log.info("redis缓存数据:" + seckillCommodityInfo);
            seckillCommodity = JSON.parseObject(seckillActivityInfo,
                    SeckillCommodity.class);
        } else {
            seckillCommodity =
                    seckillCommodityDao.querySeckillCommodityById(seckillActivity.getCommodityId());
        }
        resultMap.put("seckillActivity", seckillActivity);
        resultMap.put("seckillCommodity", seckillCommodity);
        resultMap.put("seckillPrice", seckillActivity.getSeckillPrice());
        resultMap.put("oldPrice", seckillActivity.getOldPrice());
        resultMap.put("commodityId", seckillActivity.getCommodityId());
        resultMap.put("commodityName", seckillCommodity.getName());
        resultMap.put("commodityDesc", seckillCommodity.getDescription());
        return "seckill_detail";
    }

    /**
     * 获取当前服务器端的时间
     * @return
     */
    @ResponseBody
    @RequestMapping("/seckill/getSystemTime")
    public String getSystemTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间
        return date;
    }

}
