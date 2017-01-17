package br.com.pegasus.solutions.tc.impl;

import br.com.pegasus.solutions.tc.pojo.MainTC;
import br.com.pegasus.solutions.tc.pojo.TC;

/**
 * GenerateTCFiles
 * 
 * @author Rafael Peres dos Santos
 *
 */
public class ValidateTCFiles {

	private static ValidateTCFiles validateTCFiles;

	private ValidateTCFiles() {
		super();
	}

	/**
	 * getInstance
	 * 
	 * @return {@link ValidateTCFiles}
	 */
	public static ValidateTCFiles getInstance() {
		if (validateTCFiles == null) {
			synchronized (ValidateTCFiles.class) {
				if (validateTCFiles == null) {
					validateTCFiles = new ValidateTCFiles();
				}
			}
		}
		return validateTCFiles;
	}

	/**
	 * getNewIntance
	 * 
	 * @return {@link ValidateTCFiles}
	 */
	public static ValidateTCFiles getNewIntance() {
		return new ValidateTCFiles();
	}

	/**
	 * validate
	 * 
	 * this is to validate the content of the main test case and the test case
	 * files
	 * 
	 * 
	 * @param mainTC
	 *            {@link MainTC}
	 */
	public void validate(MainTC mainTC) {
		// TODO to implement validate
		StringBuilder mainTestCaseImpl = mainTC.generateMainTestCaseImpl();
		System.out.println(mainTestCaseImpl);

		for (TC tc : mainTC.getTcs()) {
			StringBuilder testCaseImpl = tc.generateTcImpl(mainTC);
			System.out.println(testCaseImpl);
		}
	}

}
