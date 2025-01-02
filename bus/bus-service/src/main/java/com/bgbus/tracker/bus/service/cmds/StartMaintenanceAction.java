package com.bgbus.tracker.bus.service.cmds;

import com.bgbus.tracker.bus.model.Bus;
import org.chenile.stm.STMInternalTransitionInvoker;
import org.chenile.stm.State;
import org.chenile.stm.action.STMTransitionAction;
import org.chenile.stm.model.Transition;

public class StartMaintenanceAction implements STMTransitionAction<Bus>
{
    @Override
    public void doTransition(Bus stateEntity, Object transitionParam, State startState, String eventId, State endState, STMInternalTransitionInvoker<?> stm, Transition transition) throws Exception {

    }
}
