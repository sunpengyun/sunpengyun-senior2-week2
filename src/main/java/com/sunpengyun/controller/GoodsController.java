package com.sunpengyun.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sunpengyun.domain.Goods;
/**
 * 
    * @ClassName: GoodsController
    * @Description:Controller层
    * @author mypc
    * @date 2019年10月14日
    *
 */
@Controller
public class GoodsController {
    
	@Autowired
	private RedisTemplate redisTemplate;
	/**
	 * 
	    * @Title: findByList
	    * @Description: 对list集合进行分页搜索
	    * @param @param m
	    * @param @param page
	    * @param @param pageSize
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping("findByList")
	public String findByList(Model m,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10")Integer pageSize) {
		//使用redis模板查找list
		List<Goods> list = redisTemplate.opsForList().range("goods_list", (page-1)*pageSize, (page-1)*pageSize+pageSize-1);
		m.addAttribute("list", list);
		//设置分页属性
		if(page!=1) {
			m.addAttribute("prePage", page-1);
		}else {
			m.addAttribute("prePage", 1);
		}
		m.addAttribute("nextPage", page+1);
		return "list";
	}
	
	@RequestMapping("findByZset")
	public String findByZset(Model m,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10")Integer pageSize) {
		//使用redis模板查找Zset
		Set range = redisTemplate.opsForZSet().reverseRange("goods_zset", (page-1)*pageSize, (page-1)*pageSize+pageSize-1);
		m.addAttribute("list", range);
		//设置分页属性
		if(page!=1) {
			m.addAttribute("prePage", page-1);
		}else {
			m.addAttribute("prePage", 1);
		}
		m.addAttribute("nextPage", page+1);
		return "listByZset";
	}

}
