package com.bgbus.tracker.student.configuration;

import org.chenile.stm.STM;
import org.chenile.stm.action.STMTransitionAction;
import org.chenile.stm.impl.BeanFactoryAdapter;
import org.chenile.stm.impl.STMActionsInfoProvider;
import org.chenile.stm.impl.STMFlowStoreImpl;
import org.chenile.stm.impl.STMImpl;
import org.chenile.stm.impl.XmlFlowReader;
import org.chenile.stm.spring.SpringBeanFactoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import org.chenile.utils.entity.service.EntityStore;
import org.chenile.workflow.service.impl.StateEntityServiceImpl;
import org.chenile.workflow.service.stmcmds.BaseTransitionAction;
import org.chenile.workflow.service.stmcmds.GenericEntryAction;
import org.chenile.workflow.service.stmcmds.GenericExitAction;
import org.chenile.workflow.service.stmcmds.StmBodyTypeSelector;
import com.bgbus.tracker.student.model.Student;
import com.bgbus.tracker.student.service.cmds.AssignStudentAction;
import com.bgbus.tracker.student.service.cmds.CloseStudentAction;
import com.bgbus.tracker.student.service.cmds.ResolveStudentAction;
import com.bgbus.tracker.student.service.healthcheck.StudentHealthChecker;
import com.bgbus.tracker.student.service.store.StudentEntityStore;
import org.chenile.workflow.api.WorkflowRegistry;
import org.chenile.workflow.service.stmcmds.StmAuthoritiesBuilder;
import java.util.function.Function;
import org.chenile.core.context.ChenileExchange;

/**
 This is where you will instantiate all the required classes in Spring

*/
@Configuration
public class StudentConfiguration {
	private static final String FLOW_DEFINITION_FILE = "com/bgbus/tracker/student/states.xml";
	
	@Bean BeanFactoryAdapter studentBeanFactoryAdapter() {
		return new SpringBeanFactoryAdapter();
	}
	
	@Bean STMFlowStoreImpl studentFlowStore(@Qualifier("studentBeanFactoryAdapter") BeanFactoryAdapter studentBeanFactoryAdapter) throws Exception{
		STMFlowStoreImpl stmFlowStore = new STMFlowStoreImpl();
		stmFlowStore.setBeanFactory(studentBeanFactoryAdapter);
		return stmFlowStore;
	}
	
	@Bean @Autowired STM<Student> studentEntityStm(@Qualifier("studentFlowStore") STMFlowStoreImpl stmFlowStore) throws Exception{
		STMImpl<Student> stm = new STMImpl<>();		
		stm.setStmFlowStore(stmFlowStore);
		return stm;
	}
	
	@Bean @Autowired STMActionsInfoProvider studentActionsInfoProvider(@Qualifier("studentFlowStore") STMFlowStoreImpl stmFlowStore) {
		STMActionsInfoProvider provider =  new STMActionsInfoProvider(stmFlowStore);
        WorkflowRegistry.addSTMActionsInfoProvider("student",provider);
        return provider;
	}
	
	@Bean EntityStore<Student> studentEntityStore() {
		return new StudentEntityStore();
	}
	
	@Bean @Autowired StateEntityServiceImpl<Student> _studentStateEntityService_(
			@Qualifier("studentEntityStm") STM<Student> stm,
			@Qualifier("studentActionsInfoProvider") STMActionsInfoProvider studentInfoProvider,
			@Qualifier("studentEntityStore") EntityStore<Student> entityStore){
		return new StateEntityServiceImpl<>(stm, studentInfoProvider, entityStore);
	}
	
	// Now we start constructing the STM Components 
	
	@Bean @Autowired GenericEntryAction<Student> studentEntryAction(@Qualifier("studentEntityStore") EntityStore<Student> entityStore,
			@Qualifier("studentActionsInfoProvider") STMActionsInfoProvider studentInfoProvider){
		return new GenericEntryAction<Student>(entityStore,studentInfoProvider);
	}
	
	@Bean GenericExitAction<Student> studentExitAction(){
		return new GenericExitAction<Student>();
	}
	
	@Bean @Autowired StmBodyTypeSelector studentBodyTypeSelector(@Qualifier("studentActionsInfoProvider") STMActionsInfoProvider studentInfoProvider) {
		return new StmBodyTypeSelector(studentInfoProvider);
	}
	
	@Bean @Autowired STMTransitionAction<Student> studentBaseTransitionAction(){
		return new BaseTransitionAction<>();
	}
	
	@Bean AssignStudentAction assignStudent() {
		return new AssignStudentAction();
	}
	
	@Bean ResolveStudentAction resolveStudent() {
		return new ResolveStudentAction();
	}
	
	@Bean CloseStudentAction closeStudent() {
		return new CloseStudentAction();
	}

	@Bean
	XmlFlowReader studentFlowReader(@Qualifier("studentFlowStore") STMFlowStoreImpl flowStore) throws Exception {
		XmlFlowReader flowReader = new XmlFlowReader(flowStore);
		flowReader.setFilename(FLOW_DEFINITION_FILE);
		return flowReader;
	}
	

	@Bean StudentHealthChecker studentHealthChecker(){
    	return new StudentHealthChecker();
    }

    @Bean @Autowired Function<ChenileExchange, String[]> studentEventAuthoritiesSupplier(
        @Qualifier("studentActionsInfoProvider") STMActionsInfoProvider studentInfoProvider)
                    throws Exception{
        StmAuthoritiesBuilder builder = new StmAuthoritiesBuilder(studentInfoProvider);
        return builder.build();
    }
}
