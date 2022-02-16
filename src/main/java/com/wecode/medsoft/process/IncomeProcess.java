package com.wecode.medsoft.process;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wecode.medsoft.contracts.incomes.IncomeDetails;
import com.wecode.medsoft.contracts.incomes.PaymentDetails;
import com.wecode.medsoft.contracts.incomes.RequestIncome;
import com.wecode.medsoft.contracts.incomes.ResponseIncome;
import com.wecode.medsoft.contracts.incomes.Totals;
import com.wecode.medsoft.contracts.medicalservices.MedicalServiceResponse;
import com.wecode.medsoft.contracts.patient.PatientInfo;
import com.wecode.medsoft.contracts.product.ProductPojo;
import com.wecode.medsoft.entities.Patient;
import com.wecode.medsoft.entities.PaymentDetail;
import com.wecode.medsoft.entities.PaymentType;
import com.wecode.medsoft.entities.Product;
import com.wecode.medsoft.entities.Transaction;
import com.wecode.medsoft.entities.TransactionCategory;
import com.wecode.medsoft.entities.TransactionDetail;
import com.wecode.medsoft.entities.TransactionDetailSale;
import com.wecode.medsoft.persistence.PaymentDetailRepository;
import com.wecode.medsoft.persistence.TransactionCategoryRepository;
import com.wecode.medsoft.persistence.TransactionDetailRepository;
import com.wecode.medsoft.persistence.TransactionDetailSaleRepository;
import com.wecode.medsoft.persistence.TransactionRepository;
import com.wecode.medsoft.persistence.TransactionSummaryRepositoryImplementation;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IncomeProcess {

	
	private TransactionRepository tranRepository;
	private TransactionDetailRepository detailRepository;
	private TransactionDetailSaleRepository detailSaleRepository;
	private TransactionSummaryRepositoryImplementation summaryRepository;
	private TransactionCategoryRepository transactionCategoryRepository;
	private PaymentDetailRepository paymentDetailRepository;
	private PatientProcess patientProcess;
	private final static String SERVICES="INGRESOS POR SERVICIOS MEDICOS";
	private final static String SALES="INGRESOS POR VENTAS";
	
	IncomeProcess(TransactionRepository tranRepository,TransactionDetailRepository detailRepository,TransactionSummaryRepositoryImplementation summaryRepository,PaymentDetailRepository paymentDetailRepository,PatientProcess patientProcess,TransactionCategoryRepository transactionCategoryRepository,TransactionDetailSaleRepository detailSaleRepository){
		this.tranRepository=tranRepository;
		this.detailRepository=detailRepository;
		this.summaryRepository=summaryRepository;
		this.paymentDetailRepository=paymentDetailRepository;
		this.patientProcess=patientProcess;
		this.transactionCategoryRepository=transactionCategoryRepository;
		this.detailSaleRepository=detailSaleRepository;
	
	}
	
	@Transactional
	public ResponseIncome saveIncome(RequestIncome requestIncome) {
		Transaction transaction=null;
		TransactionDetail tranDetail=new TransactionDetail();
		PaymentDetail payDetail=null;
		ResponseIncome responseIncome=new ResponseIncome();
		try {
			ObjectMapper mapper=new ObjectMapper();
			log.info("Request Income: {}",mapper.writeValueAsString(requestIncome));
			
			
			transaction=buildTransaction(requestIncome,SERVICES);
			Patient patient=patientProcess.createPatient(requestIncome);
			transaction.setPatient(patient);
			Transaction newTransaction=tranRepository.save(transaction);
			
			//deleting tx Details
			List<TransactionDetail> trxDetail= detailRepository.findByTransaction(newTransaction);
			if(trxDetail!=null && !trxDetail.isEmpty()) {
				for (TransactionDetail transactionDetail : trxDetail) {
					detailRepository.delete(transactionDetail);
				}
			}
			
			for (MedicalServiceResponse medicalServiceResponse : requestIncome.getServices()) {
				tranDetail=builTransactionDetail(newTransaction, medicalServiceResponse);
				detailRepository.save(tranDetail);
			}
			
			//deleting payment type
			List<PaymentDetail> paymentDetails= paymentDetailRepository.findByTransaction(newTransaction);
			if(paymentDetails!=null && !paymentDetails.isEmpty()) {
				for (PaymentDetail paymentDetail : paymentDetails) {
					paymentDetailRepository.delete(paymentDetail);
				}
			}
			
			for(PaymentDetails pd: requestIncome.getPaymentDetails()) {
				payDetail=buildPaymentDetail(newTransaction,pd);
				paymentDetailRepository.save(payDetail);
			}
			
			
			responseIncome.setCode("200");
			responseIncome.setMessage("SUCCESSFULL");
		} catch (Exception e) {
			responseIncome.setCode("500");
			responseIncome.setMessage("ERROR " +e.getMessage() );
			log.error(e.getMessage());
		}
		return responseIncome;
	}
	
	@Transactional
	public ResponseIncome saveIncomeSales(RequestIncome requestIncome) {
		Transaction transaction=null;
		TransactionDetailSale tranDetailSale=new TransactionDetailSale();
		PaymentDetail payDetail=null;
		ResponseIncome responseIncome=new ResponseIncome();
		try {
			ObjectMapper mapper=new ObjectMapper();
			log.info("Request Income: {}",mapper.writeValueAsString(requestIncome));
			
			
			transaction=buildTransaction(requestIncome,SALES);
			Patient patient=patientProcess.createPatient(requestIncome);
			transaction.setPatient(patient);
			Transaction newTransaction=tranRepository.save(transaction);
			
			//deleting tx Details Sale
			List<TransactionDetailSale> trxDetail=detailSaleRepository.findByTransaction(newTransaction);
			if(trxDetail!=null && !trxDetail.isEmpty()) {
				for (TransactionDetailSale transactionDetailSale : trxDetail) {
					detailSaleRepository.delete(transactionDetailSale);
				}
			}
			
			requestIncome.getProducts();
			for(ProductPojo prod: requestIncome.getProducts()) {
				tranDetailSale=builTransactionDetailSale(newTransaction, prod);
				detailSaleRepository.save(tranDetailSale);
			}
			
			
			//deleting payment type
			List<PaymentDetail> paymentDetails= paymentDetailRepository.findByTransaction(newTransaction);
			if(paymentDetails!=null && !paymentDetails.isEmpty()) {
				for (PaymentDetail paymentDetail : paymentDetails) {
					paymentDetailRepository.delete(paymentDetail);
				}
			}
			
			for(PaymentDetails pd: requestIncome.getPaymentDetails()) {
				payDetail=buildPaymentDetail(newTransaction,pd);
				paymentDetailRepository.save(payDetail);
			}
			
			//Descargar inventario
			
			responseIncome.setCode("200");
			responseIncome.setMessage("SUCCESSFULL");
		} catch (Exception e) {
			responseIncome.setCode("500");
			responseIncome.setMessage("ERROR " +e.getMessage() );
			log.error(e.getMessage());
		}
		return responseIncome;
	}
	
	

	private PaymentDetail buildPaymentDetail(Transaction newTransaction, PaymentDetails pd) {
		PaymentDetail pdetail=new PaymentDetail();
		PaymentType pt=new PaymentType();
		pt.setPtId(pd.getPtId());
		pdetail.setPaymentType(pt);
		pdetail.setPdAmount(pd.getAmount());
		pdetail.setTransaction(newTransaction);
		return pdetail;
	}

	public Transaction buildTransaction(RequestIncome requestIncome,String type) throws Exception {
		Transaction transaction=new Transaction();
		try {
			PaymentType pt=new PaymentType();
			pt.setPtId(requestIncome.getFormOfPayment().getId());
			transaction.setPaymentType(pt);
			List<TransactionCategory> tcList=transactionCategoryRepository.findByTcName(type);
			transaction.setTransactionCategory(tcList.get(0));
			transaction.setTxDate(new Timestamp(requestIncome.getTxDate().getTime()));
			if(type.equals(SERVICES)) {
				transaction.setTxTransSubtotal(requestIncome.getServices().stream().mapToDouble(o -> o.getCost()).sum());
			}else {
				transaction.setTxTransSubtotal(requestIncome.getProducts().stream().mapToDouble(o -> o.getSellingPrice()).sum());
			}
			
			transaction.setTxTransDiscount(getTotal("Descuentos",requestIncome.getTotals()));
			transaction.setTxDiscountPercentage(requestIncome.getDiscount());
			transaction.setTxTransClientTotal(getTotal("Sub Total Cliente", requestIncome.getTotals()));
			transaction.setTxTransFee(getTotal("Comisiones",requestIncome.getTotals()));
			transaction.setTxTransTotal(getTotal("Total",requestIncome.getTotals()));
			if(requestIncome.getId()!=null) {
				transaction.setTxId(requestIncome.getId());
			}
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
	
	private TransactionDetailSale builTransactionDetailSale(Transaction trx, ProductPojo prod) {
		TransactionDetailSale tdetailSale=null;
		Product prd=null;
		try {
			tdetailSale=new TransactionDetailSale();
			tdetailSale.setTransaction(trx);
			prd=new Product();
			prd.setPrdId(prod.getId());
			tdetailSale.setProduct(prd);
			return tdetailSale;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public ResponseEntity<List<ResponseIncome>> getDailyIncomes(){
		try {
			ResponseIncome responseIncome=new ResponseIncome();
			List<ResponseIncome> incomes=new ArrayList<>();
			ResponseIncome income=null;
			List<Transaction> transactions=(List<Transaction>)summaryRepository.getDailyTransactions();
			if(transactions!=null && transactions.size()>0) {
				responseIncome.setCode("200");
				responseIncome.setMessage("SUCCESSFULL");
				for (Transaction transaction : transactions) {
					income=new ResponseIncome();
					income.setCode("200");
					income.setMessage("SUCCESSFULL");
					income.setTxId(transaction.getTxId());
					income.setTxSubTotal(transaction.getTxTransSubtotal());
					income.setTxTotal(transaction.getTxTransTotal());
					
					income.setPaymentType(transaction.getPaymentType().getPtName());
					income.setSubTotalClient(transaction.getTxTransClientTotal());
					PatientInfo patient=new PatientInfo();
					patient.setAddress(transaction.getPatient().getPatientContact().getPcAddress());
					patient.setBirthday(transaction.getPatient().getPtBirthday()!=null?transaction.getPatient().getPtBirthday().toString():null);
					patient.setIdentification(transaction.getPatient().getPtIdentification());
					patient.setLastName(transaction.getPatient().getPtLastName());
					patient.setName(transaction.getPatient().getPtName());
					patient.setPhone(transaction.getPatient().getPatientContact().getPcPhoneNumber());;
					income.setPatient(patient);
					income.setDiscount(transaction.getTxTransDiscount());
					income.setComission(transaction.getTxTransFee());
					//income.setPhone(transaction.getTxTelefono());
					//income.setLastName(transaction.getTxApellidos());
					//income.setName(transaction.getTxNombres());
					List<PaymentDetail> payments=transaction.getPaymentDetails();
					List<PaymentDetails> pdetails=new ArrayList<>();
					PaymentDetails pdetail=null;
					for (PaymentDetail pd : payments) {
						pdetail=new PaymentDetails();
						pdetail.setAmount(pd.getPdAmount());
						pdetail.setPdId(pd.getPdId());
						pdetail.setPtId(pd.getPaymentType().getPtId());
						pdetail.setTxId(pd.getTransaction().getTxId());
						pdetail.setDescription(pd.getPaymentType().getPtName());
						pdetails.add(pdetail);
					}
					income.setPaymentDetails(pdetails);
					incomes.add(income);
				}
				
			}else {
				income=new ResponseIncome();
				income.setCode("400");
				income.setMessage("NO DATA");
			}
			return new ResponseEntity<>(incomes,HttpStatus.OK);
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public ResponseEntity<List<ResponseIncome>> getDailyIncomesDateRange(Date start, Date end){
		try {
			ResponseIncome responseIncome=new ResponseIncome();
			List<ResponseIncome> incomes=new ArrayList<>();
			ResponseIncome income=null;
			List<Transaction> transactions=null;
			transactions=(List<Transaction>)summaryRepository.getDailyTransactionsDateRange(start,end);
			if(transactions!=null && transactions.size()>0) {
				responseIncome.setCode("200");
				responseIncome.setMessage("SUCCESSFULL");
				for (Transaction transaction : transactions) {
					income=new ResponseIncome();
					income.setCode("200");
					income.setMessage("SUCCESSFULL");
					income.setTxId(transaction.getTxId());
					income.setTxTotal(transaction.getTxTransTotal());
					income.setTxSubTotal(transaction.getTxTransSubtotal());
					income.setPaymentType(transaction.getPaymentType().getPtName());
					income.setSubTotalClient(transaction.getTxTransClientTotal());
					income.setDate(transaction.getTxDate());
					PatientInfo patient=new PatientInfo();
					patient.setAddress(transaction.getPatient().getPatientContact().getPcAddress());
					patient.setBirthday(transaction.getPatient().getPtBirthday()!=null?transaction.getPatient().getPtBirthday().toString():null);
					patient.setIdentification(transaction.getPatient().getPtIdentification());
					patient.setLastName(transaction.getPatient().getPtLastName());
					patient.setName(transaction.getPatient().getPtName());
					patient.setPhone(transaction.getPatient().getPatientContact().getPcPhoneNumber());
					income.setPatient(patient);
					income.setDiscount(transaction.getTxTransDiscount());
					income.setComission(transaction.getTxTransFee());
					//income.setPhone(transaction.getTxTelefono());
					//income.setLastName(transaction.getTxApellidos());
					//income.setName(transaction.getTxNombres());
					List<PaymentDetail> payments=transaction.getPaymentDetails();
					List<PaymentDetails> pdetails=new ArrayList<>();
					PaymentDetails pdetail=null;
					for (PaymentDetail pd : payments) {
						pdetail=new PaymentDetails();
						pdetail.setAmount(pd.getPdAmount());
						pdetail.setPdId(pd.getPdId());
						pdetail.setPtId(pd.getPaymentType().getPtId());
						pdetail.setTxId(pd.getTransaction().getTxId());
						pdetail.setDescription(pd.getPaymentType().getPtName());
						pdetails.add(pdetail);
					}
					income.setPaymentDetails(pdetails);
					incomes.add(income);
				}
				
			}else {
				income=new ResponseIncome();
				income.setCode("400");
				income.setMessage("NO DATA");
			}
			return new ResponseEntity<>(incomes,HttpStatus.OK);
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public ResponseEntity<ResponseIncome> getTxById(Integer id){
		try {
			ResponseIncome income=null;
			List<IncomeDetails> incomeDetails =new ArrayList<>();
			List<MedicalServiceResponse> services=new ArrayList<>();
			MedicalServiceResponse service=new MedicalServiceResponse();
			Optional<Transaction> transaction=tranRepository.findById(id);
			income=new ResponseIncome();
			if(transaction.isPresent()) {
				income.setCode("200");
				income.setMessage("SUCCESS");
				income.setPaymentType(transaction.get().getPaymentType().getPtName());
				income.setPaymentId(transaction.get().getPaymentType().getPtId());
				//income.setPhone(transaction.get().getTxTelefono());
				//income.setLastName(transaction.get().getTxApellidos());
				//income.setName(transaction.get().getTxNombres());
				PatientInfo patient=new PatientInfo();
				patient.setAddress(transaction.get().getPatient().getPatientContact().getPcAddress());
				patient.setBirthday(transaction.get().getPatient().getPtBirthday()!=null?transaction.get().getPatient().getPtBirthday().toString():null);
				patient.setIdentification(transaction.get().getPatient().getPtIdentification());
				patient.setLastName(transaction.get().getPatient().getPtLastName());
				patient.setName(transaction.get().getPatient().getPtName());
				patient.setPhone(transaction.get().getPatient().getPatientContact().getPcPhoneNumber());
				income.setPatient(patient);
				income.setSubTotalClient(transaction.get().getTxTransClientTotal());
				income.setTxId(transaction.get().getTxId());
				income.setTxTotal(transaction.get().getTxTransTotal());
				income.setTxSubTotal(transaction.get().getTxTransSubtotal());
				income.setDiscount(transaction.get().getTxDiscountPercentage());
				income.setDiscountTotal(transaction.get().getTxTransDiscount());
				for (TransactionDetail transactionDetail : transaction.get().getTransactionDetails()) {
					service=new MedicalServiceResponse();
					service.setCategory(transactionDetail.getService().getServicesCategory().getScId());
					service.setCategoryName(transactionDetail.getService().getServicesCategory().getScName());
					service.setCost(transactionDetail.getService().getSvCost());
					service.setId(transactionDetail.getService().getSvId());
					service.setDescription(transactionDetail.getService().getSvName());
					service.setTrxdId(transactionDetail.getTxdId());
					services.add(service);
				}
				income.setServices(services);
				
				List<PaymentDetail> payments=transaction.get().getPaymentDetails();
				List<PaymentDetails> pdetails=new ArrayList();
				PaymentDetails pdetail=null;
				for (PaymentDetail pd : payments) {
					pdetail=new PaymentDetails();
					pdetail.setAmount(pd.getPdAmount());
					pdetail.setPdId(pd.getPdId());
					pdetail.setPtId(pd.getPaymentType().getPtId());
					pdetail.setTxId(pd.getTransaction().getTxId());
					pdetail.setDescription(pd.getPaymentType().getPtName());
					pdetails.add(pdetail);
				}
				income.setPaymentDetails(pdetails);
				
				return new ResponseEntity<>(income,HttpStatus.OK);
			}else {
				income.setCode("404");
				income.setMessage("NO DATA");
				return new ResponseEntity<>(income,HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			throw e;
		}
	}
}
