package br.com.pegasus.solutions.tc.impl;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import br.com.pegasus.solutions.tc.pojo.FileToCompile;
import br.com.pegasus.solutions.tc.util.StringUtil;

/**
 * CompilerUtil
 * 
 * @author Rafael Peres dos Santos
 *
 */
public class CompilerUtil {
	private static CompilerUtil compilerUtil;

	private CompilerUtil() {
		super();
	}

	/**
	 * getInstance
	 * 
	 * @return {@link CompilerUtil}
	 */
	public static CompilerUtil getInstance() {
		if (compilerUtil == null) {
			synchronized (CompilerUtil.class) {
				if (compilerUtil == null) {
					compilerUtil = new CompilerUtil();
				}
			}
		}
		return compilerUtil;
	}

	/**
	 * getNewIntance
	 * 
	 * @return {@link CompilerUtil}
	 */
	public static CompilerUtil getNewIntance() {
		return new CompilerUtil();
	}

	/**
	 * compile
	 * 
	 * for each filesToCompile value invoke the compile with fileToCompile
	 * 
	 * 
	 * @param filesToCompile
	 *            {@link List}
	 */
	public void compile(List<FileToCompile> filesToCompile) {
		for (int i = 0; i < filesToCompile.size(); i++) {
			FileToCompile fileToCompile = filesToCompile.get(i);
			try {
				compile(fileToCompile, StringUtil.getInstance().getNullAsEmpty(filesToCompile.get(i).getFileName())
						.contains("MainTestCaseImpl"));
			} catch (Throwable throwable) {
				if (!throwable.getMessage().contains("cannot find symbol") || i != filesToCompile.size() - 1) {
					System.out.println(throwable.getMessage());
				}
			}
		}
	}

	/**
	 * compile
	 * 
	 * @param fileToCompile
	 *            {@link FileToCompile}
	 * @param isToShowError
	 *            {@link Boolean}
	 * @throws Throwable
	 */
	public void compile(FileToCompile fileToCompile, boolean isToShowError) throws Throwable {
		File root = new File(fileToCompile.getPath());
		File sourceFile = new File(root, fileToCompile.getFileName());
		sourceFile.getParentFile().mkdirs();
		Files.write(sourceFile.toPath(), fileToCompile.getSourceCode().toString().getBytes(StandardCharsets.UTF_8));

		if (fileToCompile.isSimpleCompile()) {
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			compiler.run(null, null, null, sourceFile.getPath());

		} else {
			JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
			DiagnosticCollector<JavaFileObject> diagnosticsCollector = new DiagnosticCollector<JavaFileObject>();
			StandardJavaFileManager standardJavaFileManager = null;

			try {
				standardJavaFileManager = javaCompiler.getStandardFileManager(diagnosticsCollector, null, null);
				Iterable<? extends JavaFileObject> compilationUnits = standardJavaFileManager
						.getJavaFileObjectsFromStrings(Arrays.asList(sourceFile.getPath()));
				JavaCompiler.CompilationTask compilationTask = javaCompiler.getTask(null, standardJavaFileManager,
						diagnosticsCollector, null, null, compilationUnits);
				boolean success = compilationTask.call();
				if (!success) {
					List<Diagnostic<? extends JavaFileObject>> diagnostics = diagnosticsCollector.getDiagnostics();
					for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics) {
						String errorMsg = diagnostic.getMessage(null);

						if (!errorMsg.contains("cannot find symbol") || isToShowError) {
							System.out.println(errorMsg);
						}
					}
				}
			} finally {
				if (standardJavaFileManager != null) {
					standardJavaFileManager.close();
				}
			}
		}
	}

}
