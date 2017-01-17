package br.com.pegasus.solutions.tc.util.tc.builder;

import java.util.ArrayList;
import java.util.List;

import br.com.pegasus.solutions.tc.pojo.TC;
import br.com.pegasus.solutions.tc.pojo.expression.Expression;
import br.com.pegasus.solutions.tc.pojo.expression.ExpressionType;
import br.com.pegasus.solutions.tc.pojo.expression.FailExpresssion;
import br.com.pegasus.solutions.tc.pojo.expression.SuccessExpression;
import br.com.pegasus.solutions.tc.util.StringUtil;

/**
 * ExpressionBuilder
 * 
 * @author Rafael Peres dos Santos
 *
 */
public class ExpressionBuilder {
	private static ExpressionBuilder expressionBuilder;

	private ExpressionBuilder() {
	}

	/**
	 * getInstance
	 * 
	 * @return {@link ExpressionBuilder}
	 */
	public static ExpressionBuilder getInstance() {
		if (expressionBuilder == null) {
			synchronized (TCBuilder.class) {
				if (expressionBuilder == null) {
					expressionBuilder = new ExpressionBuilder();
				}
			}
		}

		return expressionBuilder;
	}

	/**
	 * getNewIntance
	 * 
	 * @return {@link ExpressionBuilder}
	 */
	public static ExpressionBuilder getNewIntance() {
		return new ExpressionBuilder();
	}

	/**
	 * addExpression
	 * 
	 * build the expression that is used to assert if implemented is ok. and add
	 * set it in the expression
	 * 
	 * @param expression
	 *            {@link Expression}
	 * @param tcFile
	 *            {@link String}
	 * @param tc
	 *            {@link TC}
	 * @param tcLineIndex
	 *            int
	 * @param tcLine
	 *            {@link String}
	 */
	public void addExpression(Expression expression, String tcFile, TC tc, int tcLineIndex, String tcLine) {
		String[] andOrExpressions = tcLine.split("and|or|AND|OR");
		List<String> expressionTypesStringArray = new ArrayList<>();

		for (int andOrExpressionIndex = 0; andOrExpressionIndex < andOrExpressions.length; andOrExpressionIndex++) {
			andOrExpressions[andOrExpressionIndex] = andOrExpressions[andOrExpressionIndex].trim();

			if (andOrExpressionIndex == 0) {
				expression.setMethodToInvoke(andOrExpressions[andOrExpressionIndex]);
				String expressionTypeString = StringUtil.getInstance().replace(tcLine, " ", andOrExpressions)
						.replaceAll(System.getProperty("line.separator"), "").replace("\t", "");
				if (expressionTypeString != null && expressionTypeString.length() > 0) {
					String[] tokens = expressionTypeString.split(" ");
					if (tokens != null) {
						for (String token : tokens) {
							if (StringUtil.getInstance().isNotEmpty(token)) {
								expressionTypesStringArray.add(token);
							}
						}
					}
				}

			} else if (andOrExpressionIndex >= 1) {
				if (StringUtil.getInstance().contains(andOrExpressions[andOrExpressionIndex], "(")) {
					addExpressionInParentheses(tcLine, tcFile, tcLineIndex, andOrExpressions, andOrExpressionIndex);

				} else {
					addExpression(expression, tcLine, andOrExpressions, expressionTypesStringArray,
							andOrExpressionIndex);
				}
			}
		}

		if (expression instanceof SuccessExpression) {
			tc.getSuccessExpressions().add((SuccessExpression) expression);
		} else if (expression instanceof FailExpresssion) {
			tc.getFailExpressions().add((FailExpresssion) expression);
		} else {
			throw new IllegalArgumentException("invalid expression!");
		}
	}

	/**
	 * addExpression
	 * 
	 * @param expression
	 *            {@link Expression}
	 * @param tcLine
	 *            {@link String}
	 * @param andOrExpressions
	 *            {@link String}[]
	 * @param expressionTypesStringArray
	 *            {@link List}<String>
	 * @param i
	 *            int
	 */
	private void addExpression(Expression expression, String tcLine, String[] andOrExpressions,
			List<String> expressionTypesStringArray, int i) {
		String[] andOrExpressionTokens = andOrExpressions[i].split("not null|NOT NULL|is null|IS NULL|>=|<=|<|>|==");

		String methodToInvoke = andOrExpressionTokens[0].trim();
		String valueToCompare = andOrExpressionTokens[1].trim();
		String subTypeExpression = andOrExpressions[i].replace(methodToInvoke, "").replace(valueToCompare, "").trim();

		ExpressionType previusExpressionType = ExpressionType.getValueFrom(expressionTypesStringArray.get(i - 1));
		List<Expression> subExpressions = expression.getSubExpressions();
		ExpressionType expressionType = ExpressionType.getValueFrom(subTypeExpression);
		Expression subExpression = new Expression(previusExpressionType, methodToInvoke, expressionType,
				valueToCompare);

		String previusExpressionTypeString = StringUtil.getInstance().replace(tcLine, "", andOrExpressions);
		previusExpressionType = ExpressionType.getValueFrom(previusExpressionTypeString);

		subExpressions.add(subExpression);
	}

	/**
	 * addExpressionInParentheses
	 * 
	 * @param tcLine
	 *            {@link String}
	 * @param tcFile
	 *            {@link String}
	 * @param tcLineIndex
	 *            int
	 * @param andOrExpressions
	 *            {@link String}[]
	 * @param andOrExpressionIndex
	 *            int
	 */
	public void addExpressionInParentheses(String tcLine, String tcFile, int tcLineIndex, String[] andOrExpressions,
			int andOrExpressionIndex) {
		// TODO to impl
	}
}
