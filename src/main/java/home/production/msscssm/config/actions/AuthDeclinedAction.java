package home.production.msscssm.config.actions;

import home.production.msscssm.domain.PaymentEvent;
import home.production.msscssm.domain.PaymentState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Component
public class AuthDeclinedAction implements Action<PaymentState, PaymentEvent> {

  @Override
  public void execute(StateContext<PaymentState, PaymentEvent> stateContext) {
    System.out.println("Sending notification of AuthDeclined Approved");
  }
}