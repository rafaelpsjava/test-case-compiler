package br.com.pegasus.solutions.tc.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import br.com.pegasus.solutions.tc.util.pojo.TemplateContext;

/**
 * TemplateUtil
 * 
 * @author Rafael Peres dos Santos
 *
 */
public class TemplateUtil {

	private static TemplateUtil templateUtil;

	private TemplateUtil() {
		super();
	}

	/**
	 * getInstance
	 * 
	 * @return {@link TemplateUtil}
	 */
	public static TemplateUtil getInstance() {
		if (templateUtil == null) {
			synchronized (TemplateUtil.class) {
				if (templateUtil == null) {
					templateUtil = new TemplateUtil();
				}
			}
		}
		return templateUtil;
	}

	/**
	 * getNewIntance
	 * 
	 * @return {@link TemplateUtil}
	 */
	public static TemplateUtil getNewIntance() {
		return new TemplateUtil();
	}

	/**
	 * getContent
	 * 
	 * get the lines of the templateName and replace the params of template that
	 * uses ${param} with the values of the templateContext, if the line does't
	 * contains \n add it. append the line in linesStringBuilder.
	 * 
	 * @param templateName
	 *            {@link String}
	 * @param templateContext
	 *            {@link TemplateContext}
	 * @return {@link StringBuilder}
	 */
	public StringBuilder getContent(String templateName, TemplateContext templateContext) {
		if (StringUtil.getInstance().isEmpty(templateName)) {
			throw new IllegalArgumentException("templateName cannot be null!");
		}

		List<String> lines = null;
		try {
			lines = FileReaderUtil.getInstance().getLines(TemplateUtil.class, templateName);
		} catch (IOException | URISyntaxException e) {
			throw new RuntimeException(
					String.format("An error occured while trying reading the template: %s", templateName));
		}
		StringBuilder linesStringBuilder = new StringBuilder();
		if (lines != null) {
			for (int i = 0; i < lines.size(); i++) {
				String line = lines.get(i);

				if (StringUtil.getInstance().isNotEmpty(line)) {
					for (String k : templateContext.keySet()) {
						line = line.replace(k, templateContext.get(k));
					}

					if (!line.contains(AppConstants.LINE_SEPARATOR)) {
						line += AppConstants.LINE_SEPARATOR;
					}
				}

				linesStringBuilder.append(line);
			}
		}

		return linesStringBuilder;
	}
}
