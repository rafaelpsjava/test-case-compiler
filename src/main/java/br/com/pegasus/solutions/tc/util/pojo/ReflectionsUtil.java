package br.com.pegasus.solutions.tc.util.pojo;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;


/**
 * ReflectionsUtil
 * 
 * @author Rafael Peres dos Santos
 *
 */
public class ReflectionsUtil extends Reflections {
	private static ReflectionsUtil reflectionsUtil;

	private ReflectionsUtil() {
	}

	/**
	 * getResouces
	 * 
	 * @param resourceSuffix
	 * @return
	 */
	public synchronized Set<String> getResouces(String directory, String resourceSuffix) {
		if (resourceSuffix == null || resourceSuffix.trim().isEmpty()) {
			throw new IllegalArgumentException("resourceSuffix cannot be null or empty!");
		}

		Reflections reflections = new Reflections(directory == null ? "" : directory, new ResourcesScanner());
		Set<String> resources = reflections.getResources(Pattern.compile(".*\\." + resourceSuffix));
		return resources;
	}

	/**
	 * invoke
	 * 
	 * this method will invoke the MethodInvocation[] and set the result
	 * MethodInvocation[i] the invocationResult
	 * 
	 * @param object
	 *            {@link Object} The instance where methods will be invoked
	 * @param methodsToInvoke
	 *            {@link MethodToInvoke}
	 * @return {@link Void}
	 */
	public synchronized void invoke(Object object, List<MethodToInvoke> methodsToInvoke) {
		if (methodsToInvoke != null) {

			for (int i = 0; i < methodsToInvoke.size(); i++) {
				MethodToInvoke methodInvocation = methodsToInvoke.get(i);
				try {
					Method declaredMethod = null;
					if (methodInvocation.getParametersTypesAsArray() == null) {
						declaredMethod = object.getClass().getDeclaredMethod(methodInvocation.getName());
					} else {
						declaredMethod = object.getClass().getDeclaredMethod(methodInvocation.getName(), methodInvocation.getParametersTypesAsArray());
					}

					if (declaredMethod != null) {
						Object invocationResult = null;
						if (methodInvocation.getParameters() != null) {
							invocationResult = declaredMethod.invoke(object, methodInvocation.getParameters());
						} else {
							invocationResult = declaredMethod.invoke(object);
						}
						methodInvocation.setInvocationResult(invocationResult);
					}
				} catch (Throwable throwable) {
				}
			}
		}
	}

	public static ReflectionsUtil getInstance() {
		if (reflectionsUtil == null) {
			synchronized (ReflectionsUtil.class) {
				if (reflectionsUtil == null) {
					reflectionsUtil = new ReflectionsUtil();
				}
			}
		}
		return reflectionsUtil;
	}

	public static ReflectionsUtil getNewInstance() {
		return new ReflectionsUtil();
	}
}
