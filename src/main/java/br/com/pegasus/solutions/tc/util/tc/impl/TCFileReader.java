package br.com.pegasus.solutions.tc.util.tc.impl;

import java.util.List;

import br.com.pegasus.solutions.tc.pojo.MainTC;
import br.com.pegasus.solutions.tc.util.AppConstants;
import br.com.pegasus.solutions.tc.util.FileReaderUtil;
import br.com.pegasus.solutions.tc.util.StringUtil;
import br.com.pegasus.solutions.tc.util.tc.builder.MainTCBuilder;
import br.com.pegasus.solutions.tc.util.tc.builder.TCBuilder;

/**
 * TCFileReader
 * 
 * @author Rafael Peres dos Santos
 *
 */
public class TCFileReader {
	private static final int TC = 3;
	private static final int VARIABLE = 2;
	private static final int PACKAGE = 1;
	private static TCFileReader tcFileReader;

	/**
	 * getInstance
	 * 
	 * @return {@link TCFileReader}
	 */
	public static TCFileReader getInstance() {
		if (tcFileReader == null) {
			synchronized (TCFileReader.class) {
				if (tcFileReader == null) {

					tcFileReader = new TCFileReader();
				}
			}
		}

		return tcFileReader;
	}

	/**
	 * getNewInstance
	 * 
	 * @return {@link TCFileReader}
	 */
	public static TCFileReader getNewInstance() {
		return new TCFileReader();
	}

	/**
	 * getMainTC
	 * 
	 * for each line of main.tc file check if the lineIndex == 0 if true set
	 * authorMainTc
	 * 
	 * if not check if the line is not empty
	 * 
	 * check if the line content contains # that is used to indicate that the
	 * line is just some info to indicate the type of the content of the bellows
	 * lines if the line
	 * 
	 * check if the type of line is equal to some implemented types invoke the
	 * spefic method based in the type that are implemented
	 * 
	 * 
	 * @param clazz
	 *            {@link Class}
	 * @param tcPath
	 *            {@link String}
	 * @return {@link MainTC}
	 */
	public MainTC getMainTC(Class<?> clazz, String tcPath) {
		List<String> lines;
		try {
			lines = FileReaderUtil.getInstance().getLines(clazz, String.format(AppConstants.TC_BASE_PATH, tcPath));
		} catch (Throwable e) {
			throw new RuntimeException(
					String.format("An error occurred while trying read %s : %s", tcPath, e.getMessage()));
		}

		MainTC mainTC = new MainTC();
		int count = 0;
		for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
			String line = lines.get(lineIndex).replaceAll(AppConstants.LINE_SEPARATOR, "").replace("\t", "");
			lines.set(lineIndex, line);

			if (lineIndex == 0) {
				mainTC.setAuthorMainTc(lines.get(lineIndex).replaceAll("[#]", "").trim());

			} else {

				if (StringUtil.getInstance().isNotEmpty(line)) {

					if (line.contains("#")) {
						count++;
					} else {
						if (count == PACKAGE) {
							MainTCBuilder.getInstance().addPackageDecl(line, mainTC, lineIndex);

						} else if (count == VARIABLE) {
							MainTCBuilder.getInstance().addVariableDefinition(line, mainTC, lineIndex);

						} else if (count == TC) {
							TCBuilder.getInstance().addTC(clazz, lines, mainTC, lineIndex);

						}
					}
				}
			}
		}

		if (StringUtil.getInstance().isEmpty(mainTC.getBasePackage())) {
			throw new RuntimeException("generatedTestCasesPackage cannot be null or empty!");
		}

		return mainTC;
	}

}
