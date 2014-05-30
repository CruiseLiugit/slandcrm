package util;

import org.junit.Test;

import com.sland.model.dao.PeriodicalDao;
import com.sland.model.pojo.Periodical;

public class TestPeriodical {
	
	/**
	 * 测试根据绝对路径得到图片
	 */
	@Test
	public void testfindPeriodicalByCoverThunbname()
	{
		PeriodicalDao dao  = new PeriodicalDao();
		Periodical peri = dao.findPeriodicalByCoverThunbname("sfrontcover_1385825353.png");
		System.out.println(peri.getId());
	}

	@Test
	public void testAddApprove()
	{
		PeriodicalDao dao  = new PeriodicalDao();
		dao.getTopicByThunbname("1385825377013.jpg");
		
	}
}
