package br.com.pegasus.solutions.tc;

import java.util.List;

import org.junit.Test;

import br.com.pegasus.solutions.tc.abstractions.AbstractMatcherExpressionTest;
import br.com.pegasus.solutions.tc.abstractions.TestDetail;
import br.com.pegasus.solutions.tc.util.pojo.MatcherExpression;
import br.com.pegasus.solutions.tc.util.pojo.MatcherExpressionType;

/**
 * MatcherExpressionTestImpl
 * 
 * @author Rafael Peres dos Santos
 *
 */
public class MatcherExpressionTestImpl extends AbstractMatcherExpressionTest {

	@Test
	public void methodInvocationDeclImpl() {
		System.out.println("methodInvocationDeclImpl");
		String methodInvocationDecl = matcherExpressionHash
				.get(new MatcherExpression("methodInvocationDecl", MatcherExpressionType.METHODS_IMPL)).getValue();
		List<TestDetail> testDetailList = methodInvocationDeclTestHash.get("methodInvocationDecl");

		testDetails(methodInvocationDecl, testDetailList);
		System.out.println("-------------------------------------");
	}

	@Test
	public void classDeclImpl() {
		System.out.println("classDeclImpl");
		String methodInvocationDecl = matcherExpressionHash
				.get(new MatcherExpression("classDecl", MatcherExpressionType.CLASS)).getValue();
		List<TestDetail> testDetailList = methodInvocationDeclTestHash.get("classDecl");

		testDetails(methodInvocationDecl, testDetailList);
		System.out.println("-------------------------------------");
	}

	@Test
	public void methodDeclImpl() {
		System.out.println("methodDeclImpl");
		String methodInvocationDecl = matcherExpressionHash
				.get(new MatcherExpression("methodDecl", MatcherExpressionType.METHODS_IMPL)).getValue();
		List<TestDetail> testDetailList = methodInvocationDeclTestHash.get("methodDecl");

		testDetails(methodInvocationDecl, testDetailList);
		System.out.println("-------------------------------------");
	}

	@Test
	public void paramMethodDeclImpl() {
		System.out.println("paramMethodDeclImpl");
		String methodInvocationDecl = matcherExpressionHash
				.get(new MatcherExpression("paramMethodDecl", MatcherExpressionType.METHODS_IMPL)).getValue();
		List<TestDetail> testDetailList = methodInvocationDeclTestHash.get("paramMethodDecl");

		testDetails(methodInvocationDecl, testDetailList);
		System.out.println("-------------------------------------");
	}

	@Test
	public void paramMethodImpl() {
		System.out.println("paramMethod");
		String methodInvocationDecl = matcherExpressionHash
				.get(new MatcherExpression("paramMethod", MatcherExpressionType.METHODS_IMPL)).getValue();
		List<TestDetail> testDetailList = methodInvocationDeclTestHash.get("paramMethod");

		testDetails(methodInvocationDecl, testDetailList);
		System.out.println("-------------------------------------");
	}

	@Test
	public void expressionConnectorsImpl() {
		System.out.println("paramMethod");
		String methodInvocationDecl = matcherExpressionHash
				.get(new MatcherExpression("expressionConnectors", MatcherExpressionType.METHODS_IMPL)).getValue();
		List<TestDetail> testDetailList = methodInvocationDeclTestHash.get("expressionConnectors");

		testDetails(methodInvocationDecl, testDetailList);
		System.out.println("-------------------------------------");
	}

	@Test
	public void mathExpressionImpl() {
		System.out.println("mathExpressionImpl");
		String methodInvocationDecl = matcherExpressionHash
				.get(new MatcherExpression("mathExpression", MatcherExpressionType.METHODS_IMPL)).getValue();
		List<TestDetail> testDetailList = methodInvocationDeclTestHash.get("mathExpression");

		testDetails(methodInvocationDecl, testDetailList);
		System.out.println("-------------------------------------");
	}

	@Test
	public void packageDeclImpl() {
		System.out.println("packageDeclImpl");
		String methodInvocationDecl = matcherExpressionHash
				.get(new MatcherExpression("packageDecl", MatcherExpressionType.CLASS)).getValue();
		List<TestDetail> testDetailList = methodInvocationDeclTestHash.get("packageDecl");

		testDetails(methodInvocationDecl, testDetailList);
		System.out.println("-------------------------------------");
	}

