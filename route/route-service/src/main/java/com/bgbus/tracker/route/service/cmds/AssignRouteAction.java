package com.bgbus.tracker.route.service.cmds;

import org.chenile.stm.STMInternalTransitionInvoker;
import org.chenile.stm.State;
import org.chenile.stm.action.STMTransitionAction;
import org.chenile.stm.model.Transition;

import com.bgbus.tracker.route.model.AssignRoutePayload;
import com.bgbus.tracker.route.model.Route;

public class AssignRouteAction implements STMTransitionAction<Route>{

	@Override
	public void doTransition(Route route, Object transitionParam, State startState, String eventId,
			State endState, STMInternalTransitionInvoker<?> stm, Transition transition) throws Exception {
		AssignRoutePayload payload = (AssignRoutePayload) transitionParam;
		route.assignee = payload.assignee;
		route.assignComment = payload.getComment();
	}

}
