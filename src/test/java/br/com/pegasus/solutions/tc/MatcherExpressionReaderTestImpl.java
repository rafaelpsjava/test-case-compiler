package br.com.pegasus.solutions.tc;

import org.junit.Assert;
import org.junit.Test;

import br.com.pegasus.solutions.tc.util.MatcherExpressionReader;
import br.com.pegasus.solutions.tc.util.pojo.MatcherExpression;
import br.com.pegasus.solutions.tc.util.pojo.MatcherExpressionHash;
import br.com.pegasus.solutions.tc.util.pojo.MatcherExpressionType;

/**
 * MatcherExpressionReaderTestImpl
 * 
 * @author Rafael Peres dos Santos
 *
 */
public class MatcherExpressionReaderTestImpl {

	@Test
	public void getMatcherExpressions() {
		MatcherExpressionHash matcherExpressions = MatcherExpressionReader.getInstance().getMatcherExpressions();
		MatcherExpression methodInvocation = matcherExpressions
				.get(new MatcherExpression("author", MatcherExpressionType.ACCEPT_CHARS));

		Assert.assertEquals(true, "(.)*".equals(methodInvocation.getValue()));
	}

}
