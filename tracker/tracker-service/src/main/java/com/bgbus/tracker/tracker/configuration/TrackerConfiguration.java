package com.bgbus.tracker.tracker.configuration;

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
import com.bgbus.tracker.tracker.model.Tracker;
import com.bgbus.tracker.tracker.service.cmds.AssignTrackerAction;
import com.bgbus.tracker.tracker.service.cmds.CloseTrackerAction;
import com.bgbus.tracker.tracker.service.cmds.ResolveTrackerAction;
import com.bgbus.tracker.tracker.service.healthcheck.TrackerHealthChecker;
import com.bgbus.tracker.tracker.service.store.TrackerEntityStore;
import org.chenile.workflow.api.WorkflowRegistry;
import org.chenile.workflow.service.stmcmds.StmAuthoritiesBuilder;
import java.util.function.Function;
import org.chenile.core.context.ChenileExchange;

/**
 This is where you will instantiate all the required classes in Spring

*/
@Configuration
public class TrackerConfiguration {
	private static final String FLOW_DEFINITION_FILE = "com/bgbus/tracker/tracker/states.xml";
	
	@Bean BeanFactoryAdapter trackerBeanFactoryAdapter() {
		return new SpringBeanFactoryAdapter();
	}
	
	@Bean STMFlowStoreImpl trackerFlowStore(@Qualifier("trackerBeanFactoryAdapter") BeanFactoryAdapter trackerBeanFactoryAdapter) throws Exception{
		STMFlowStoreImpl stmFlowStore = new STMFlowStoreImpl();
		stmFlowStore.setBeanFactory(trackerBeanFactoryAdapter);
		return stmFlowStore;
	}
	
	@Bean @Autowired STM<Tracker> trackerEntityStm(@Qualifier("trackerFlowStore") STMFlowStoreImpl stmFlowStore) throws Exception{
		STMImpl<Tracker> stm = new STMImpl<>();		
		stm.setStmFlowStore(stmFlowStore);
		return stm;
	}
	
	@Bean @Autowired STMActionsInfoProvider trackerActionsInfoProvider(@Qualifier("trackerFlowStore") STMFlowStoreImpl stmFlowStore) {
		STMActionsInfoProvider provider =  new STMActionsInfoProvider(stmFlowStore);
        WorkflowRegistry.addSTMActionsInfoProvider("tracker",provider);
        return provider;
	}
	
	@Bean EntityStore<Tracker> trackerEntityStore() {
		return new TrackerEntityStore();
	}
	
	@Bean @Autowired StateEntityServiceImpl<Tracker> _trackerStateEntityService_(
			@Qualifier("trackerEntityStm") STM<Tracker> stm,
			@Qualifier("trackerActionsInfoProvider") STMActionsInfoProvider trackerInfoProvider,
			@Qualifier("trackerEntityStore") EntityStore<Tracker> entityStore){
		return new StateEntityServiceImpl<>(stm, trackerInfoProvider, entityStore);
	}
	
	// Now we start constructing the STM Components 
	
	@Bean @Autowired GenericEntryAction<Tracker> trackerEntryAction(@Qualifier("trackerEntityStore") EntityStore<Tracker> entityStore,
			@Qualifier("trackerActionsInfoProvider") STMActionsInfoProvider trackerInfoProvider){
		return new GenericEntryAction<Tracker>(entityStore,trackerInfoProvider);
	}
	
	@Bean GenericExitAction<Tracker> trackerExitAction(){
		return new GenericExitAction<Tracker>();
	}
	
	@Bean @Autowired StmBodyTypeSelector trackerBodyTypeSelector(@Qualifier("trackerActionsInfoProvider") STMActionsInfoProvider trackerInfoProvider) {
		return new StmBodyTypeSelector(trackerInfoProvider);
	}
	
	@Bean @Autowired STMTransitionAction<Tracker> trackerBaseTransitionAction(){
		return new BaseTransitionAction<>();
	}
	
	@Bean AssignTrackerAction assignTracker() {
		return new AssignTrackerAction();
	}
	
	@Bean ResolveTrackerAction resolveTracker() {
		return new ResolveTrackerAction();
	}
	
	@Bean CloseTrackerAction closeTracker() {
		return new CloseTrackerAction();
	}

	@Bean
	XmlFlowReader trackerFlowReader(@Qualifier("trackerFlowStore") STMFlowStoreImpl flowStore) throws Exception {
		XmlFlowReader flowReader = new XmlFlowReader(flowStore);
		flowReader.setFilename(FLOW_DEFINITION_FILE);
		return flowReader;
	}
	

	@Bean TrackerHealthChecker trackerHealthChecker(){
    	return new TrackerHealthChecker();
    }

    @Bean @Autowired Function<ChenileExchange, String[]> trackerEventAuthoritiesSupplier(
        @Qualifier("trackerActionsInfoProvider") STMActionsInfoProvider trackerInfoProvider)
                    throws Exception{
        StmAuthoritiesBuilder builder = new StmAuthoritiesBuilder(trackerInfoProvider);
        return builder.build();
    }
}
