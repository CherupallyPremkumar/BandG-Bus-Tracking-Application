package com.bgbus.tracker.route.configuration;

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
import com.bgbus.tracker.route.model.Route;
import com.bgbus.tracker.route.service.cmds.AssignRouteAction;
import com.bgbus.tracker.route.service.cmds.CloseRouteAction;
import com.bgbus.tracker.route.service.cmds.ResolveRouteAction;
import com.bgbus.tracker.route.service.healthcheck.RouteHealthChecker;
import com.bgbus.tracker.route.service.store.RouteEntityStore;
import org.chenile.workflow.api.WorkflowRegistry;
import org.chenile.workflow.service.stmcmds.StmAuthoritiesBuilder;
import java.util.function.Function;
import org.chenile.core.context.ChenileExchange;

/**
 This is where you will instantiate all the required classes in Spring

*/
@Configuration
public class RouteConfiguration {
	private static final String FLOW_DEFINITION_FILE = "com/bgbus/tracker/route/states.xml";
	
	@Bean BeanFactoryAdapter routeBeanFactoryAdapter() {
		return new SpringBeanFactoryAdapter();
	}
	
	@Bean STMFlowStoreImpl routeFlowStore(@Qualifier("routeBeanFactoryAdapter") BeanFactoryAdapter routeBeanFactoryAdapter) throws Exception{
		STMFlowStoreImpl stmFlowStore = new STMFlowStoreImpl();
		stmFlowStore.setBeanFactory(routeBeanFactoryAdapter);
		return stmFlowStore;
	}
	
	@Bean @Autowired STM<Route> routeEntityStm(@Qualifier("routeFlowStore") STMFlowStoreImpl stmFlowStore) throws Exception{
		STMImpl<Route> stm = new STMImpl<>();		
		stm.setStmFlowStore(stmFlowStore);
		return stm;
	}
	
	@Bean @Autowired STMActionsInfoProvider routeActionsInfoProvider(@Qualifier("routeFlowStore") STMFlowStoreImpl stmFlowStore) {
		STMActionsInfoProvider provider =  new STMActionsInfoProvider(stmFlowStore);
        WorkflowRegistry.addSTMActionsInfoProvider("route",provider);
        return provider;
	}
	
	@Bean EntityStore<Route> routeEntityStore() {
		return new RouteEntityStore();
	}
	
	@Bean @Autowired StateEntityServiceImpl<Route> _routeStateEntityService_(
			@Qualifier("routeEntityStm") STM<Route> stm,
			@Qualifier("routeActionsInfoProvider") STMActionsInfoProvider routeInfoProvider,
			@Qualifier("routeEntityStore") EntityStore<Route> entityStore){
		return new StateEntityServiceImpl<>(stm, routeInfoProvider, entityStore);
	}
	
	// Now we start constructing the STM Components 
	
	@Bean @Autowired GenericEntryAction<Route> routeEntryAction(@Qualifier("routeEntityStore") EntityStore<Route> entityStore,
			@Qualifier("routeActionsInfoProvider") STMActionsInfoProvider routeInfoProvider){
		return new GenericEntryAction<Route>(entityStore,routeInfoProvider);
	}
	
	@Bean GenericExitAction<Route> routeExitAction(){
		return new GenericExitAction<Route>();
	}
	
	@Bean @Autowired StmBodyTypeSelector routeBodyTypeSelector(@Qualifier("routeActionsInfoProvider") STMActionsInfoProvider routeInfoProvider) {
		return new StmBodyTypeSelector(routeInfoProvider);
	}
	
	@Bean @Autowired STMTransitionAction<Route> routeBaseTransitionAction(){
		return new BaseTransitionAction<>();
	}
	
	@Bean AssignRouteAction assignRoute() {
		return new AssignRouteAction();
	}
	
	@Bean ResolveRouteAction resolveRoute() {
		return new ResolveRouteAction();
	}
	
	@Bean CloseRouteAction closeRoute() {
		return new CloseRouteAction();
	}

	@Bean
	XmlFlowReader routeFlowReader(@Qualifier("routeFlowStore") STMFlowStoreImpl flowStore) throws Exception {
		XmlFlowReader flowReader = new XmlFlowReader(flowStore);
		flowReader.setFilename(FLOW_DEFINITION_FILE);
		return flowReader;
	}
	

	@Bean RouteHealthChecker routeHealthChecker(){
    	return new RouteHealthChecker();
    }

    @Bean @Autowired Function<ChenileExchange, String[]> routeEventAuthoritiesSupplier(
        @Qualifier("routeActionsInfoProvider") STMActionsInfoProvider routeInfoProvider)
                    throws Exception{
        StmAuthoritiesBuilder builder = new StmAuthoritiesBuilder(routeInfoProvider);
        return builder.build();
    }
}
