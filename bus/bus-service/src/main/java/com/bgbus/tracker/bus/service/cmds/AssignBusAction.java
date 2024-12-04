package com.bgbus.tracker.bus.service.cmds;

import org.chenile.stm.STMInternalTransitionInvoker;
import org.chenile.stm.State;
import org.chenile.stm.action.STMTransitionAction;
import org.chenile.stm.model.Transition;

import com.bgbus.tracker.bus.model.AssignBusPayload;
import com.bgbus.tracker.bus.model.Bus;

public class AssignBusAction implements STMTransitionAction<Bus>{

	@Override
	public void doTransition(Bus bus, Object transitionParam, State startState, String eventId,
			State endState, STMInternalTransitionInvoker<?> stm, Transition transition) throws Exception {
		AssignBusPayload payload = (AssignBusPayload) transitionParam;
		bus.assignee = payload.assignee;
		bus.assignComment = payload.getComment();
	}

}
