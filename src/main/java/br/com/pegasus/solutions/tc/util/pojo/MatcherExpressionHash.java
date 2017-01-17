package br.com.pegasus.solutions.tc.util.pojo;

import java.util.LinkedHashMap;

import br.com.pegasus.solutions.tc.util.StringUtil;

/**
 * MatcherExpressionHash
 * 
 * @author Rafael Peres dos Santos
 *
 */
@SuppressWarnings("serial")
public final class MatcherExpressionHash extends LinkedHashMap<MatcherExpression, MatcherExpression> {

	public void setValue(MatcherExpression matcherExpression,
			boolean hasInitializedJustTheMatcherExpressionWithoutSubExpression) {

		if (matcherExpression != null) {
			String valueString = matcherExpression.getValue();
			String matcherFile = matcherExpression.getMatcherFile();

			if (StringUtil.getInstance().isNotEmpty(valueString)) {
				boolean hasSubExpression = false;
				if (!hasInitializedJustTheMatcherExpressionWithoutSubExpression) {
					for (MatcherExpression matcherSubExpression : values()) {
						String matcherSubExpressionValue = matcherSubExpression.getValue();

						if (matcherFile.equals(matcherSubExpression.getMatcherFile())) {
							if (valueString.contains(matcherSubExpressionValue)) {
								hasSubExpression = true;
								break;
							}
						}
					}
				}

				if (!hasSubExpression || hasInitializedJustTheMatcherExpressionWithoutSubExpression) {
					for (MatcherExpression matcher : values()) {
						if (matcherFile.equals(matcher.getMatcherFile())) {
							String matcherName = String.format("${%s}", matcher.getName());
							String matcherValue = matcher.getValue();

							if (valueString.contains(matcherName)) {
								valueString = valueString.replace(matcherName, matcherValue);
							}
						}
					}
				}

				matcherExpression.setValue(valueString);
			}
		}
	}
}
