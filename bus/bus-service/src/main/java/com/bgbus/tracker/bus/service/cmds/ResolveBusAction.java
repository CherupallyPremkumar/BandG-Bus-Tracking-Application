package com.bgbus.tracker.bus.service.cmds;

import org.chenile.stm.STMInternalTransitionInvoker;
import org.chenile.stm.State;
import org.chenile.stm.action.STMTransitionAction;
import org.chenile.stm.model.Transition;

import org.chenile.workflow.param.MinimalPayload;
import com.bgbus.tracker.bus.model.Bus;

public class ResolveBusAction implements STMTransitionAction<Bus>{

	@Override
	public void doTransition(Bus bus, Object transitionParam, State startState, String eventId,
			State endState, STMInternalTransitionInvoker<?> stm, Transition transition) throws Exception {
		MinimalPayload payload = (MinimalPayload) transitionParam;
		bus.resolveComment = payload.getComment();
	}

}
