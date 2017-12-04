package com.ddtong.core.util;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 使用FreeMarker
 */
public class FreeMarkerTemplateUtil {

	private static final Logger logger = LoggerFactory.getLogger(FreeMarkerTemplateUtil.class);

	private static Configuration config = null;

	private static void init() throws Exception {
		FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
		bean.setTemplateLoaderPath("templates/freemarker");
		config = bean.createConfiguration();

	}

	public static String getContent(String templateName, Object model) {
		try {
			if (config == null) {
				init();
			}

			Template template = config.getTemplate(templateName);

			StringWriter result = new StringWriter();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("model", model);

			template.process(map, result);

			String str = result.toString();

			return str;

		} catch (Exception e) {

			logger.error("freemarker process error ...... ", e);

			return "";
		}
	}
}
