package com.xanadutec.coreflex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.RoleModel;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        
        // Ensure Role 1 exists
        RoleModel adminRole = (RoleModel) session.get(RoleModel.class, 1);
        if (adminRole == null) {
            session.createSQLQuery("INSERT IGNORE INTO roles (id, role) VALUES (1, 'ROLE_ADMIN')").executeUpdate();
            adminRole = (RoleModel) session.get(RoleModel.class, 1);
        }
        
        // Ensure user 'santosh' exists
        Long count = ((Number) session.createQuery("select count(u) from UserModel u where u.userName = 'santosh'").uniqueResult()).longValue();
        if (count == 0) {
            UserModel user = new UserModel();
            user.setUserName("santosh");
            user.setPassword("santosh");
            user.setUserRoll(adminRole);
            user.setFirstName("Santosh Admin");
            session.save(user);
        } else {
            // Update password if exists
            UserModel user = (UserModel) session.createQuery("from UserModel u where u.userName = 'santosh'").setMaxResults(1).uniqueResult();
            user.setPassword("santosh");
            user.setUserRoll(adminRole);
            session.update(user);
        }
    }
}
