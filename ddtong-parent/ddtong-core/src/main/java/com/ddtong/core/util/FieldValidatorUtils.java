package com.ddtong.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldValidatorUtils {

	/**
	 * 校验是否手机号
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isMobile(String input) {
		Matcher matcher = Pattern.compile("^(13|14|15|17|18)[0-9]{9}").matcher(input);
		return matcher.matches();
	}

	/**
	 * 身份证合法性校验(校验格式规则)
	 * 
	 * @param idcard
	 * @return
	 */
	public static boolean idcardValidator(String idcard) {
		IdcardValidator idcardValidator = new IdcardValidator();
		return idcardValidator.isValidatedIdcard(idcard);
	}
}
