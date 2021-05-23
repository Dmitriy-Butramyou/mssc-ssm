package home.production.msscssm.config.actions;

import home.production.msscssm.domain.PaymentEvent;
import home.production.msscssm.domain.PaymentState;
import home.production.msscssm.service.PaymentServiceImpl;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PreAuthAction implements Action<PaymentState, PaymentEvent> {

  @Override
  public void execute(StateContext<PaymentState, PaymentEvent> stateContext) {
    System.out.println("PreAuth was called");

    if (new Random().nextInt(10) < 8) {
      System.out.println("Per Auth Approved");
      stateContext.getStateMachine().sendEvent(MessageBuilder.withPayload(PaymentEvent.PRE_AUTH_APPROVED)
          .setHeader(PaymentServiceImpl.PAYMENT_ID_HEADER, stateContext.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER))
          .build());
    } else {
      System.out.println("Per Auth Declined no credit");
      stateContext.getStateMachine().sendEvent(MessageBuilder.withPayload(PaymentEvent.PRE_AUTH_DECLINED)
          .setHeader(PaymentServiceImpl.PAYMENT_ID_HEADER, stateContext.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER))
          .build());
    }
  }
}
