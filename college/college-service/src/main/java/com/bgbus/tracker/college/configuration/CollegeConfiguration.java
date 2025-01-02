package com.bgbus.tracker.college.configuration;

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
import com.bgbus.tracker.college.model.College;
import com.bgbus.tracker.college.service.cmds.AssignCollegeAction;
import com.bgbus.tracker.college.service.cmds.CloseCollegeAction;
import com.bgbus.tracker.college.service.cmds.ResolveCollegeAction;
import com.bgbus.tracker.college.service.healthcheck.CollegeHealthChecker;
import com.bgbus.tracker.college.service.store.CollegeEntityStore;
import org.chenile.workflow.api.WorkflowRegistry;
import org.chenile.workflow.service.stmcmds.StmAuthoritiesBuilder;
import java.util.function.Function;
import org.chenile.core.context.ChenileExchange;

/**
 This is where you will instantiate all the required classes in Spring

*/
@Configuration
public class CollegeConfiguration {
	private static final String FLOW_DEFINITION_FILE = "com/bgbus/tracker/college/states.xml";
	
	@Bean BeanFactoryAdapter collegeBeanFactoryAdapter() {
		return new SpringBeanFactoryAdapter();
	}
	
	@Bean STMFlowStoreImpl collegeFlowStore(@Qualifier("collegeBeanFactoryAdapter") BeanFactoryAdapter collegeBeanFactoryAdapter) throws Exception{
		STMFlowStoreImpl stmFlowStore = new STMFlowStoreImpl();
		stmFlowStore.setBeanFactory(collegeBeanFactoryAdapter);
		return stmFlowStore;
	}
	
	@Bean @Autowired STM<College> collegeEntityStm(@Qualifier("collegeFlowStore") STMFlowStoreImpl stmFlowStore) throws Exception{
		STMImpl<College> stm = new STMImpl<>();		
		stm.setStmFlowStore(stmFlowStore);
		return stm;
	}
	
	@Bean @Autowired STMActionsInfoProvider collegeActionsInfoProvider(@Qualifier("collegeFlowStore") STMFlowStoreImpl stmFlowStore) {
		STMActionsInfoProvider provider =  new STMActionsInfoProvider(stmFlowStore);
        WorkflowRegistry.addSTMActionsInfoProvider("college",provider);
        return provider;
	}
	
	@Bean EntityStore<College> collegeEntityStore() {
		return new CollegeEntityStore();
	}
	
	@Bean @Autowired StateEntityServiceImpl<College> _collegeStateEntityService_(
			@Qualifier("collegeEntityStm") STM<College> stm,
			@Qualifier("collegeActionsInfoProvider") STMActionsInfoProvider collegeInfoProvider,
			@Qualifier("collegeEntityStore") EntityStore<College> entityStore){
		return new StateEntityServiceImpl<>(stm, collegeInfoProvider, entityStore);
	}
	
	// Now we start constructing the STM Components 
	
	@Bean @Autowired GenericEntryAction<College> collegeEntryAction(@Qualifier("collegeEntityStore") EntityStore<College> entityStore,
			@Qualifier("collegeActionsInfoProvider") STMActionsInfoProvider collegeInfoProvider){
		return new GenericEntryAction<College>(entityStore,collegeInfoProvider);
	}
	
	@Bean GenericExitAction<College> collegeExitAction(){
		return new GenericExitAction<College>();
	}
	
	@Bean @Autowired StmBodyTypeSelector collegeBodyTypeSelector(@Qualifier("collegeActionsInfoProvider") STMActionsInfoProvider collegeInfoProvider) {
		return new StmBodyTypeSelector(collegeInfoProvider);
	}
	
	@Bean @Autowired STMTransitionAction<College> collegeBaseTransitionAction(){
		return new BaseTransitionAction<>();
	}
	
	@Bean AssignCollegeAction assignCollege() {
		return new AssignCollegeAction();
	}
	
	@Bean ResolveCollegeAction resolveCollege() {
		return new ResolveCollegeAction();
	}
	
	@Bean CloseCollegeAction closeCollege() {
		return new CloseCollegeAction();
	}

	@Bean
	XmlFlowReader collegeFlowReader(@Qualifier("collegeFlowStore") STMFlowStoreImpl flowStore) throws Exception {
		XmlFlowReader flowReader = new XmlFlowReader(flowStore);
		flowReader.setFilename(FLOW_DEFINITION_FILE);
		return flowReader;
	}
	

	@Bean CollegeHealthChecker collegeHealthChecker(){
    	return new CollegeHealthChecker();
    }

    @Bean @Autowired Function<ChenileExchange, String[]> collegeEventAuthoritiesSupplier(
        @Qualifier("collegeActionsInfoProvider") STMActionsInfoProvider collegeInfoProvider)
                    throws Exception{
        StmAuthoritiesBuilder builder = new StmAuthoritiesBuilder(collegeInfoProvider);
        return builder.build();
    }
}
