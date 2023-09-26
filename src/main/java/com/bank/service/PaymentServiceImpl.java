package com.bank.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.model.TransferPaymentEntity;
import com.bank.repository.ITransferPaymentRepository;

import lombok.RequiredArgsConstructor;

/** RequiredArgsConstructor vuelve las variables final propias del constructor; hace Autowired */
@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements IPaymentService {

	private final ITransferPaymentRepository repository;

	@Override
	public void altaEntidad(TransferPaymentEntity entity) {
		repository.save(entity);
	}
}