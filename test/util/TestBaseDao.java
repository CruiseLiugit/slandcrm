package util;

import org.junit.Test;

public class TestBaseDao {
	@Test
	public void testConnection() {
		System.out.println(HibernateSessionFactory.getSession()
				.beginTransaction());
	}

}
