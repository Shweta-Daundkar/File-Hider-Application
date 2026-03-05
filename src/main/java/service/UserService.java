package service;

import java.sql.SQLException;

import dao.UserDao;
import model.User;

public class UserService {
	public static Integer saveUser(User user) {
		try {
			if(UserDao.isExists(user.getEmail())) {
				return 0;
			}else {
				UserDao.saveUser(user);
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return 0;
	}

}
