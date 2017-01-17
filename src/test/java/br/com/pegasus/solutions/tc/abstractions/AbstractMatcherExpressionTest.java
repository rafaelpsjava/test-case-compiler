package br.com.pegasus.solutions.tc.abstractions;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import br.com.pegasus.solutions.tc.util.MatcherExpressionReader;
import br.com.pegasus.solutions.tc.util.pojo.MatcherExpressionHash;

/**
 * AbstractMatcherExpressionTest this is not good aproach have a big method but
 * this is just for loading some test detail for matcher expression...
 * 
 * @author Rafael Peres dos Santos
 *
 */
public abstract class AbstractMatcherExpressionTest {

	protected static MatcherExpressionHash matcherExpressionHash;

	protected static TestHash methodInvocationDeclTestHash = new TestHash();

	public AbstractMatcherExpressionTest() {
		if (matcherExpressionHash == null) {
			matcherExpressionHash = MatcherExpressionReader.getInstance().getMatcherExpressions();

			List<TestDetail> testDetailMethodInvocationList = new ArrayList<>();
			testDetailMethodInvocationList.add(getTestDetail("a.method()", Expected.TRUE));
			testDetailMethodInvocationList.add(getTestDetail("a.method( a)", Expected.TRUE));
			testDetailMethodInvocationList.add(getTestDetail("a.method( a )", Expected.TRUE));
			testDetailMethodInvocationList.add(getTestDetail("a.method(   a   )", Expected.TRUE));
			testDetailMethodInvocationList.add(getTestDetail("a.method(a,a,a)", Expected.TRUE));
			testDetailMethodInvocationList.add(getTestDetail("a.method(a , a , a)", Expected.TRUE));
			testDetailMethodInvocationList.add(getTestDetail("a.method( a, a, a )", Expected.TRUE));
			testDetailMethodInvocationList.add(getTestDetail("a.method( a   ,    a    ,   a   )", Expected.TRUE));
			testDetailMethodInvocationList.add(getTestDetail("a.method( a , a , a )", Expected.TRUE));
			methodInvocationDeclTestHash.put("methodInvocationDecl", testDetailMethodInvocationList);

			List<TestDetail> testMethodDeclList = new ArrayList<>();
			testMethodDeclList.add(getTestDetail("method(Method method)", Expected.TRUE));
			testMethodDeclList.add(getTestDetail("method( Method method )", Expected.TRUE));
			testMethodDeclList.add(getTestDetail("method(      Method   method )", Expected.TRUE));
			testMethodDeclList.add(getTestDetail("method( MI mI, MXI mXI, MXIIIII mXIIIII )", Expected.TRUE));
			methodInvocationDeclTestHash.put("methodDecl", testMethodDeclList);

			List<TestDetail> testBooleanExpressionImplList = new ArrayList<>();
			testBooleanExpressionImplList.add(getTestDetail("expression > expres", Expected.TRUE));
			methodInvocationDeclTestHash.put("booleanExpression", testBooleanExpressionImplList);

			List<TestDetail> testParamMethodImplList = new ArrayList<>();
			testParamMethodImplList.add(getTestDetail("methodI methodI", Expected.TRUE));
			testParamMethodImplList.add(getTestDetail("methodI methodI,methodI methodI", Expected.TRUE));
			testParamMethodImplList.add(getTestDetail(
					"methodI methodI,methodI methodI,methodI methodI, methodI methodI, methodI methodI, methodI methodI",
					Expected.TRUE));
			testParamMethodImplList.add(getTestDetail("method ", Expected.FALSE));
			methodInvocationDeclTestHash.put("paramMethodDecl", testParamMethodImplList);

			List<TestDetail> testParamMethodList = new ArrayList<>();
			testParamMethodList.add(getTestDetail("methodI", Expected.TRUE));
			testParamMethodList.add(
					getTestDetail("methodI,methodI, methodI,methodI,methodI, methodI,methodI,methodI", Expected.TRUE));
			testParamMethodList.add(getTestDetail("methodI,methodI,methodI,methodI", Expected.TRUE));
			methodInvocationDeclTestHash.put("paramMethod", testParamMethodList);

			List<TestDetail> testExpressionConnectorsList = new ArrayList<>();
			addTestDetails(testExpressionConnectorsList, Expected.TRUE, " and ", " or ", " AND ", " OR ", "    and   ");
			testExpressionConnectorsList.add(getTestDetail("       ", Expected.FALSE));
			methodInvocationDeclTestHash.put("expressionConnectors", testExpressionConnectorsList);

			List<TestDetail> testClassDecl = new ArrayList<>();
			testClassDecl.add(getTestDetail("public class SOMENAME {", Expected.TRUE));
			testClassDecl.add(getTestDetail("   public     class      SOMENAME {    ", Expected.TRUE));
			testClassDecl.add(getTestDetail("public    class  SOMENAME { ", Expected.TRUE));
			testClassDecl.add(getTestDetail("public abstract class SOMENAME {", Expected.TRUE));
			methodInvocationDeclTestHash.put("classDecl", testClassDecl);

			List<TestDetail> testMathExpression = new ArrayList<>();
			addTestDetails(testMathExpression, Expected.TRUE, "==", ">=", ">", "<", "<=");
			testMathExpression.add(getTestDetail("=<", Expected.FALSE));
			methodInvocationDeclTestHash.put("mathExpression", testMathExpression);

			List<TestDetail> testPackageDecl = new ArrayList<>();
			testPackageDecl.add(getTestDetail("package a.a.a.a.a.a.a.a.a.a.a.a.a.a;", Expected.TRUE));
			testPackageDecl.add(getTestDetail("package a;", Expected.TRUE));
			testPackageDecl.add(getTestDetail("package a1;", Expected.TRUE));
			methodInvocationDeclTestHash.put("packageDecl", testPackageDecl);

			List<TestDetail> testClassName = new ArrayList<>();
			testClassName.add(getTestDetail("SOMECLASSNAME", Expected.TRUE));
			methodInvocationDeclTestHash.put("className", testClassName);

			List<TestDetail> testImportDecl = new ArrayList<>();
			testImportDecl.add(getTestDetail("import a.a.a.a.a.a.a.a.a.a.a.a.a.a.A;", Expected.TRUE));
			testImportDecl.add(getTestDetail("import a.a.a.a.a.a.a.a.a.a.a.a.a.A;", Expected.TRUE));
			testImportDecl.add(getTestDetail("import a.a.A;", Expected.TRUE));
			methodInvocationDeclTestHash.put("importDecl", testImportDecl);

			List<TestDetail> testAuthor = new ArrayList<>();
			addTestDetails(testAuthor, Expected.TRUE, "author");
			methodInvocationDeclTestHash.put("author", testAuthor);

			List<TestDetail> testDescription = new ArrayList<>();
			testDescription.add(getTestDetail("description", Expected.TRUE));
			methodInvocationDeclTestHash.put("description", testDescription);

			List<TestDetail> tesStringType = new ArrayList<>();
			tesStringType.add(getTestDetail("\"sometext\"\"\"", Expected.TRUE));
			tesStringType.add(getTestDetail("\"", Expected.FALSE));
			methodInvocationDeclTestHash.put("stringType", tesStringType);

			List<TestDetail> testBooleanExpression = new ArrayList<>();
			addTestDetails(testBooleanExpression, Expected.TRUE, "a > b and b > c", "a > b", "1 > 3", "1 > -1");
			testBooleanExpression.add(getTestDetail("var.method() == var.method()", Expected.TRUE));
			methodInvocationDeclTestHash.put("booleanExpression", testBooleanExpression);

			List<TestDetail> testBooleanExpressionAcceptType = new ArrayList<>();
			addTestDetails(testBooleanExpressionAcceptType, Expected.TRUE, "a", "1", "1.0");
			methodInvocationDeclTestHash.put("booleanExpressionAcceptType", testBooleanExpressionAcceptType);

			List<TestDetail> testBooleanExpressionType = new ArrayList<>();
			addTestDetails(testBooleanExpressionType, Expected.TRUE, "a > b");
			methodInvocationDeclTestHash.put("booleanExpressionType", testBooleanExpressionType);

			List<TestDetail> testIntegerType = new ArrayList<>();
			addTestDetails(testIntegerType, Expected.TRUE, "+1", "-1", "1");
			methodInvocationDeclTestHash.put("integerType", testIntegerType);
		}
	}

	private TestDetail getTestDetail(String value, Expected expected) {
		TestDetail testDetailInstance = new TestDetail(value, expected);
		return testDetailInstance;
	}

	private void addTestDetails(List<TestDetail> testDetail, Expected expected, String... values) {
		if (testDetail != null) {
			if (values != null) {
				for (String value : values) {
					testDetail.add(getTestDetail(value, expected));
				}
			}
		}
	}

	protected void testDetails(String methodInvocationDecl, List<TestDetail> testDetailList) {
		for (TestDetail testDetail : testDetailList) {
			System.out.println(getBooleanAsString(testDetail.getValue().matches(methodInvocationDecl)) + ": "
					+ testDetail.getValue());

			Assert.assertEquals(testDetail.getExpected().valueAsBoolean(),
					testDetail.getValue().matches(methodInvocationDecl));
		}
	}

	private String getBooleanAsString(boolean booleanValue) {
		return booleanValue ? "True\t" : "False\t";
	}
}
