package com.rom.hrpayroll.entities.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rom.hrpayroll.entities.Payment;
import com.rom.hrpayroll.entities.services.PaymentService;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource {

	@Autowired
	private PaymentService paymentService;
	
	@HystrixCommand(fallbackMethod = "getPaymentAltenative")
	@GetMapping(value = "/{workerId}/days/{days}")
	public ResponseEntity<Payment> findPayment(@PathVariable Long workerId, @PathVariable Integer days){
		Payment payment = paymentService.getPayment(workerId, days);
		return ResponseEntity.ok(payment);
	}
	
	public ResponseEntity<Payment> getPaymentAltenative( Long workerId, Integer days){
		Payment payment = new Payment("Teste", 400.0, days);
		return ResponseEntity.ok(payment);
	}
	
	
}
