package com.sdf.service;

import com.sdf.dao.UserDao;
import com.sdf.dao.impl.UserDaoImpl;
import com.sdf.domain.Scanner;

public interface ScannerService {

    UserDao dao = new UserDaoImpl();

    Scanner ScannerLogin(Scanner scanner);
}
