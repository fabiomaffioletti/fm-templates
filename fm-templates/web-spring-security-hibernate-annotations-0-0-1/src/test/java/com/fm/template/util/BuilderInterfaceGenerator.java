package com.fm.template.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fm.template.model.Config;

/**
 * Utility class for Java code in order to build Fluent Interface
 */
public class BuilderInterfaceGenerator {

	public static void main(String[] args) {
		generateBulilderIntercface(Config.class);
	}

	private static void generateBulilderIntercface(Class<?> c) {
		StringBuilder sb = new StringBuilder();
		String generatetInterfaceName = c.getSimpleName() + "Fluent ";
		sb.append("public class ").append(c.getSimpleName()).append("Builder{\n");
		sb.append("public interface ").append(generatetInterfaceName);
		sb.append("extends BaseBuilder<").append(c.getSimpleName()).append("> {");
		sb.append("\n");
		for (Field f : getInheritedFields(c)) {
			sb.append("\t");
			sb.append(generatetInterfaceName).append(f.getName()).append("(");
			sb.append(f.getType().getSimpleName()).append(" v);\n");
		}
		sb.append("}\n\n");
		String methodName = firstChaeLowerCase(c.getSimpleName());
		sb.append("public static ").append(generatetInterfaceName).append(methodName).append("Builder(){\n\t");
		sb.append(c.getSimpleName()).append(" instance = new ").append(c.getSimpleName()).append("(toDo ... );\n");
		sb.append("\treturn FluentBuilder.builderFor(").append(generatetInterfaceName).append(".class,instance);");
		sb.append("\n}\n}");
		System.out.println(sb.toString());
	}

	public static List<Field> getInheritedFields(Class<?> type) {
		List<Field> fields = new ArrayList<Field>();
		for (Class<?> c = type; c != null; c = c.getSuperclass()) {
			fields.addAll(Arrays.asList(c.getDeclaredFields()));
		}
		return fields;
	}

	private static String firstChaeLowerCase(String s) {
		return s.substring(0, 1).toLowerCase() + s.substring(1);
	}
}
