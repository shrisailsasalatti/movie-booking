package com.merin.moviebooking.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.merin.moviebooking.entity.Customer;
import com.merin.moviebooking.entity.Payment;
import com.merin.moviebooking.entity.PaymentMasterData;
import com.merin.moviebooking.exception.AccessForbiddenException;
import com.merin.moviebooking.exception.PaymentNotFoundException;
import com.merin.moviebooking.repository.ICustomerRepository;
import com.merin.moviebooking.service.IPaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/payment")
@Tag(name = "Payment Controller",description = "Payment Management Portal")
public class PaymentController            //[viewPaymentId]  
{
	
	@Autowired
	private IPaymentService paymentService;
	
	@Autowired
	private ICustomerRepository iCustomerRepository;
	
	
	@PostMapping("/addMasterData")
	public HttpStatus addMasterData(@RequestBody PaymentMasterData masterData) throws PaymentNotFoundException
	{
		
		Customer customer=iCustomerRepository.findById(masterData.getCustomerId()).get();
		masterData.setCustomer(customer);
		paymentService.addMasterData(masterData);
		return HttpStatus.CREATED;
		
	}
	
	
	@PostMapping("/paymentByCard")
	public HttpStatus addPaymentByCard(@RequestBody Payment payment) throws PaymentNotFoundException
	{
		
		paymentService.addPaymentByCard(payment);
		return HttpStatus.ACCEPTED;
		
	}
	
	@PostMapping("/paymentByUpi")
	public HttpStatus addPaymentByUpi(@RequestBody Payment payment) throws PaymentNotFoundException
	{
		
		paymentService.addPaymentByUpi(payment);
		return HttpStatus.ACCEPTED;
		
	}
	
	
	@PostMapping("/paymentByNetBanking")
	public HttpStatus addPaymentByNetBanking(@RequestBody Payment payment) throws AccessForbiddenException, PaymentNotFoundException
	{
		
		paymentService.addPaymentByNetBanking(payment);
		return HttpStatus.ACCEPTED;
		
	}
	
	@GetMapping("viewPaymentById/{id}")
	public Payment getPaymentDetailsById(@PathVariable("id") int id)
	{
		return paymentService.getPaymentDetailsById(id);
		
	}
	
	@GetMapping("/viewPaymentRevenueByDate/{date}")
	public double getPaymentRevenueByDate(@PathVariable("date") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date)
	{
		double revenue=paymentService.getPaymentRevenueByDate(date);
		return revenue;
		
		
	}
	
	
	@GetMapping("/viewGrossRevenue")
	public double getGrossPaymentRevenue()
	{
		double revenue=paymentService.getGrossPaymentRevenue();
		return revenue;
	}
	
	
	@DeleteMapping("/deletePaymentById/{id}")
	public HttpStatus removePaymentById(@PathVariable("id") int id) throws AccessForbiddenException, PaymentNotFoundException
	{
		
		paymentService.deletePaymentById(id);
		return HttpStatus.CREATED;
		
	}
	
	
}
