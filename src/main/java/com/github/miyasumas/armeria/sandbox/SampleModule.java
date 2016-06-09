package com.github.miyasumas.armeria.sandbox;

import java.util.function.Supplier;
import javax.inject.Provider;
import com.github.miyasumas.armeria.sandbox.service.HelloService;
import com.github.miyasumas.armeria.sandbox.service.HelloServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import com.linecorp.armeria.common.SerializationFormat;
import com.linecorp.armeria.server.Service;
import com.linecorp.armeria.server.logging.LoggingService;
import com.linecorp.armeria.server.thrift.ThriftService;

/**
 * @author MIYASAKA Yasumasa
 * @since 2016/05/02
 */
public class SampleModule extends AbstractModule {

	/**
	 * {@inheritDoc}
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		MapBinder<String, Service> serviceBinder = MapBinder.newMapBinder(binder(), String.class, Service.class);
		serviceBinder.addBinding("/hello").toProvider(createServiceProvider(HelloServiceImpl::new));
	}

	private Provider<Service> createServiceProvider(Supplier<Object> serviceCreator) {
		return () -> {
			return ThriftService.of(serviceCreator.get(), SerializationFormat.THRIFT_TEXT)
				.decorate(LoggingService::new);
		};
	}
}
