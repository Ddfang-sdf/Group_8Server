package com.sdf.dao;

import com.sdf.domain.Scanner;

public interface UserDao {
    Scanner findForLogin(Scanner scanner);
}
