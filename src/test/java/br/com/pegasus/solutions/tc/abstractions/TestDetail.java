package br.com.pegasus.solutions.tc.abstractions;

/**
 * TestDetail
 * 
 * @author Rafael Peres dos Santos
 *
 */
public final class TestDetail {

	private String value;

	private Expected expected;

	public TestDetail() {
	}

	public TestDetail(String value, Expected expected) {
		this.value = value;
		this.expected = expected;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Expected getExpected() {
		return expected;
	}

	public void setExpected(Expected expected) {
		this.expected = expected;
	}

}