	@Test
	public void classNameImpl() {
		System.out.println("classNameImpl");
		String methodInvocationDecl = matcherExpressionHash
				.get(new MatcherExpression("className", MatcherExpressionType.CLASS)).getValue();
		List<TestDetail> testDetailList = methodInvocationDeclTestHash.get("className");

		testDetails(methodInvocationDecl, testDetailList);
		System.out.println("-------------------------------------");
	}

	@Test
	public void importDeclImpl() {
		System.out.println("importDecl");
		String methodInvocationDecl = matcherExpressionHash
				.get(new MatcherExpression("importDecl", MatcherExpressionType.CLASS)).getValue();
		List<TestDetail> testDetailList = methodInvocationDeclTestHash.get("importDecl");

		testDetails(methodInvocationDecl, testDetailList);
		System.out.println("-------------------------------------");
	}

	@Test
	public void authorImpl() {
		System.out.println("authorImpl");
		String methodInvocationDecl = matcherExpressionHash
				.get(new MatcherExpression("author", MatcherExpressionType.ACCEPT_CHARS)).getValue();
		List<TestDetail> testDetailList = methodInvocationDeclTestHash.get("author");

		testDetails(methodInvocationDecl, testDetailList);
		System.out.println("-------------------------------------");
	}

	@Test
	public void descriptionImpl() {
		System.out.println("descriptionImpl");
		String methodInvocationDecl = matcherExpressionHash
				.get(new MatcherExpression("description", MatcherExpressionType.ACCEPT_CHARS)).getValue();
		List<TestDetail> testDetailList = methodInvocationDeclTestHash.get("description");

		testDetails(methodInvocationDecl, testDetailList);
		System.out.println("-------------------------------------");
	}

	@Test
	public void stringTypeImpl() {
		System.out.println("stringTypeImpl");
		String methodInvocationDecl = matcherExpressionHash
				.get(new MatcherExpression("stringType", MatcherExpressionType.TYPES)).getValue();
		List<TestDetail> testDetailList = methodInvocationDeclTestHash.get("stringType");

		testDetails(methodInvocationDecl, testDetailList);
		System.out.println("-------------------------------------");
	}

	@Test
	public void booleanExpressionImpl() {
		System.out.println("booleanExpressionImpl");
		String methodInvocationDecl = matcherExpressionHash
				.get(new MatcherExpression("booleanExpression", MatcherExpressionType.METHODS_IMPL)).getValue();
		List<TestDetail> testDetailList = methodInvocationDeclTestHash.get("booleanExpression");

		testDetails(methodInvocationDecl, testDetailList);
		System.out.println("-------------------------------------");
	}

	@Test
	public void booleanExpressionAcceptTypeImpl() {
		System.out.println("booleanExpressionAcceptTypeImpl");
		String methodInvocationDecl = matcherExpressionHash
				.get(new MatcherExpression("booleanExpressionAcceptType", MatcherExpressionType.METHODS_IMPL))
				.getValue();
		List<TestDetail> testDetailList = methodInvocationDeclTestHash.get("booleanExpressionAcceptType");

		testDetails(methodInvocationDecl, testDetailList);
		System.out.println("-------------------------------------");
	}

	@Test
	public void booleanExpressionTypeImpl() {
		System.out.println("booleanExpressionTypeImpl");
		String methodInvocationDecl = matcherExpressionHash
				.get(new MatcherExpression("booleanExpressionType", MatcherExpressionType.METHODS_IMPL)).getValue();
		List<TestDetail> testDetailList = methodInvocationDeclTestHash.get("booleanExpressionType");

		testDetails(methodInvocationDecl, testDetailList);
		System.out.println("-------------------------------------");
	}

	@Test
	public void integerTypeImpl() {
		System.out.println("integerTypeImpl");
		String methodInvocationDecl = matcherExpressionHash
				.get(new MatcherExpression("integerType", MatcherExpressionType.TYPES)).getValue();
		List<TestDetail> testDetailList = methodInvocationDeclTestHash.get("integerType");

		testDetails(methodInvocationDecl, testDetailList);
		System.out.println("-------------------------------------");
	}
}
