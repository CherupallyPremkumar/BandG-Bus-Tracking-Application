package com.bgbus.tracker.busstop.service.cmds;

import org.chenile.stm.STMInternalTransitionInvoker;
import org.chenile.stm.State;
import org.chenile.stm.action.STMTransitionAction;
import org.chenile.stm.model.Transition;

import org.chenile.workflow.param.MinimalPayload;
import com.bgbus.tracker.busstop.model.Busstop;

public class ResolveBusstopAction implements STMTransitionAction<Busstop>{

	@Override
	public void doTransition(Busstop busstop, Object transitionParam, State startState, String eventId,
			State endState, STMInternalTransitionInvoker<?> stm, Transition transition) throws Exception {
		MinimalPayload payload = (MinimalPayload) transitionParam;
		busstop.resolveComment = payload.getComment();
	}

}
