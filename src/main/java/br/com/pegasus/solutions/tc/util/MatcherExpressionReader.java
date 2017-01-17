package br.com.pegasus.solutions.tc.util;

import java.util.List;

import br.com.pegasus.solutions.tc.util.pojo.MatcherExpression;
import br.com.pegasus.solutions.tc.util.pojo.MatcherExpressionHash;
import br.com.pegasus.solutions.tc.util.pojo.MatcherExpressionType;

/**
 * MatcherExpressionReader
 * 
 * @author Rafael Peres dos Santos
 *
 */
public final class MatcherExpressionReader {

	private static final String MATCHER_EXPRESSION_CLASS_EXPRESSION_NAME = "matcherExpression/classExpression.matcher";
	private static MatcherExpressionReader matcherExpressionReader;

	private MatcherExpressionReader() {
	}

	/**
	 * getInstance
	 * 
	 * @return {@link MatcherExpressionReader}
	 */
	public static MatcherExpressionReader getInstance() {
		if (matcherExpressionReader == null) {
			synchronized (MatcherExpressionReader.class) {
				if (matcherExpressionReader == null) {
					matcherExpressionReader = new MatcherExpressionReader();
				}
			}
		}

		return matcherExpressionReader;
	}

	/**
	 * getNewInstance
	 * 
	 * @return {@link MatcherExpressionReader}
	 */
	public static MatcherExpressionReader getNewInstance() {
		return new MatcherExpressionReader();
	}

	/**
	 * getMatcherExpressions
	 * 
	 * return the default matcher expression class file
	 * 
	 * @return {@link MatcherExpressionHash}
	 */
	public MatcherExpressionHash getMatcherExpressions() {
		return getMatcherExpressions(MATCHER_EXPRESSION_CLASS_EXPRESSION_NAME);
	}

	/**
	 * getMatcherExpressions
	 * 
	 * get the lines of the file with FileReaderUtil iterate each line if the
	 * line has at the first char # replace the # with "" and get the type of
	 * matcherExpression,
	 * 
	 * if not verify if the line is not empty and get the name of matcher and
	 * get the matcherExpressionValue put it in hash
	 * 
	 * in the iteration invoke the setValue of matcherExpressionHash with the
	 * matcherExpression of the current line, this set just the
	 * matcherExpression with no matcherSubExpression
	 * 
	 * 
	 * after read the lines for all matcherExpressionHash values invoke the
	 * setValue another time to set the matcherSubExpression
	 * 
	 * @param matcherFile
	 *            {@link String}
	 * @return {@link MatcherExpressionHash}
	 */
	public MatcherExpressionHash getMatcherExpressions(String matcherFile) {
		List<String> lines = null;
		try {
			lines = FileReaderUtil.getInstance().getLines(MatcherExpressionReader.class, matcherFile);
		} catch (Throwable throwable) {
			throw new RuntimeException("An error occurred while trying reading the matcher expression file");
		}

		MatcherExpressionHash matcherExpressionHash = new MatcherExpressionHash();
		MatcherExpressionType matcherExpressionType = null;
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			MatcherExpression matcherExpression = null;
			if (line.startsWith("#")) {
				line = line.replace("#", "").trim();
				matcherExpressionType = MatcherExpressionType.getType(line);
			} else {
				if (StringUtil.getInstance().isNotEmpty(line)) {
					if (matcherExpressionType != null) {
						int eqIndex = line.indexOf("=");

						if (eqIndex > 0 && line.length() > eqIndex + 1) {
							String name = line.substring(0, eqIndex);
							String value = StringUtil.getInstance().unescapeJavaString(line.substring(eqIndex + 1));

							matcherExpression = new MatcherExpression(name, value, matcherExpressionType);
							matcherExpression.setMatcherFile(matcherFile);
							matcherExpressionHash.put(matcherExpression, matcherExpression);
						} else {
							throw new RuntimeException("the name and a value matcher expression is mandatory!");
						}
					}
				}
			}
			if (matcherExpression != null) {
				matcherExpressionHash.setValue(matcherExpression, false);
			}
		}

		for (MatcherExpression matcher : matcherExpressionHash.values()) {
			matcherExpressionHash.setValue(matcher, true);
		}

		return matcherExpressionHash;
	}

}
