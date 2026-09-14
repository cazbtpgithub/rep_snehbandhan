package com.xanadutec.coreflex.PaymentGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.xanadutec.coreflex.model.PaymentGatewayTransactionSummary;

@Repository("PaymentGatewayDao")
@Transactional
public class PaymentGatewayDaoImpl implements PaymentGatewayDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public PaymentGatewayTransactionSummary savePaymentGatewayTransactionSummary(
			PaymentGatewayTransactionSummary paymentGatewayTransactionSummary) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(paymentGatewayTransactionSummary);
			} catch (Exception e) {
			e.printStackTrace();
		}
		return paymentGatewayTransactionSummary;
	}

	@Override
	public PaymentGatewayTransactionSummary getPaymentGatewayTransactionSummaryByTransactionId(String trx_id) {
		
		try {
				Session session=sessionFactory.getCurrentSession();
				Criteria criteria=session.createCriteria(PaymentGatewayTransactionSummary.class);
				criteria.add(Restrictions.eq("txn_Id", trx_id));
				PaymentGatewayTransactionSummary txtObject=(PaymentGatewayTransactionSummary) criteria.uniqueResult();
				return txtObject;
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

}
