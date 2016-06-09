package com.github.miyasumas.armeria.sandbox.service;

import java.time.Instant;
import javax.inject.Inject;
import org.apache.thrift.TException;
import com.github.miyasumas.armeria.sandbox.Now;

/**
 * @author MIYASAKA Yasumasa
 * @since 2016/04/29
 */
public class HelloServiceImpl implements HelloService.Iface {

	@Inject
	@Now
	private Instant now;

	/**
	 * {@inheritDoc}
	 * @see com.github.miyasumas.armeria.sandbox.service.HelloService.Iface#hello(java.lang.String)
	 */
	@Override
	public String helloA(String name) throws TException {
		return "Hello, " + name + " (" + now + ")";
	}
}
