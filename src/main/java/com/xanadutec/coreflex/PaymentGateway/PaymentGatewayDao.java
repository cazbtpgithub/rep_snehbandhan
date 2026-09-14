package com.xanadutec.coreflex.PaymentGateway;

import com.xanadutec.coreflex.model.PaymentGatewayTransactionSummary;

public interface PaymentGatewayDao {
	
	public PaymentGatewayTransactionSummary savePaymentGatewayTransactionSummary(
			PaymentGatewayTransactionSummary paymentGatewayTransactionSummary);
	public PaymentGatewayTransactionSummary getPaymentGatewayTransactionSummaryByTransactionId(String trx_id);
}
