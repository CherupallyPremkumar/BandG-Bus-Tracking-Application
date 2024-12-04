package com.bgbus.tracker.bus.configuration;

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
import com.bgbus.tracker.bus.model.Bus;
import com.bgbus.tracker.bus.service.cmds.AssignBusAction;
import com.bgbus.tracker.bus.service.cmds.CloseBusAction;
import com.bgbus.tracker.bus.service.cmds.ResolveBusAction;
import com.bgbus.tracker.bus.service.healthcheck.BusHealthChecker;
import com.bgbus.tracker.bus.service.store.BusEntityStore;
import org.chenile.workflow.api.WorkflowRegistry;
import org.chenile.workflow.service.stmcmds.StmAuthoritiesBuilder;
import java.util.function.Function;
import org.chenile.core.context.ChenileExchange;

/**
 This is where you will instantiate all the required classes in Spring

*/
@Configuration
public class BusConfiguration {
	private static final String FLOW_DEFINITION_FILE = "com/bgbus/tracker/bus/states.xml";
	
	@Bean BeanFactoryAdapter busBeanFactoryAdapter() {
		return new SpringBeanFactoryAdapter();
	}
	
	@Bean STMFlowStoreImpl busFlowStore(@Qualifier("busBeanFactoryAdapter") BeanFactoryAdapter busBeanFactoryAdapter) throws Exception{
		STMFlowStoreImpl stmFlowStore = new STMFlowStoreImpl();
		stmFlowStore.setBeanFactory(busBeanFactoryAdapter);
		return stmFlowStore;
	}
	
	@Bean @Autowired STM<Bus> busEntityStm(@Qualifier("busFlowStore") STMFlowStoreImpl stmFlowStore) throws Exception{
		STMImpl<Bus> stm = new STMImpl<>();		
		stm.setStmFlowStore(stmFlowStore);
		return stm;
	}
	
	@Bean @Autowired STMActionsInfoProvider busActionsInfoProvider(@Qualifier("busFlowStore") STMFlowStoreImpl stmFlowStore) {
		STMActionsInfoProvider provider =  new STMActionsInfoProvider(stmFlowStore);
        WorkflowRegistry.addSTMActionsInfoProvider("bus",provider);
        return provider;
	}
	
	@Bean EntityStore<Bus> busEntityStore() {
		return new BusEntityStore();
	}
	
	@Bean @Autowired StateEntityServiceImpl<Bus> _busStateEntityService_(
			@Qualifier("busEntityStm") STM<Bus> stm,
			@Qualifier("busActionsInfoProvider") STMActionsInfoProvider busInfoProvider,
			@Qualifier("busEntityStore") EntityStore<Bus> entityStore){
		return new StateEntityServiceImpl<>(stm, busInfoProvider, entityStore);
	}
	
	// Now we start constructing the STM Components 
	
	@Bean @Autowired GenericEntryAction<Bus> busEntryAction(@Qualifier("busEntityStore") EntityStore<Bus> entityStore,
			@Qualifier("busActionsInfoProvider") STMActionsInfoProvider busInfoProvider){
		return new GenericEntryAction<Bus>(entityStore,busInfoProvider);
	}
	
	@Bean GenericExitAction<Bus> busExitAction(){
		return new GenericExitAction<Bus>();
	}
	
	@Bean @Autowired StmBodyTypeSelector busBodyTypeSelector(@Qualifier("busActionsInfoProvider") STMActionsInfoProvider busInfoProvider) {
		return new StmBodyTypeSelector(busInfoProvider);
	}
	
	@Bean @Autowired STMTransitionAction<Bus> busBaseTransitionAction(){
		return new BaseTransitionAction<>();
	}
	
	@Bean AssignBusAction assignBus() {
		return new AssignBusAction();
	}
	
	@Bean ResolveBusAction resolveBus() {
		return new ResolveBusAction();
	}
	
	@Bean CloseBusAction closeBus() {
		return new CloseBusAction();
	}

	@Bean
	XmlFlowReader busFlowReader(@Qualifier("busFlowStore") STMFlowStoreImpl flowStore) throws Exception {
		XmlFlowReader flowReader = new XmlFlowReader(flowStore);
		flowReader.setFilename(FLOW_DEFINITION_FILE);
		return flowReader;
	}
	

	@Bean BusHealthChecker busHealthChecker(){
    	return new BusHealthChecker();
    }

    @Bean @Autowired Function<ChenileExchange, String[]> busEventAuthoritiesSupplier(
        @Qualifier("busActionsInfoProvider") STMActionsInfoProvider busInfoProvider)
                    throws Exception{
        StmAuthoritiesBuilder builder = new StmAuthoritiesBuilder(busInfoProvider);
        return builder.build();
    }
}
