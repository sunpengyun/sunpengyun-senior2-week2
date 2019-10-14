package com.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sunpengyun.domain.Goods;
import com.sunpengyun.utils.StreamUtil;
import com.sunpengyun.utils.StringUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-beans.xml")
public class RedisTest {
	
	@Autowired
	private RedisTemplate RedisTemplate;
	
	@Test
	public void testTxt() throws IOException {
		//加载一个文件对象
		File f = new File("E:\\Goods.txt");
		//使用工具包读取文件内容
		String txt = StreamUtil.readTextFile(f);
		BufferedReader br = new BufferedReader(new FileReader(f));
		//System.out.println(txt);
		//把¥替换成空串
		txt.replace("¥","");
		//把%替换成空串
		txt.replace("%","");
		//声明一个String类型的字符串
	     String num = null;
	     int i = 1;
	     while((num=br.readLine())!=null) {
	    	 //根据==切割文本内容，每次读取一行数据
	    	String[] split = num.split("==");
	    	//调用构造方法，生成106个对象
	    	//调用工具包的hashtest方法判断是否有值
	    	if(StringUtil.hasText(split[1])&&StringUtil.isNum(i)) {
	    		Goods g = new Goods(i, split[1], split[2], split[3]);	    		
	    		System.out.println(g);
	    		RedisTemplate.opsForList().leftPush("goods_list", g);
	    	}else {
	    		System.out.println("id不是数字或者商品名称不是一个有效文本!");
	    		continue;
	    	}
            //输出测试
            //使用redis模板存入list集合
            i++;
	     }
		System.out.println("List序列化成功!");
		
		
	}
	
	
	@Test
	public void testZset() throws IOException {
		File f = new File("E:\\Goods.txt");
		String txt = StreamUtil.readTextFile(f);
		//把¥替换成空串
	    txt.replace("¥","");
		//把%替换成空串
	    txt.replace("%","");
		BufferedReader br = new BufferedReader(new FileReader(f));
		String num = null;
	     int i = 1;
	     while((num=br.readLine())!=null) {
	    	 //根据==切割
	    	String[] split = num.split("==");
	    	//调用构造方法，生成106个对象
	    	if(StringUtil.hasText(split[1])&&StringUtil.isNum(i)) {
	    		Goods g = new Goods(i, split[1], split[2], split[3]);	    		
	    		System.out.println(g);
	    		RedisTemplate.opsForZSet().add("goods_zset", g,i);
	    	}else {
	    		System.out.println("id不是数字或者商品名称不是一个有效文本!");	
	    	}
           //使用redis模板，放入Zset集合里面
           i++;
	     }
	     System.out.println("Zset序列化成功");
	}
	

}
