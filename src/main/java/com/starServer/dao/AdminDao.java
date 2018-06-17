package com.starServer.dao;

import com.starServer.entity.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 所有的传参顺序第一个都是businessId
 */
@Repository
public interface AdminDao {

    Admin getAdminByUsername(String username);

    Admin getAdminByAccessToken(String accessToken);

    boolean updateToken(Admin admin);

    boolean saveAdmin(Admin admin);

    boolean deleteAdmin(int id);

    List<Admin> getAdminListByPage(int limit, int offset);
    
    int getAdminCount();
}
