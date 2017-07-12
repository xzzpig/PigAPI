package com.github.xzzpig.pigutils.crypt;

import com.github.xzzpig.pigutils.crypt.md5.FileMD5Crypter;
import com.github.xzzpig.pigutils.crypt.md5.MD5Crypter;
import com.github.xzzpig.pigutils.event.TransformEvent;
import com.github.xzzpig.pigutils.event.TransformEvent.Transformer;

public abstract class Crypter {

	private static class CrypterTransformr implements Transformer<String, Crypter> {
		private Crypter crypter;

		public CrypterTransformr(Crypter crypter) {
			this.crypter = crypter;
		}

		@Override
		public String toString() {
			return "crypt." + crypter.getCryptType();
		}

		@Override
		public Crypter transform(String str) {
			if (str.equals("crypt." + crypter.getCryptType()))
				return crypter;
			else
				return null;
		}
	}

	static {
		regCrypter(new MD5Crypter());
		regCrypter(new FileMD5Crypter());
	}

	public static Cryptable crypt(String type, Object... objs) {
		Crypter crypter = TransformEvent.transform("crypt." + type, Crypter.class);
		return crypter == null ? null : crypter.crypt(objs);
	}

	public static Decryptable decrypt(String type, Object... objs) {
		Crypter crypter = TransformEvent.transform("crypt." + type, Crypter.class);
		return crypter == null ? null : crypter.decrypt(objs);
	}

	public static void regCrypter(Crypter crypter) {
		TransformEvent.addTransformer(new CrypterTransformr(crypter));
	}

	public static void unregCrypter(Crypter crypter) {
		TransformEvent.removeTransformer(new CrypterTransformr(crypter));
	}

	protected abstract Cryptable crypt(Object... objs);

	protected abstract Decryptable decrypt(Object... objs);

	public abstract String getCryptType();
}
