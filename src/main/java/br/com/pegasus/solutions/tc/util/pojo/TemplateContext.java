package br.com.pegasus.solutions.tc.util.pojo;

import java.util.Hashtable;
import java.util.Map;

/**
 * TemplateContext
 * 
 * @author Rafael Peres dos Santos
 *
 */
@SuppressWarnings("serial")
public class TemplateContext extends Hashtable<String, String> {

	public TemplateContext() {
	}

	@Override
	public String put(String key, String value) {

		return super.put(String.format("${%s}", key), value);
	}

	@Override
	public synchronized void putAll(Map<? extends String, ? extends String> hashtable) {
		for (String key : hashtable.keySet()) {
			this.put(key, hashtable.get(key));
		}
	}

}
