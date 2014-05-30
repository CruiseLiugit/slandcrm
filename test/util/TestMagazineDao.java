package util;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.Map;

import com.sland.model.dao.MagazineDao;
import com.sland.model.pojo.*;
import com.sland.model.pojo.entity.PeriodicalPageEntity;

public class TestMagazineDao {
	private MagazineDao dao = null;

	@Before
	public void getDao() {
		dao = new MagazineDao();
	}

	@Test
	public void testSaveYear() {
		Year y = new Year();
		y.setStatu(1);
		y.setYearname("2013");
		
		dao.saveYear(y);
	}
	
	@Test
	public void testListYear() {
		List<Year> list = dao.findAllYears();
		for (Year year : list) {
			System.out.println(year.getYearname()+"   statu:"+year.getStatu());
		}
	}
	
	
	//查询 期刊 菜单点击后，进入页面的数据
	@Test
	public void testListPeriodicals(){
		List<PeriodicalPageEntity> list = dao.findAllPeriodicals();
		System.out.println(list.size());		
		for (PeriodicalPageEntity myEntity : list) {
			System.out.println(myEntity.getTitle());
		}
		
	}
	
	//查询所有 期刊，供生成 List.xml 
		@Test
		public void testListAllMagazines(){
			Map<String,List> map = dao.getListXml();
			
			
		}
	
	
}
