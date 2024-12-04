package com.bgbus.tracker.student.service.cmds;

import org.chenile.stm.STMInternalTransitionInvoker;
import org.chenile.stm.State;
import org.chenile.stm.action.STMTransitionAction;
import org.chenile.stm.model.Transition;

import org.chenile.workflow.param.MinimalPayload;
import com.bgbus.tracker.student.model.Student;

public class ResolveStudentAction implements STMTransitionAction<Student>{

	@Override
	public void doTransition(Student student, Object transitionParam, State startState, String eventId,
			State endState, STMInternalTransitionInvoker<?> stm, Transition transition) throws Exception {
		MinimalPayload payload = (MinimalPayload) transitionParam;
		student.resolveComment = payload.getComment();
	}

}
