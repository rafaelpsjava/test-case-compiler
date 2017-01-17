package br.com.pegasus.solutions.tc.pojo.expression;

/**
 * ExpressionType
 * 
 * @author Rafael Peres dos Santos
 *
 */
public enum ExpressionType {

	NOT_NULL("not null"), IS_NULL("is null"), EQ("=="), GT(">"), GTE(">="), LT("<"), LTE("lte"), AND("and"), OR("or");

	private String desc;

	private ExpressionType(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public static ExpressionType getValueFrom(String desc) {
		try {
			for (ExpressionType expressionType : ExpressionType.values()) {
				if (expressionType.getDesc().equals(desc)) {
					return expressionType;
				}
			}
			return null;
		} catch (Throwable throwable) {
			return null;
		}
	}

}
