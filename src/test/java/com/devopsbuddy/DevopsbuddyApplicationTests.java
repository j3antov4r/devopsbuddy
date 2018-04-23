package com.devopsbuddy;

import com.devopsbuddy.utils.MD5Util;
import com.devopsbuddy.web.i18n.I18NService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.security.provider.MD5;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DevopsbuddyApplicationTests {

	@Autowired
	I18NService i18NService;



	@Test
	public void testMessageByLocaleService() {
		String expectedResult = "Bootstrap starter template";
		String messageId = "index.main.callout";
		String actual = i18NService.getMessage(messageId);
		Assert.assertEquals("The actual and expected Strings doesn't match", expectedResult, actual);
	}

	@Test
	public void testMD5gravatar() {
		String email= "jeantovar@gmail.com";
		String hash= MD5Util.md5Hex(email);
		System.out.println("jean MD5 => "+ hash);
	}
}
