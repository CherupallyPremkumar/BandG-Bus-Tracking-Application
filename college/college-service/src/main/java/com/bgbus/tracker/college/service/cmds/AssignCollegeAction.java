package com.bgbus.tracker.college.service.cmds;

import org.chenile.stm.STMInternalTransitionInvoker;
import org.chenile.stm.State;
import org.chenile.stm.action.STMTransitionAction;
import org.chenile.stm.model.Transition;

import com.bgbus.tracker.college.model.AssignCollegePayload;
import com.bgbus.tracker.college.model.College;

public class AssignCollegeAction implements STMTransitionAction<College>{

	@Override
	public void doTransition(College college, Object transitionParam, State startState, String eventId,
			State endState, STMInternalTransitionInvoker<?> stm, Transition transition) throws Exception {
		AssignCollegePayload payload = (AssignCollegePayload) transitionParam;
		college.assignee = payload.assignee;
		college.assignComment = payload.getComment();
	}

}
