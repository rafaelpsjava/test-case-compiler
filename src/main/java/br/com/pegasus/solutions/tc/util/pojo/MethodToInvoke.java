package br.com.pegasus.solutions.tc.util.pojo;

import java.util.List;

/**
 * MethodToInvoke
 * 
 * @author Rafael Peres dos Santos
 *
 */
public class MethodToInvoke {

	private String name;

	private Object[] parameters;

	private List<Class<?>> parametersTypes;

	private Object invocationResult;

	public MethodToInvoke(String name) {
		this.name = name;
	}

	public MethodToInvoke(String name, List<Class<?>> parametersTypes) {
		this(name, null, parametersTypes);
	}

	public MethodToInvoke(String name, Object[] parameters) {
		this(name, parameters, null);
	}

	public MethodToInvoke(String name, Object[] parameters, List<Class<?>> parametersTypes) {
		this.name = name;
		this.parametersTypes = parametersTypes;
		this.parameters = parameters;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Class<?>> getParametersTypes() {
		return parametersTypes;
	}

	public Class<?>[] getParametersTypesAsArray() {
		if (getParametersTypes() != null) {
			return getParametersTypes().toArray(new Class<?>[] {});
		}
		return null;
	}

	public void setParametersTypes(List<Class<?>> clazzs) {
		this.parametersTypes = clazzs;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public void setParameters(Object[] args) {
		this.parameters = args;
	}

	public Object getInvocationResult() {
		return invocationResult;
	}

	public void setInvocationResult(Object invocationResult) {
		this.invocationResult = invocationResult;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MethodToInvoke other = (MethodToInvoke) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
