package br.com.pegasus.solutions.tc.pojo;

/**
 * FileToCompile
 * 
 * @author Rafael Peres dos Santos
 *
 */
public class FileToCompile {

	private String path;

	private String fileName;

	private StringBuilder sourceCode;

	private boolean simpleCompile;

	public FileToCompile() {
	}

	public FileToCompile(String path) {
		this(path, null);
	}

	public FileToCompile(String path, String fileName) {
		this(path, fileName, null);
	}

	public FileToCompile(String path, String fileName, StringBuilder sourceCode) {
		this.setFileName(fileName);
		this.setPath(path);
		this.setSourceCode(sourceCode);

		if (this.fileName != null) {
			this.fileName = String.format("%s.java", fileName.replace(".", "/"));
		}
		if (this.path != null) {
			this.path = path.replace(".", "/");
		}
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public StringBuilder getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(StringBuilder sourceCode) {
		this.sourceCode = sourceCode;
	}

	public boolean isSimpleCompile() {
		return simpleCompile;
	}

	public void setSimpleCompile(boolean simpleCompile) {
		this.simpleCompile = simpleCompile;
	}

}
