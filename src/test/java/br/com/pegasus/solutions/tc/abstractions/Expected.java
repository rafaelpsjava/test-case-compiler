package br.com.pegasus.solutions.tc.abstractions;

/**
 * Expected
 * 
 * @author Rafael Peres dos Santos
 *
 */
public enum Expected {

	TRUE, FALSE;

	public boolean valueAsBoolean() {
		return this.equals(TRUE);
	}
}
