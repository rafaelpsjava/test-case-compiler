package br.com.pegasus.solutions.tc.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * FileReaderUtil
 * 
 * @author Rafael Peres dos Santos
 *
 */
public final class FileReaderUtil extends IOUtil {

	private static FileReaderUtil fileReaderUtil;

	private FileReaderUtil() {
		super();
	}

	/**
	 * getInstance
	 * 
	 * @return {@link FileReaderUtil}
	 */
	public static FileReaderUtil getInstance() {
		if (fileReaderUtil == null) {
			synchronized (FileReaderUtil.class) {
				if (fileReaderUtil == null) {
					fileReaderUtil = new FileReaderUtil();
				}
			}
		}
		return fileReaderUtil;
	}

	/**
	 * getNewIntance
	 * 
	 * @return {@link FileReaderUtil}
	 */
	public static FileReaderUtil getNewIntance() {
		return new FileReaderUtil();
	}

	/**
	 * getLines
	 * 
	 * return the lines of the file as string, if isToAddLineSeparator add \n in
	 * the line before add it in a list of string that represents the content of
	 * the file
	 * 
	 * @param clazz
	 *            {@link Class}<?>
	 * @param fileName
	 *            {@link String}
	 * @return {@link List}<String>
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public List<String> getLines(Class<?> clazz, String fileName)
			throws FileNotFoundException, IOException, URISyntaxException {

		return getLines(clazz, fileName, false);
	}

	/**
	 * getLines
	 * 
	 * get the input stream of the file instantiate a buffered reader using
	 * inputstream as parameter and for each line add in the list of content
	 * 
	 * @param clazz
	 *            {@link Class}<?>
	 * @param fileName
	 *            {@link String}
	 * @param isToAddLineSeparator
	 *            boolean
	 * @return {@link List}<String>
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public List<String> getLines(Class<?> clazz, String fileName, boolean isToAddLineSeparator)
			throws FileNotFoundException, IOException, URISyntaxException {
		List<String> lines = new ArrayList<String>();

		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			inputStreamReader = new InputStreamReader(getInputStream(clazz, fileName));
			bufferedReader = new BufferedReader(inputStreamReader);

			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				if (isToAddLineSeparator) {
					line += System.getProperty("line.separator");
				}
				lines.add(line);
			}

		} finally {
			close(bufferedReader, inputStreamReader, inputStream);
		}

		return lines;
	}
}