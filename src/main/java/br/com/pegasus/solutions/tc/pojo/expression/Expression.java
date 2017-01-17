package br.com.pegasus.solutions.tc.pojo.expression;

import java.util.ArrayList;
import java.util.List;

/**
 * Expression
 * 
 * @author Rafael Peres dos Santos
 *
 */
public class Expression {

	private String name;

	private ExpressionType previusExpressionType;

	private String methodToInvoke;

	private ExpressionType expressionType;

	private String valueToCompare;

	private List<Expression> subExpressions;

	public Expression() {
	}

	public Expression(String name) {
		this.name = name;
	}

	public Expression(ExpressionType previusExpressionType, String methodToInvoke, ExpressionType expressionType,
			String valueToCompare) {
		this.setPreviusExpressionType(previusExpressionType);
		this.setMethodToInvoke(methodToInvoke);
		this.setExpressionType(expressionType);
		this.setValueToCompare(valueToCompare);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ExpressionType getPreviusExpressionType() {
		return previusExpressionType;
	}

	public void setPreviusExpressionType(ExpressionType previusExpressionType) {
		this.previusExpressionType = previusExpressionType;
	}

	public String getMethodToInvoke() {
		return methodToInvoke;
	}

	public void setMethodToInvoke(String methodToInvoke) {
		this.methodToInvoke = methodToInvoke;
	}

	public ExpressionType getExpressionType() {
		return expressionType;
	}

	public void setExpressionType(ExpressionType expressionType) {
		this.expressionType = expressionType;
	}

	public String getValueToCompare() {
		return valueToCompare;
	}

	public void setValueToCompare(String valueToCompare) {
		this.valueToCompare = valueToCompare;
	}

	public List<Expression> getSubExpressions() {
		if (this.subExpressions == null) {
			this.subExpressions = new ArrayList<>();
		}
		return subExpressions;
	}

	public void setSubExpressions(List<Expression> subExpressions) {
		this.subExpressions = subExpressions;
	}

}
