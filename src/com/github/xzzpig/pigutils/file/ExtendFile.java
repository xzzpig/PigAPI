package com.github.xzzpig.pigutils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URI;
import java.util.Scanner;
import java.util.function.Consumer;

public class ExtendFile extends File {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6607765001093094027L;

	public ExtendFile(File file) {
		super(file.getPath());
	}

	public ExtendFile(File parent, String child) {
		super(parent, child);
	}

	public ExtendFile(String pathname) {
		super(pathname);
	}

	public ExtendFile(String parent, String child) {
		super(parent, child);
	}

	public ExtendFile(URI uri) {
		super(uri);
	}

	public boolean appendTo(File file) {
		ExtendFile f = file instanceof ExtendFile ? (ExtendFile) file : new ExtendFile(file);
		return f.withOutputStream(this::sendTo, true);
	}

	public boolean copyTo(File file) {
		ExtendFile f = file instanceof ExtendFile ? (ExtendFile) file : new ExtendFile(file);
		return f.withOutputStream(this::sendTo, false);
	}

	public boolean eachLine(Consumer<String> consumer) {
		return withScanner(s -> {
			while (s.hasNextLine())
				consumer.accept(s.nextLine());
		});
	}

	public boolean sendTo(OutputStream out) {
		return withInputStream(in -> {
			int i;
			try {
				while ((i = in.read()) != -1) {
					out.write(i);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	public String text() {
		StringBuffer sb = new StringBuffer();
		if (eachLine(str -> sb.append('\n').append(str)))
			return sb.toString().replaceFirst("\n", "");
		else
			return null;
	}

	public boolean withInputStream(Consumer<InputStream> consumer) {
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(this);
			consumer.accept(fin);
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			try {
				fin.close();
			} catch (IOException e) {
			}
		}
	}

	public boolean withOutputStream(Consumer<OutputStream> consumer, boolean append) {
		FileOutputStream fout = null;
		try {
			fout = new FileOutputStream(this, append);
			consumer.accept(fout);
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			try {
				fout.close();
			} catch (IOException e) {
			}
		}
	}

	public boolean withScanner(Consumer<Scanner> consumer) {
		return withInputStream(in -> consumer.accept(new Scanner(in)));
	}

	public boolean withWriter(Consumer<Writer> consumer, boolean append) {
		Writer fWriter = null;
		try {
			fWriter = new FileWriter(this, append);
			consumer.accept(fWriter);
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			try {
				fWriter.close();
			} catch (IOException e) {
			}
		}
	}
}
