package br.com.pegasus.solutions.tc.util.pojo;

/**
 * MatcherExpressionType
 * 
 * @author Rafael Peres dos Santos
 *
 */
public enum MatcherExpressionType {

	ACCEPT_CHARS, TYPES, CLASS, METHODS_IMPL;

	/**
	 * getType
	 * 
	 * @param expressionType
	 *            {@link MatcherExpressionType}
	 * @return {@link MatcherExpressionType}
	 */
	public static MatcherExpressionType getType(String expressionType) {
		MatcherExpressionType[] values = values();
		if (expressionType != null) {
			for (int i = 0; i < values.length; i++) {
				if (values[i].name().equals(expressionType.toUpperCase())) {
					return values[i];
				}
			}
		}
		return null;
	}
}
