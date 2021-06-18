package com.jiuzhang.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@ComponentScan(basePackages = {"com.jiuzhang"})
@SpringBootApplication
@MapperScan("com.jiuzhang.seckill.db.mappers")
public class SeckillApplication {

	@RequestMapping("/addSeckillActivity")
	public String addSeckillActivity(){
		return "add_activity";
	}

	@ResponseBody
	@RequestMapping("/addSeckillActivityAction")
	public String addSeckillActivityAction(
			@RequestParam("name") String name,
			@RequestParam("commodityId") long commodityId,
			@RequestParam("seckillPrice") BigDecimal seckillPrice,
			@RequestParam("oldPrice") BigDecimal oldPrice,
			@RequestParam("seckillNumber") long seckillNumber
	) {
		SeckillActivity seckillActivity = new SeckillActivity();
		seckillActivity.setName(name);
		seckillActivity.setCommodityId(commodityId);
		seckillActivity.setSeckillPrice(seckillPrice);
		seckillActivity.setOldPrice(oldPrice);
		seckillActivity.setSeckillNumber(seckillNumber);
		seckillActivity.setActivityStatus(1);
		seckillActivityDao.inertSeckillActivity(seckillActivity);
		return seckillActivity.toString();
	}


	public static void main(String[] args) {
		SpringApplication.run(SeckillApplication.class, args);
	}




}
