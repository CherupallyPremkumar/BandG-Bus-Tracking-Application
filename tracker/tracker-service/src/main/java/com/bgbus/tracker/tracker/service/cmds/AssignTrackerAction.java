package com.bgbus.tracker.tracker.service.cmds;

import org.chenile.stm.STMInternalTransitionInvoker;
import org.chenile.stm.State;
import org.chenile.stm.action.STMTransitionAction;
import org.chenile.stm.model.Transition;

import com.bgbus.tracker.tracker.model.AssignTrackerPayload;
import com.bgbus.tracker.tracker.model.Tracker;

public class AssignTrackerAction implements STMTransitionAction<Tracker>{

	@Override
	public void doTransition(Tracker tracker, Object transitionParam, State startState, String eventId,
			State endState, STMInternalTransitionInvoker<?> stm, Transition transition) throws Exception {
		AssignTrackerPayload payload = (AssignTrackerPayload) transitionParam;
		tracker.assignee = payload.assignee;
		tracker.assignComment = payload.getComment();
	}

}
