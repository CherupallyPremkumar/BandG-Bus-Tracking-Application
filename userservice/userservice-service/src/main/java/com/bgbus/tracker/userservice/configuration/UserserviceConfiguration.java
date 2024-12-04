package com.bgbus.tracker.userservice.configuration;

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
import com.bgbus.tracker.userservice.model.Userservice;
import com.bgbus.tracker.userservice.service.cmds.AssignUserserviceAction;
import com.bgbus.tracker.userservice.service.cmds.CloseUserserviceAction;
import com.bgbus.tracker.userservice.service.cmds.ResolveUserserviceAction;
import com.bgbus.tracker.userservice.service.healthcheck.UserserviceHealthChecker;
import com.bgbus.tracker.userservice.service.store.UserserviceEntityStore;
import org.chenile.workflow.api.WorkflowRegistry;
import org.chenile.workflow.service.stmcmds.StmAuthoritiesBuilder;
import java.util.function.Function;
import org.chenile.core.context.ChenileExchange;

/**
 This is where you will instantiate all the required classes in Spring

*/
@Configuration
public class UserserviceConfiguration {
	private static final String FLOW_DEFINITION_FILE = "com/bgbus/tracker/userservice/states.xml";
	
	@Bean BeanFactoryAdapter userserviceBeanFactoryAdapter() {
		return new SpringBeanFactoryAdapter();
	}
	
	@Bean STMFlowStoreImpl userserviceFlowStore(@Qualifier("userserviceBeanFactoryAdapter") BeanFactoryAdapter userserviceBeanFactoryAdapter) throws Exception{
		STMFlowStoreImpl stmFlowStore = new STMFlowStoreImpl();
		stmFlowStore.setBeanFactory(userserviceBeanFactoryAdapter);
		return stmFlowStore;
	}
	
	@Bean @Autowired STM<Userservice> userserviceEntityStm(@Qualifier("userserviceFlowStore") STMFlowStoreImpl stmFlowStore) throws Exception{
		STMImpl<Userservice> stm = new STMImpl<>();		
		stm.setStmFlowStore(stmFlowStore);
		return stm;
	}
	
	@Bean @Autowired STMActionsInfoProvider userserviceActionsInfoProvider(@Qualifier("userserviceFlowStore") STMFlowStoreImpl stmFlowStore) {
		STMActionsInfoProvider provider =  new STMActionsInfoProvider(stmFlowStore);
        WorkflowRegistry.addSTMActionsInfoProvider("userservice",provider);
        return provider;
	}
	
	@Bean EntityStore<Userservice> userserviceEntityStore() {
		return new UserserviceEntityStore();
	}
	
	@Bean @Autowired StateEntityServiceImpl<Userservice> _userserviceStateEntityService_(
			@Qualifier("userserviceEntityStm") STM<Userservice> stm,
			@Qualifier("userserviceActionsInfoProvider") STMActionsInfoProvider userserviceInfoProvider,
			@Qualifier("userserviceEntityStore") EntityStore<Userservice> entityStore){
		return new StateEntityServiceImpl<>(stm, userserviceInfoProvider, entityStore);
	}
	
	// Now we start constructing the STM Components 
	
	@Bean @Autowired GenericEntryAction<Userservice> userserviceEntryAction(@Qualifier("userserviceEntityStore") EntityStore<Userservice> entityStore,
			@Qualifier("userserviceActionsInfoProvider") STMActionsInfoProvider userserviceInfoProvider){
		return new GenericEntryAction<Userservice>(entityStore,userserviceInfoProvider);
	}
	
	@Bean GenericExitAction<Userservice> userserviceExitAction(){
		return new GenericExitAction<Userservice>();
	}
	
	@Bean @Autowired StmBodyTypeSelector userserviceBodyTypeSelector(@Qualifier("userserviceActionsInfoProvider") STMActionsInfoProvider userserviceInfoProvider) {
		return new StmBodyTypeSelector(userserviceInfoProvider);
	}
	
	@Bean @Autowired STMTransitionAction<Userservice> userserviceBaseTransitionAction(){
		return new BaseTransitionAction<>();
	}
	
	@Bean AssignUserserviceAction assignUserservice() {
		return new AssignUserserviceAction();
	}
	
	@Bean ResolveUserserviceAction resolveUserservice() {
		return new ResolveUserserviceAction();
	}
	
	@Bean CloseUserserviceAction closeUserservice() {
		return new CloseUserserviceAction();
	}

	@Bean
	XmlFlowReader userserviceFlowReader(@Qualifier("userserviceFlowStore") STMFlowStoreImpl flowStore) throws Exception {
		XmlFlowReader flowReader = new XmlFlowReader(flowStore);
		flowReader.setFilename(FLOW_DEFINITION_FILE);
		return flowReader;
	}
	

	@Bean UserserviceHealthChecker userserviceHealthChecker(){
    	return new UserserviceHealthChecker();
    }

    @Bean @Autowired Function<ChenileExchange, String[]> userserviceEventAuthoritiesSupplier(
        @Qualifier("userserviceActionsInfoProvider") STMActionsInfoProvider userserviceInfoProvider)
                    throws Exception{
        StmAuthoritiesBuilder builder = new StmAuthoritiesBuilder(userserviceInfoProvider);
        return builder.build();
    }
}
