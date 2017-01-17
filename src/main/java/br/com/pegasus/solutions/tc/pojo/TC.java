package br.com.pegasus.solutions.tc.pojo;

import java.util.ArrayList;
import java.util.List;

import br.com.pegasus.solutions.tc.pojo.expression.Expression;
import br.com.pegasus.solutions.tc.pojo.expression.FailExpresssion;
import br.com.pegasus.solutions.tc.pojo.expression.SuccessExpression;
import br.com.pegasus.solutions.tc.util.AppConstants;
import br.com.pegasus.solutions.tc.util.StringUtil;
import br.com.pegasus.solutions.tc.util.TemplateUtil;
import br.com.pegasus.solutions.tc.util.pojo.TemplateContext;

/**
 * TC
 * 
 * @author Rafael Peres dos Santos
 *
 */
public class TC {

	private String name;

	private String description;

	private String author;

	private VariableDefinition variables;

	private List<SuccessExpression> successExpressions;

	private List<FailExpresssion> failExpressions;

	private StringBuilder tcImpl;

	public TC() {
	}

	public TC(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public VariableDefinition getVariables() {
		if (this.variables == null) {
			this.variables = new VariableDefinition();
		}
		return variables;
	}

	public void setVariables(VariableDefinition variables) {
		this.variables = variables;
	}

	public List<SuccessExpression> getSuccessExpressions() {
		if (this.successExpressions == null) {
			this.successExpressions = new ArrayList<>();
		}
		return successExpressions;
	}

	public void setSuccessExpressions(List<SuccessExpression> successExpressions) {
		this.successExpressions = successExpressions;
	}

	public List<FailExpresssion> getFailExpressions() {
		if (failExpressions == null) {
			this.failExpressions = new ArrayList<>();
		}
		return failExpressions;
	}

	public void setFailExpressions(List<FailExpresssion> failExpressions) {
		this.failExpressions = failExpressions;
	}

	/**
	 * generateTcImpl
	 * 
	 * @param mainTC
	 *            {@link MainTC}
	 * @return {@link StringBuilder}
	 */
	public StringBuilder generateTcImpl(MainTC mainTC) {
		if (tcImpl == null) {
			String name = StringUtil.getInstance().firstCharUpper(getName().split("/.")[0]).replace(".tc", "");
			String description = getDescription();
			String author = getAuthor();

			TemplateContext templateContext = new TemplateContext();
			templateContext.put("testCaseName", String.format("%s%s", name, "TestCaseImpl"));
			templateContext.put("description", description);
			templateContext.put("author", author);
			templateContext.put("generatedTestCasesPackage", mainTC.getBasePackage());
			templateContext.put("imports", generateImports(mainTC).toString());
			templateContext.put("methodsImpl", generateMethodsImpl().toString());

			tcImpl = TemplateUtil.getInstance().getContent("testUnitCase.tctemplate", templateContext);
		}

		return tcImpl;
	}

	/**
	 * generateMethodsImpl
	 * 
	 * @return {@link StringBuilder}
	 */
	private StringBuilder generateMethodsImpl() {
		StringBuilder methodsImpl = new StringBuilder();

		for (SuccessExpression successExpression : getSuccessExpressions()) {
			generateMethodImpl(methodsImpl, successExpression);
		}

		for (FailExpresssion failExpression : getFailExpressions()) {
			generateMethodImpl(methodsImpl, failExpression);
		}

		return methodsImpl;
	}

	/**
	 * generateMethodImpl
	 * 
	 * @param methodsImpl
	 *            {@link StringBuilder}
	 * @param expression
	 *            {@link Expression}
	 */
	private void generateMethodImpl(StringBuilder methodsImpl, Expression expression) {
		String successMethodName = expression.getName();
		methodsImpl.append(String.format("\t@Test%s", AppConstants.LINE_SEPARATOR));
		methodsImpl.append(String.format("\tpublic void %s() { %s", successMethodName, AppConstants.LINE_SEPARATOR));
		StringBuilder methodContentImpl = new StringBuilder(AppConstants.LINE_SEPARATOR);
		// TODO to impl
		methodsImpl.append(methodContentImpl);
		methodsImpl.append(String.format("\t}%s%s", AppConstants.LINE_SEPARATOR, AppConstants.LINE_SEPARATOR));
	}

	private StringBuilder generateImports(MainTC mainTC) {
		PackageDefinition mainTCPackages = mainTC.getPackages();
		VariableDefinition mainTcVariables = mainTC.getVariables();
		StringBuilder imports = new StringBuilder();
		imports.append("import org.junit.Assert;");
		imports.append(AppConstants.LINE_SEPARATOR);
		imports.append("import org.junit.Test;");
		imports.append(AppConstants.LINE_SEPARATOR);

		for (String tcVariable : getVariables().keySet()) {
			for (String mainTcVariable : mainTcVariables.keySet()) {
				if (getVariables().get(tcVariable).startsWith(mainTcVariable + ".")) {

					String importString = mainTcVariables.get(mainTcVariable);
					for (String mainTCPackage : mainTCPackages.keySet()) {
						importString = importString.replace(String.format("${%s}", mainTCPackage),
								mainTCPackages.get(mainTCPackage));
					}

					StringBuilder newImport = new StringBuilder();
					newImport.append("import ");
					newImport.append(importString);
					newImport.append(";");
					newImport.append(AppConstants.LINE_SEPARATOR);

					if (!imports.toString().contains(newImport)) {
						imports.append(newImport.toString());
					}
				}
			}
		}
		return imports;
	}

}
