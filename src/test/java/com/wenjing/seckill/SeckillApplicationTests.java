package com.wenjing.seckill;

import com.wenjing.seckill.db.dao.*;
import com.wenjing.seckill.db.po.SeckillActivity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
class SeckillApplicationTests {

//	@Test
//	void contextLoads() throws IOException {
//		String resource = "mybatis-config.xml";
//		InputStream inputStream = Resources.getResourceAsStream(resource);
//		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//		SqlSession openSession = sqlSessionFactory.openSession();
//
//		try{
//			SeckillCommodity commodity = openSession.selectOne("com.wenjing.seckill.SeckillCommodityMapper.selectSeckill",1);
//			System.out.println(commodity);
//		}finally {
//			openSession.close();
//		}
//	}

	@Autowired
	SeckillActivityMapper seckillActivityMapper;

	@Test
	void contextLoads() throws IOException {
//		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
//
//		SqlSession openSession = sqlSessionFactory.openSession(true);


			//Commodity
//			SeckillCommodityMapper mapper = openSession.getMapper(SeckillCommodityMapper.class);
//			//add
//			SeckillCommodity commodity = new SeckillCommodity();
//			commodity.setId(122L);
//			commodity.setName("paper");
//			commodity.setDescription("paper");
//			commodity.setPrice(12);
//			mapper.addCommodity(commodity);
//			openSession.commit();
//
//			//check
//			mapper.getCommodityById(122L);
//			openSession.commit();


			//Activity
//			SeckillActivityMapper mapper = openSession.getMapper(SeckillActivityMapper.class);
			//add


			SeckillActivity activity= new SeckillActivity();
			activity.setId(19L);
			activity.setName("ss");
			activity.setCommodityId(323L);
			activity.setOldPrice(new BigDecimal(234));
			activity.setSeckillPrice(new BigDecimal(199));
			activity.setActivityStatus(1);
			activity.setTotalStock(200L);
			activity.setAvailableStock(200);
			activity.setLockStock(200L);
//
			seckillActivityMapper.addActivity(activity);
//

//			mapper.addActivity(activity);
//			openSession.commit();
//
//			//check
//			mapper.getActivityById(13);
//			openSession.commit();


			//Order
//			SeckillOrderMapper mapper = openSession.getMapper(SeckillOrderMapper.class);
//			//add
//			SeckillOrder order= new SeckillOrder();
//			order.setId(123L);
//			order.setCommodityId(123L);
//			order.setSeckillActivityId(232L);
//			order.setMoney(new BigDecimal(222));
//			order.setUserId(13579L);
//			order.setStatus(1);
//			order.setPayTime(new Date(2021-1900, Calendar.MAY, 12));
//			order.setCreateTime(new Date(2021-1900, Calendar.MAY, 11));
//			mapper.addOrder(order);
//			openSession.commit();
//
//			//update
//			SeckillOrder order= new SeckillOrder();
//			order.setId(123L);
//			order.setCommodityId(233L);
//			order.setSeckillActivityId(56L);
//			order.setMoney(new BigDecimal(43));
//			order.setUserId(13579L);
//			order.setStatus(1);
//			order.setPayTime(new Date(2021-1900, Calendar.MAY, 22));
//			order.setCreateTime(new Date(2021-1900, Calendar.MAY, 21));
//			mapper.updateOrder(order);
//			openSession.commit();
//
//			//delete
//			mapper.deleteOrder(123L);
//			openSession.commit();


			//User
//			SeckillUserMapper mapper = openSession.getMapper(SeckillUserMapper.class);
//			//add
//			SeckillUser user= new SeckillUser();
//			user.setId(1L);
//			user.setUserName("Philip");
//			user.setAddress("Canada");
//			user.setPhone("123456");
//			mapper.addUser(user);
//			openSession.commit();
//
//			//update
//			SeckillUser user= new SeckillUser();
//			user.setId(1L);
//			user.setUserName("Philip");
//			user.setAddress("China");
//			user.setPhone("22334455");
//			mapper.updateUser(user);
//			openSession.commit();
//
//			//delete
//			mapper.deleteUser(1L);
//			openSession.commit();
//
//			//check
//			mapper.getUserById(1);
//			openSession.commit();



	}

}
