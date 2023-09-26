package com.bank.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.ResultDto;
import com.bank.dto.TransferPaymentInDto;
import com.bank.model.TransferPaymentEntity;
import com.bank.service.IPaymentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/** RequiredArgsConstructor vuelve las variables final propias del constructor; hace Autowired */
@Tag(name = "Payments API", description = "servicios que realizan transferencias de pagos")
@RestController
@RequiredArgsConstructor
public class PaymentControllerImpl implements IPaymentController {

	private final IPaymentService service;

	@Override
	@Operation(summary = "Realiza la transferencia de pago", description = "Devuelve el resultado de la operación")
	public ResponseEntity<ResultDto> transferPayment(TransferPaymentInDto dto) {
		ResultDto result = new ResultDto("OK", "Operación correcta");
		String transactionId = UUID.randomUUID().toString();
		TransferPaymentEntity transferPaymentEntity = TransferPaymentEntity.builder()
				.id(transactionId)
				.avaiableBalance(dto.getAvaiableBalance())
				.amountPaid(dto.getAmountPaid())
				.isEnable(dto.getIsEnable())
				.isProcessed(false)
				.build();
		
		service.altaEntidad(transferPaymentEntity);
		Map<String, Object> info = new HashMap<>();
		info.put("transaction_id", transactionId);
		result.setDetails(info);
		
		return ResponseEntity.ok(result);
	}

}