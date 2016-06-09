package com.github.miyasumas.armeria.sandbox;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.linecorp.armeria.common.SessionProtocol;
import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.Service;
import com.linecorp.armeria.server.docs.DocService;
import com.linecorp.armeria.server.http.healthcheck.HttpHealthCheckService;

/**
 * @author MIYASAKA Yasumasa
 * @since 2016/04/29
 */
public class Main {

	private static Injector injector;

	@Inject
	private Map<String, Service> services;

	public void start() throws InterruptedException, ExecutionException {
		System.out.println(services);
		ServerBuilder serverBuilder = new ServerBuilder()
			.port(8080, SessionProtocol.HTTP)
			.serviceAt("/ping", new HttpHealthCheckService());
		services.forEach((k, v) -> serverBuilder.serviceAt(k, v));
		serverBuilder.serviceUnder("/docs/", new DocService());
		System.out.println(serverBuilder);
		Server server = serverBuilder.build();
		System.out.println(server);
		server.start().get();
	}

	/**
	 * @param args
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		injector = Guice.createInjector(new SampleModule());
		injector.getInstance(Main.class).start();
	}
}
