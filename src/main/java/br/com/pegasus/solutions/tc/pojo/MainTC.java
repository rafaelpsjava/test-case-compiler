package br.com.pegasus.solutions.tc.pojo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.GsonBuilder;

import br.com.pegasus.solutions.tc.util.AppConstants;
import br.com.pegasus.solutions.tc.util.StringUtil;
import br.com.pegasus.solutions.tc.util.TemplateUtil;
import br.com.pegasus.solutions.tc.util.pojo.TemplateContext;

/**
 * MainTC
 * 
 * @author Rafael Peres dos Santos
 *
 */
public class MainTC {

	private String authorMainTc = "";

	private PackageDefinition packages;

	private VariableDefinition variables;

	private List<TC> tcs;

	private StringBuilder mainTestCaseImpl;

	public MainTC() {
	}

	public List<TC> getTcs() {
		if (this.tcs == null) {
			this.tcs = new ArrayList<>();
		}
		return tcs;
	}

	public void setTcs(List<TC> tc) {
		this.tcs = tc;
	}

	public String getAuthorMainTc() {
		return authorMainTc;
	}

	public void setAuthorMainTc(String authorMainTc) {
		this.authorMainTc = authorMainTc;
	}

	public PackageDefinition getPackages() {
		if (this.packages == null) {
			this.packages = new PackageDefinition();
		}
		return packages;
	}

	public void setPackages(PackageDefinition packages) {
		this.packages = packages;
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

	public String getBasePackage() {
		return getPackages().get("generatedTestCasesPackage");
	}

	/**
	 * generateMainTestCaseImpl
	 * 
	 * instantiate the templateContext put the params of the template and invoke
	 * the TemplateUtil to get the content
	 * 
	 * @return {@link StringBuilder}
	 */
	public StringBuilder generateMainTestCaseImpl() {
		if (mainTestCaseImpl == null) {
			TemplateContext templateContext = new TemplateContext();
			templateContext.put("description",
					"This is the Main Test Case this will trigger the test cases that are in SuiteClasses.");
			templateContext.put("author", authorMainTc);
			templateContext.put("suiteClasses", getSuiteClasses().toString());
			templateContext.put("imports", getImports().toString());
			templateContext.put("generatedTestCasesPackage", getBasePackage());

			mainTestCaseImpl = TemplateUtil.getInstance().getContent("mainTestUnitCase.tctemplate", templateContext);
		}

		return mainTestCaseImpl;
	}

	/**
	 * getSuiteClasses
	 * 
	 * for each test case get the name the file and add sufix TestCaseImpl.class
	 * append it in the stringbuilder and suiteClasses that is the annotations
	 * over the main test case decl impl
	 * 
	 * 
	 * @return {@link StringBuilder}
	 */
	private StringBuilder getSuiteClasses() {
		StringBuilder suiteClasses = new StringBuilder();
		StringBuilder tcClasses = new StringBuilder();
		for (int i = 0; i < getTcs().size(); i++) {
			TC tc = getTcs().get(i);
			String tcName = StringUtil.getInstance().firstCharUpper(tc.getName().split("/.")[0]).replace(".tc", "");
			tcClasses.append(String.format("%sTestCaseImpl%s", tcName, ".class"));

			if (i != getTcs().size() - 1) {
				tcClasses.append(", ");
			}
		}

		suiteClasses.append("@RunWith(Suite.class)");
		suiteClasses.append(AppConstants.LINE_SEPARATOR);
		suiteClasses.append(String.format("@SuiteClasses({ %s })", tcClasses));
		suiteClasses.append(AppConstants.LINE_SEPARATOR);

		return suiteClasses;
	}

	/**
	 * getImports
	 * 
	 * instantiate the imports and append the imports of the main test case impl
	 * 
	 * @return {@link StringBuilder}
	 */
	private StringBuilder getImports() {
		StringBuilder imports = new StringBuilder();
		imports.append("import org.junit.runner.RunWith;");
		imports.append(AppConstants.LINE_SEPARATOR);
		imports.append("import org.junit.runners.Suite.SuiteClasses;");
		imports.append(AppConstants.LINE_SEPARATOR);
		imports.append("import org.junit.runners.Suite;");
		imports.append(AppConstants.LINE_SEPARATOR);

		return imports;
	}

	/**
	 * return to string as json
	 */
	@Override
	public String toString() {
		return new GsonBuilder().setPrettyPrinting().create().toJson(this);
	}
}
