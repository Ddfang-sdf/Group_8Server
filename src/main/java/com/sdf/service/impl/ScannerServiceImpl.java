package com.sdf.service.impl;

import com.sdf.dao.UserDao;
import com.sdf.dao.impl.UserDaoImpl;
import com.sdf.domain.Scanner;
import com.sdf.service.ScannerService;

public class ScannerServiceImpl implements ScannerService {
    UserDao dao = new UserDaoImpl();
    @Override
    public Scanner ScannerLogin(Scanner scanner) {

        return dao.findForLogin(scanner);
    }
}
