package com.realtime.yaml.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class YamlTest {

	@Test
	public void testLoadFromDoc() {

		Yaml yaml = new Yaml(new Constructor(Contact.class));
		MyContactList contact = yaml.loadAs(YamlTest.class.getResourceAsStream("/contact.yml"), MyContactList.class);
		contact.getContact().stream().forEach(System.out::println);
		assertEquals("pradeep", contact.getContact().get(0).getName());
		assertEquals(4, contact.getContact().size());
	}

}
