package com.xanadutec.coreflex.PaymentGateway;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xanadutec.coreflex.model.FcmNotification;
import com.xanadutec.coreflex.model.PaymentGatewayTransactionSummary;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelDetails;

@Service("PaymentGatewayService")
public class PaymentGatewayServiceImpl implements PaymentGatewayService {

	@Autowired
	PaymentGatewayDao paymentGatewayDao;

	@Override
	public PaymentGatewayTransactionSummary savePaymentGatewayTransactionSummary(
			PaymentGatewayTransactionSummary paymentGatewayTransactionSummary) {

		return paymentGatewayDao.savePaymentGatewayTransactionSummary(paymentGatewayTransactionSummary);

	}

	@Override
	public PaymentGatewayTransactionSummary getPaymentGatewayTransactionSummaryByTransactionId(String trx_id) {
		return paymentGatewayDao.getPaymentGatewayTransactionSummaryByTransactionId(trx_id);
	}
}
