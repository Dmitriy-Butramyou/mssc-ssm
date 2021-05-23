package home.production.msscssm.config.guards;

import home.production.msscssm.domain.PaymentEvent;
import home.production.msscssm.domain.PaymentState;
import home.production.msscssm.service.PaymentServiceImpl;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

@Component
public class PaymentIdGuard implements Guard<PaymentState, PaymentEvent> {

  @Override
  public boolean evaluate(StateContext<PaymentState, PaymentEvent> stateContext) {
    return stateContext.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER) != null;
  }
}
