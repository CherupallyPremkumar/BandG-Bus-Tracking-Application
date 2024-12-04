package com.bgbus.tracker.student.service.cmds;

import org.chenile.stm.STMInternalTransitionInvoker;
import org.chenile.stm.State;
import org.chenile.stm.action.STMTransitionAction;
import org.chenile.stm.model.Transition;

import com.bgbus.tracker.student.model.AssignStudentPayload;
import com.bgbus.tracker.student.model.Student;

public class AssignStudentAction implements STMTransitionAction<Student>{

	@Override
	public void doTransition(Student student, Object transitionParam, State startState, String eventId,
			State endState, STMInternalTransitionInvoker<?> stm, Transition transition) throws Exception {
		AssignStudentPayload payload = (AssignStudentPayload) transitionParam;
		student.assignee = payload.assignee;
		student.assignComment = payload.getComment();
	}

}
