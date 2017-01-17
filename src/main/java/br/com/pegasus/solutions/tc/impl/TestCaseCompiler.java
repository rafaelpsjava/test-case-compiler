package br.com.pegasus.solutions.tc.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.pegasus.solutions.tc.pojo.FileToCompile;
import br.com.pegasus.solutions.tc.pojo.MainTC;
import br.com.pegasus.solutions.tc.pojo.TC;
import br.com.pegasus.solutions.tc.util.AppConstants;
import br.com.pegasus.solutions.tc.util.StringUtil;
import br.com.pegasus.solutions.tc.util.tc.impl.TCFileReader;

/**
 * TestCaseCompiler
 * 
 * @author Rafael Peres dos Santos
 *
 */
public class TestCaseCompiler {
	private static TestCaseCompiler testCaseCompiler;

	private TestCaseCompiler() {
		super();
	}

	/**
	 * getInstance
	 * 
	 * @return {@link TestCaseCompiler}
	 */
	public static TestCaseCompiler getInstance() {
		if (testCaseCompiler == null) {
			synchronized (ValidateTCFiles.class) {
				if (testCaseCompiler == null) {
					testCaseCompiler = new TestCaseCompiler();
				}
			}
		}
		return testCaseCompiler;
	}

	/**
	 * getNewIntance
	 * 
	 * @return {@link TestCaseCompiler}
	 */
	public static TestCaseCompiler getNewIntance() {
		return new TestCaseCompiler();
	}

	/**
	 * compile
	 * 
	 * get the main test case and build an MainTC instance with the content
	 * invoke the method compile with the class to get the test cases files that
	 * are in the scope of that class in other words classloader. the method
	 * will generate the code and compile the test case files that are in the
	 * path
	 * 
	 * @param clazz
	 *            {@link Class}
	 * @param path
	 */
	public void compile(Class<?> clazz, String path) {
		MainTC mainTC = TCFileReader.getInstance().getMainTC(clazz, "main.tc");
		compile(clazz, mainTC, path);
	}

	/**
	 * compile
	 * 
	 * @param clazz
	 *            {@link Class}
	 * @param mainTC
	 *            {@link MainTC}
	 * @param path
	 *            {@link String}
	 */
	private void compile(Class<?> clazz, MainTC mainTC, String path) {
		ValidateTCFiles.getInstance().validate(mainTC);
		List<FileToCompile> testCases = new ArrayList<>();

		path = String.format("%s/src/test/java", path.replace("\\", "/"));
		System.out.println(mainTC);

		for (TC tc : mainTC.getTcs()) {
			String tcFileName = StringUtil.getInstance().firstCharUpper(tc.getName()).replace(".tc", "");
			String fileName = String.format("%s.%s%s", mainTC.getBasePackage(), tcFileName,
					AppConstants.TEST_CASE_SUFIX);
			StringBuilder sourceCode = tc.generateTcImpl(mainTC);

			testCases.add(new FileToCompile(path, fileName, sourceCode));
		}

		String mainTestCaseImplFileName = String.format("%s.MainTestCaseImpl", mainTC.getBasePackage());
		testCases.add(new FileToCompile(path, mainTestCaseImplFileName, mainTC.generateMainTestCaseImpl()));

		CompilerUtil.getInstance().compile(testCases);
	}

}
