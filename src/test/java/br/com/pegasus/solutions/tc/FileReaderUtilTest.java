package br.com.pegasus.solutions.tc;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.pegasus.solutions.tc.util.FileReaderUtil;

/**
 * FileReaderUtilTest
 * 
 * @author Rafael Peres dos Santos
 *
 */
public class FileReaderUtilTest {

	@Test
	public void getLines() {

		try {
			List<String> lines = FileReaderUtil.getInstance().getLines(FileReaderUtilTest.class,
					"mainTestUnitCase.tctemplate");
			Assert.assertEquals(true, lines.size() > 0);
		} catch (Throwable e) {
			throw new AssertionError("an error ocurred while trying read mainTestUnitCase.tctemplate file");
		}
	}
}
