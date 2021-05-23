package home.production.msscssm.service;

import home.production.msscssm.domain.Payment;
import home.production.msscssm.domain.PaymentEvent;
import home.production.msscssm.domain.PaymentState;
import home.production.msscssm.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@SpringBootTest
class PaymentServiceImplTest {

  @Autowired
  PaymentService paymentService;

  @Autowired
  PaymentRepository paymentRepository;

  Payment payment;

  @BeforeEach
  void setUp() {
    payment = Payment.builder().amount(new BigDecimal("15.99")).build();
  }

  @Transactional
  @Test
  void preAuth() {
    Payment savedPayment = paymentService.newPayment(payment);

    System.out.println("Should be NEW");
    System.out.println(savedPayment.getState());

    StateMachine<PaymentState, PaymentEvent> sm = paymentService.preAuth(savedPayment.getId());

    Payment preAuthedPayment = paymentRepository.getById(savedPayment.getId());

    System.out.println("Should be PRE_AUTH");
    System.out.println(sm.getState().getId());
    System.out.println(preAuthedPayment);
  }
}