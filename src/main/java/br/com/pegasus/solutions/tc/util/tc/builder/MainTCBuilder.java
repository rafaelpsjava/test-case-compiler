package br.com.pegasus.solutions.tc.util.tc.builder;

import br.com.pegasus.solutions.tc.pojo.MainTC;
import br.com.pegasus.solutions.tc.pojo.PackageDefinition;
import br.com.pegasus.solutions.tc.pojo.VariableDefinition;
import br.com.pegasus.solutions.tc.util.StringUtil;

/**
 * MainTCBuilder
 * 
 * @author Rafael Peres dos Santos
 *
 */
public class MainTCBuilder {

	private static MainTCBuilder mainTCBuilder;

	private MainTCBuilder() {
	}

	/**
	 * getInstance
	 * 
	 * @return {@link MainTCBuilder}
	 */
	public static MainTCBuilder getInstance() {
		if (mainTCBuilder == null) {
			synchronized (MainTCBuilder.class) {
				if (mainTCBuilder == null) {
					mainTCBuilder = new MainTCBuilder();
				}
			}
		}

		return mainTCBuilder;
	}

	/**
	 * getNewIntance
	 * 
	 * @return {@link MainTCBuilder}
	 */
	public static MainTCBuilder getNewIntance() {
		return new MainTCBuilder();
	}

	/**
	 * addVariableDefinition
	 * 
	 * get the variables and add the variable
	 * 
	 * @param lines
	 *            {@link String}
	 * @param mainTC
	 *            {@link MainTC}
	 * @param lineIndex
	 *            int
	 */
	public void addVariableDefinition(String variableDecl, MainTC mainTC, int lineIndex) {
		if (StringUtil.getInstance().isNotEmpty(variableDecl)) {
			VariableDefinition variables = mainTC.getVariables();
			String[] packageDeclTokens = variableDecl.split("=");

			if (variableDecl.contains("=") && packageDeclTokens.length - 1 == 1) {
				String variableName = packageDeclTokens[0];
				String variableType = packageDeclTokens[1].replaceAll("\n", "");
				variables.put(variableName, variableType);

			} else {
				throw new IllegalArgumentException("invalid variableDecl at line: " + lineIndex);
			}
		} else {
			throw new IllegalArgumentException("invalid variableDecl at line: " + lineIndex);
		}
	}

	/**
	 * addPackageDecl
	 * 
	 * get the packagedefinition instance and add packageVariable
	 * 
	 * @param line
	 *            {@link String}
	 * @param mainTC
	 *            {@link MainTC}
	 * @param lineIndex
	 *            int
	 */
	public void addPackageDecl(String packageDecl, MainTC mainTC, int lineIndex) {
		if (StringUtil.getInstance().isNotEmpty(packageDecl)) {
			PackageDefinition packages = mainTC.getPackages();
			String[] packageDeclTokens = packageDecl.split("=");

			if (packageDecl.contains("=") && packageDeclTokens.length - 1 == 1) {

				String packageVariable = packageDeclTokens[0];
				String packageDirectory = packageDeclTokens[1].replaceAll("\n", "");
				packages.put(packageVariable, packageDirectory);

			} else {
				throw new IllegalArgumentException("invalid packageDecl at line: " + lineIndex);
			}
		} else {
			throw new IllegalArgumentException("invalid packageDecl at line: " + lineIndex);
		}
	}

}
