package com.wecode.medsoft.process;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wecode.medsoft.contracts.incomes.RequestIncome;
import com.wecode.medsoft.contracts.incomes.ResponseIncome;
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
	
	
	public ResponseIncome saveIncome(RequestIncome requestIncome) {
		Transaction transaction=null;
		TransactionDetail tranDetail=new TransactionDetail();
		ResponseIncome responseIncome=new ResponseIncome();
		try {
			ObjectMapper mapper=new ObjectMapper();
			log.info("Request Income: {}",mapper.writeValueAsString(requestIncome));
			transaction=buildTransaction(requestIncome);
			Transaction newTransaction=tranRepository.save(transaction);
			
			for (MedicalServiceResponse medicalServiceResponse : requestIncome.getServices()) {
				tranDetail=builTransactionDetail(newTransaction, medicalServiceResponse);
				detailRepository.save(tranDetail);
			}
			responseIncome.setCode("200");
			responseIncome.setMessage("SUCESSFULL");
		} catch (Exception e) {
			responseIncome.setCode("500");
			responseIncome.setMessage("ERROR " +e.getMessage() );
			log.error(e.getMessage());
		}
		return responseIncome;
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
			transaction.setTxNombres(requestIncome.getNombres());
			transaction.setTxApellidos(requestIncome.getApellidos());
			transaction.setTxTelefono(requestIncome.getTelefono());
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
	
	public TransactionDetail builTransactionDetail(Transaction trx,MedicalServiceResponse medicalService) {
		TransactionDetail tdetail=null;
		com.wecode.medsoft.entities.Service service=null;
		try {
			tdetail=new TransactionDetail();
			tdetail.setTransaction(trx);
			service=new com.wecode.medsoft.entities.Service();
			service.setSvId(medicalService.getId());
			tdetail.setService(service);
			return tdetail;
		} catch (Exception e) {
			throw e;
		}
	}
}
