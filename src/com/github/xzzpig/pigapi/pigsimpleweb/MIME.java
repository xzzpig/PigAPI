package com.github.xzzpig.pigapi.pigsimpleweb;

public class MIME {
	public static final MIME application_octet_stream = new MIME("application_octet-stream", "file");
	public static final MIME text_html = new MIME("text/html", "text");
	public static final MIME text_plain = new MIME("text/plain", "text");

	private final String name, type;

	public MIME(String str, String solveType) {
		name = str;
		type = solveType;
	}

	public String getName() {
		return name;
	}

	public String getSolveTyle() {
		return type;
	}

	@Override
	public String toString() {
		return name;
	}
}
