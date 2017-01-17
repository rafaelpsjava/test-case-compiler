package br.com.pegasus.solutions.tc.util.tc.builder;

import java.util.List;

import br.com.pegasus.solutions.tc.pojo.MainTC;
import br.com.pegasus.solutions.tc.pojo.TC;
import br.com.pegasus.solutions.tc.pojo.VariableDefinition;
import br.com.pegasus.solutions.tc.pojo.expression.FailExpresssion;
import br.com.pegasus.solutions.tc.pojo.expression.SuccessExpression;
import br.com.pegasus.solutions.tc.util.AppConstants;
import br.com.pegasus.solutions.tc.util.FileReaderUtil;
import br.com.pegasus.solutions.tc.util.StringUtil;

/**
 * TCBuilder
 * 
 * @author Rafael Peres dos Santos
 *
 */
public class TCBuilder {

	private static final int VARIABLE_DECL = 1;
	private static final int VARIABLE_FAIL_DECL = 2;
	private static final int VARIABLE_SUCCESS_DECL = 3;
	private static final int AUTHOR_INDEX = 0;
	private static final int DESCRIPTION_INDEX = 1;

	private static TCBuilder tCBuilder;

	private TCBuilder() {
	}

	/**
	 * getInstance
	 * 
	 * @return {@link TCBuilder}
	 */
	public static TCBuilder getInstance() {
		if (tCBuilder == null) {
			synchronized (TCBuilder.class) {
				if (tCBuilder == null) {
					tCBuilder = new TCBuilder();
				}
			}
		}

		return tCBuilder;
	}

	/**
	 * getNewIntance
	 * 
	 * @return {@link TCBuilder}
	 */
	public static TCBuilder getNewIntance() {
		return new TCBuilder();
	}

	/**
	 * addTC
	 * 
	 * get the lines of the test case file
	 * 
	 * for each line
	 * 
	 * check if the tcLineIndex of the current line is equal to specific type of
	 * the test case atribute(s) if true build the content and set the value
	 * 
	 * @param lines
	 *            {@link List}<String>
	 * @param mainTC
	 *            {@link MainTC}
	 * @param mainTcLineIndex
	 *            int
	 */
	public void addTC(Class<?> clazz, List<String> lines, MainTC mainTC, int mainTcLineIndex) {
		List<TC> tcs = mainTC.getTcs();
		String tcFile = lines.get(mainTcLineIndex);

		TC tc = new TC(tcFile);
		List<String> tcLines = null;
		try {
			tcLines = FileReaderUtil.getInstance().getLines(clazz, String.format(AppConstants.TC_BASE_PATH, tcFile));
		} catch (Throwable e) {
			throw new RuntimeException("An error occured while trying read file : " + tcFile);
		}

		int count = 0;
		for (int tcLineIndex = 0; tcLineIndex < tcLines.size(); tcLineIndex++) {
			String tcLine = tcLines.get(tcLineIndex).replaceAll(AppConstants.LINE_SEPARATOR, "");

			if (tcLineIndex == AUTHOR_INDEX) {
				tc.setAuthor(tcLine.replace("# ", "").replace("#", ""));

			} else if (tcLineIndex == DESCRIPTION_INDEX) {
				tc.setDescription(tcLine.replace("# ", "").replace("#", ""));

			} else if (!StringUtil.getInstance().isEmpty(tcLine)) {
				if (tcLine.startsWith("#")) {
					count++;
				}

				if (StringUtil.getInstance().isNotEmpty(tcLine) && !tcLine.startsWith("#")) {
					if (count == VARIABLE_DECL) {
						addVariable(tcFile, tcLine, tc, tcLineIndex);

					} else {
						String tcMethodName = tcLine.substring(0, tcLine.indexOf("()") + 2);
						tcLine = tcLine.replace(tcMethodName, "").replaceAll("[//]+", "").trim();
						tcMethodName = tcMethodName.replaceAll("[//()]+", "").trim();

						if (count == VARIABLE_FAIL_DECL && !StringUtil.getInstance().isEmpty(tcLine)) {
							String methodName = getTcMethodName(tcLineIndex, tcMethodName, "fail");
							FailExpresssion failExpresssion = new FailExpresssion(methodName);
							ExpressionBuilder.getInstance().addExpression(failExpresssion, tcFile, tc, tcLineIndex,
									tcLine);

						} else if (count == VARIABLE_SUCCESS_DECL && !StringUtil.getInstance().isEmpty(tcLine)) {
							String methodName = getTcMethodName(tcLineIndex, tcMethodName, "success");
							SuccessExpression successExpression = new SuccessExpression(methodName);
							ExpressionBuilder.getInstance().addExpression(successExpression, tcFile, tc, tcLineIndex,
									tcLine);
						}
					}
				}
			}
		}

		tcs.add(tc);
	}

	/**
	 * getTcMethodName
	 * 
	 * @param tcLineIndex
	 *            int
	 * @param tcMethodName
	 *            {@link String}
	 * @param baseMethodName
	 *            {@link String}
	 * @return {@link String}
	 */
	private String getTcMethodName(int tcLineIndex, String tcMethodName, String baseMethodName) {
		return tcMethodName.isEmpty() ? String.format("%sExpresssion%s", baseMethodName, tcLineIndex) : tcMethodName;
	}

	/**
	 * addVariable
	 * 
	 * this method is like the same just the type(s) are different of the
	 * MainTCBuilder.addPackageDecl method.
	 * 
	 * @param tcFile
	 *            {@link String}
	 * 
	 * @param lines
	 *            {@link List}<String>
	 * @param tc
	 *            {@link TC}
	 * @param tcLineIndex
	 *            int
	 */
	private void addVariable(String tcFile, String variableDecl, TC tc, int tcLineIndex) {
		if (StringUtil.getInstance().isNotEmpty(variableDecl)) {
			VariableDefinition variables = tc.getVariables();
			variableDecl = variableDecl.replaceAll(AppConstants.LINE_SEPARATOR, "").replaceAll("\t", "");
			String[] packageDeclTokens = variableDecl.split("=");

			if (variableDecl.contains("=") && packageDeclTokens.length - 1 == 1) {
				String variableName = packageDeclTokens[0];
				String variableType = packageDeclTokens[1];
				variables.put(variableName, variableType);

			} else {
				throw new IllegalArgumentException(
						String.format("%s has a invalid variableDecl at line: %d", tcFile, tcLineIndex));
			}
		} else {
			throw new IllegalArgumentException(
					String.format("%s has a invalid variableDecl at line: %d", tcFile, tcLineIndex));
		}
	}
}
