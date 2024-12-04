package com.bgbus.tracker.busstop.configuration;

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
import com.bgbus.tracker.busstop.model.Busstop;
import com.bgbus.tracker.busstop.service.cmds.AssignBusstopAction;
import com.bgbus.tracker.busstop.service.cmds.CloseBusstopAction;
import com.bgbus.tracker.busstop.service.cmds.ResolveBusstopAction;
import com.bgbus.tracker.busstop.service.healthcheck.BusstopHealthChecker;
import com.bgbus.tracker.busstop.service.store.BusstopEntityStore;
import org.chenile.workflow.api.WorkflowRegistry;
import org.chenile.workflow.service.stmcmds.StmAuthoritiesBuilder;
import java.util.function.Function;
import org.chenile.core.context.ChenileExchange;

/**
 This is where you will instantiate all the required classes in Spring

*/
@Configuration
public class BusstopConfiguration {
	private static final String FLOW_DEFINITION_FILE = "com/bgbus/tracker/busstop/states.xml";
	
	@Bean BeanFactoryAdapter busstopBeanFactoryAdapter() {
		return new SpringBeanFactoryAdapter();
	}
	
	@Bean STMFlowStoreImpl busstopFlowStore(@Qualifier("busstopBeanFactoryAdapter") BeanFactoryAdapter busstopBeanFactoryAdapter) throws Exception{
		STMFlowStoreImpl stmFlowStore = new STMFlowStoreImpl();
		stmFlowStore.setBeanFactory(busstopBeanFactoryAdapter);
		return stmFlowStore;
	}
	
	@Bean @Autowired STM<Busstop> busstopEntityStm(@Qualifier("busstopFlowStore") STMFlowStoreImpl stmFlowStore) throws Exception{
		STMImpl<Busstop> stm = new STMImpl<>();		
		stm.setStmFlowStore(stmFlowStore);
		return stm;
	}
	
	@Bean @Autowired STMActionsInfoProvider busstopActionsInfoProvider(@Qualifier("busstopFlowStore") STMFlowStoreImpl stmFlowStore) {
		STMActionsInfoProvider provider =  new STMActionsInfoProvider(stmFlowStore);
        WorkflowRegistry.addSTMActionsInfoProvider("busstop",provider);
        return provider;
	}
	
	@Bean EntityStore<Busstop> busstopEntityStore() {
		return new BusstopEntityStore();
	}
	
	@Bean @Autowired StateEntityServiceImpl<Busstop> _busstopStateEntityService_(
			@Qualifier("busstopEntityStm") STM<Busstop> stm,
			@Qualifier("busstopActionsInfoProvider") STMActionsInfoProvider busstopInfoProvider,
			@Qualifier("busstopEntityStore") EntityStore<Busstop> entityStore){
		return new StateEntityServiceImpl<>(stm, busstopInfoProvider, entityStore);
	}
	
	// Now we start constructing the STM Components 
	
	@Bean @Autowired GenericEntryAction<Busstop> busstopEntryAction(@Qualifier("busstopEntityStore") EntityStore<Busstop> entityStore,
			@Qualifier("busstopActionsInfoProvider") STMActionsInfoProvider busstopInfoProvider){
		return new GenericEntryAction<Busstop>(entityStore,busstopInfoProvider);
	}
	
	@Bean GenericExitAction<Busstop> busstopExitAction(){
		return new GenericExitAction<Busstop>();
	}
	
	@Bean @Autowired StmBodyTypeSelector busstopBodyTypeSelector(@Qualifier("busstopActionsInfoProvider") STMActionsInfoProvider busstopInfoProvider) {
		return new StmBodyTypeSelector(busstopInfoProvider);
	}
	
	@Bean @Autowired STMTransitionAction<Busstop> busstopBaseTransitionAction(){
		return new BaseTransitionAction<>();
	}
	
	@Bean AssignBusstopAction assignBusstop() {
		return new AssignBusstopAction();
	}
	
	@Bean ResolveBusstopAction resolveBusstop() {
		return new ResolveBusstopAction();
	}
	
	@Bean CloseBusstopAction closeBusstop() {
		return new CloseBusstopAction();
	}

	@Bean
	XmlFlowReader busstopFlowReader(@Qualifier("busstopFlowStore") STMFlowStoreImpl flowStore) throws Exception {
		XmlFlowReader flowReader = new XmlFlowReader(flowStore);
		flowReader.setFilename(FLOW_DEFINITION_FILE);
		return flowReader;
	}
	

	@Bean BusstopHealthChecker busstopHealthChecker(){
    	return new BusstopHealthChecker();
    }

    @Bean @Autowired Function<ChenileExchange, String[]> busstopEventAuthoritiesSupplier(
        @Qualifier("busstopActionsInfoProvider") STMActionsInfoProvider busstopInfoProvider)
                    throws Exception{
        StmAuthoritiesBuilder builder = new StmAuthoritiesBuilder(busstopInfoProvider);
        return builder.build();
    }
}
