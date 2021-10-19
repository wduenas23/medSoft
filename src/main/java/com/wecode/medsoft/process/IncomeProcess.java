package com.wecode.medsoft.process;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wecode.medsoft.contracts.incomes.RequestIncome;
import com.wecode.medsoft.contracts.incomes.Totals;
import com.wecode.medsoft.contracts.medicalservices.MedicalServiceResponse;
import com.wecode.medsoft.entities.PaymentType;
import com.wecode.medsoft.entities.Transaction;
import com.wecode.medsoft.entities.TransactionCategory;
import com.wecode.medsoft.entities.TransactionDetail;
import com.wecode.medsoft.persistence.TransactionDetailRepository;
import com.wecode.medsoft.persistence.TransactionRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class IncomeProcess {

	
	private TransactionRepository tranRepository;
	private TransactionDetailRepository detailRepository;
	
	IncomeProcess(TransactionRepository tranRepository,TransactionDetailRepository detailRepository){
		this.tranRepository=tranRepository;
		this.detailRepository=detailRepository;
	}
	
	
	public void saveIncome(RequestIncome requestIncome) {
		Transaction transaction=null;
		TransactionDetail tranDetail=new TransactionDetail();

		try {
			ObjectMapper mapper=new ObjectMapper();
			log.info("Request Income: {}",mapper.writeValueAsString(requestIncome));
			transaction=buildTransaction(requestIncome);
			Transaction newTransaction=tranRepository.save(transaction);
			tranDetail=builTransactionDetail(newTransaction, requestIncome.getServices());
			detailRepository.save(tranDetail);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	public Transaction buildTransaction(RequestIncome requestIncome) throws Exception {
		Transaction transaction=new Transaction();
		try {
			
			PaymentType pt=new PaymentType();
			pt.setPtId(requestIncome.getFormOfPayment().getId());
			transaction.setPaymentType(pt);
			
			TransactionCategory tc=new TransactionCategory();
			tc.setTcId(1);
			transaction.setTransactionCategory(tc);
			
			transaction.setTxDate(new Timestamp(requestIncome.getTxDate().getTime()));
			transaction.setTxTransSubtotal(requestIncome.getServices().stream().mapToDouble(o -> o.getCost()).sum());
			transaction.setTxTransDiscount(getTotal("Descuentos",requestIncome.getTotals()));
			transaction.setTxDiscountPercentage(requestIncome.getDiscount());
			transaction.setTxTransClientTotal(getTotal("Sub Total Cliente", requestIncome.getTotals()));
			transaction.setTxTransFee(getTotal("Comisiones",requestIncome.getTotals()));
			transaction.setTxTransTotal(getTotal("Total",requestIncome.getTotals()));
			ObjectMapper mapper=new ObjectMapper();
			log.info("Objecto Transaction: {}",mapper.writeValueAsString(transaction));
			return transaction;
		} catch (Exception e) {
			throw e;
		}
	
	}
	
	public Double getTotal(String totalTitle, List<Totals> totals) {
		return totals.stream().filter(total-> totalTitle.equalsIgnoreCase(total.getTitle())).findAny().get().getValue();
	}
	
	public TransactionDetail builTransactionDetail(Transaction trx, List<MedicalServiceResponse> services) {
		TransactionDetail tdetail=null;
		com.wecode.medsoft.entities.Service service=null;
		try {
			for (MedicalServiceResponse medicalServiceResponse : services) {
				tdetail=new TransactionDetail();
				tdetail.setTransaction(trx);
				service=new com.wecode.medsoft.entities.Service();
				service.setSvId(medicalServiceResponse.getId());
				tdetail.setService(service);
			}
			return tdetail;
		} catch (Exception e) {
			throw e;
		}
	}
}
