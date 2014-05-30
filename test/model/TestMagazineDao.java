package model;

import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.sland.model.dao.CommentDao;
import com.sland.model.dao.MagazineDao;
import com.sland.model.pojo.Comment;
import com.sland.model.pojo.MyEntity;

public class TestMagazineDao {

	@Test
	public void testFindAllStatuYears(){
		MagazineDao dao = new MagazineDao();
		List<MyEntity> yearlist = dao.findAllStatuYears();
		
	}
	
	@Test
	public void testFindCommentById(){
		CommentDao dao = new CommentDao();
		Comment entity = dao.findCommentById("1");
		System.out.println("topic id = "+entity.getTopic().getId());
		System.out.println("periodical id = "+entity.getPeriodical().getId());
		System.out.println("statu = "+entity.getStatu());
	}
	
	
	
	
	@Test
	public void testGetAllStatuYearsJSON(){
		MagazineDao dao = new MagazineDao();
		List<MyEntity> yearlist = dao.findAllStatuYears();
		// 返回的 JSON 对象，包含所有年份字段的 数组对象
		Gson gson = new Gson();
		String jsonstr = gson.toJson(yearlist);
		System.out.println(jsonstr);
	}
	
	
}
