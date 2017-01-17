package br.com.pegasus.solutions.tc.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * IOUtil
 * 
 * @author Rafael Peres dos Santos
 *
 */
public class IOUtil {

	protected IOUtil() {
	}

	/**
	 * close
	 * 
	 * close all classes that is a Closeable invoking the close method
	 * 
	 * @param closeables
	 *            {@link Closeable}...
	 */
	public void close(Closeable... closeables) {
		if (closeables != null) {
			for (Closeable closeable : closeables) {
				try {
					if (closeable != null) {
						closeable.close();
					}
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * getInputStream
	 * 
	 * replace the \n in the fileName if has any and get the inputstream of the
	 * file using the getResourceAsStream using the context of the clazz if is
	 * null try another again to get the inputstream of the file using the
	 * newInputStream method of Files class
	 * 
	 * @param clazz
	 *            {@link Class}
	 * @param fileName
	 *            {@link String}
	 * @return {@link InputStream}
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public InputStream getInputStream(Class<?> clazz, String fileName) throws URISyntaxException, IOException {
		fileName = fileName.replaceAll(System.getProperty("line.separator"), "");
		InputStream inputStream = null;
		try {
			inputStream = clazz.getClassLoader().getResourceAsStream(fileName);
		} catch (Throwable throwable) {
		}
		if (inputStream == null) {
			close(inputStream);
			try {
				Path path = Paths.get(getClass().getResource(fileName).toURI());
				inputStream = Files.newInputStream(path, StandardOpenOption.READ);
			} catch (Throwable throwable) {
			}
		}
		return inputStream;
	}
}
