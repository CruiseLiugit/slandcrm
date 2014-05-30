package util;

import org.junit.Before;
import org.junit.Test;

import com.sland.model.dao.UserDao;
import com.sland.model.pojo.Userinfo;

public class TestUser {
	private UserDao dao = null;

	@Before
	public void getDao() {
		dao = new UserDao();
	}

	@Test
	public void testLogin() {
		Userinfo info = dao.login("aaa", "aaa");
		System.out.println(info);
		System.out.println("name ="+info.getRealname());
		
	}

}
