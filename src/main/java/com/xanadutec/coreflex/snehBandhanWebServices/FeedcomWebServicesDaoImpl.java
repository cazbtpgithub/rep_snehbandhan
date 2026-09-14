package com.xanadutec.coreflex.snehBandhanWebServices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.xanadutec.coreflex.model.RoleModel;
import com.xanadutec.coreflex.model.Status;
import com.xanadutec.coreflex.model.UserModel;


@Repository("FeedcomWebServicesDao")
public class FeedcomWebServicesDaoImpl implements FeedcomWebServicesDao{
	
	
	
	
}
