package com.bgbus.tracker.userservice.service.cmds;

import org.chenile.stm.STMInternalTransitionInvoker;
import org.chenile.stm.State;
import org.chenile.stm.action.STMTransitionAction;
import org.chenile.stm.model.Transition;

import com.bgbus.tracker.userservice.model.AssignUserservicePayload;
import com.bgbus.tracker.userservice.model.Userservice;

public class AssignUserserviceAction implements STMTransitionAction<Userservice>{

	@Override
	public void doTransition(Userservice userservice, Object transitionParam, State startState, String eventId,
			State endState, STMInternalTransitionInvoker<?> stm, Transition transition) throws Exception {
		AssignUserservicePayload payload = (AssignUserservicePayload) transitionParam;
		userservice.assignee = payload.assignee;
		userservice.assignComment = payload.getComment();
	}

}
