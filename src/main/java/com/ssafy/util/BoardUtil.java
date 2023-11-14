package com.ssafy.util;

import java.util.*;
import java.io.*;

public class BoardUtil {

	private static BoardUtil instance = new BoardUtil();

	private BoardUtil() {
	}

	public static BoardUtil getInstance() {
		return instance;
	}

	public static boolean filterSlangs(String content) {
		// file 로 읽어오기, list로 만들기
		List<String> slangs = new LinkedList<String>();
		slangs.add("시발");
		slangs.add("미친");
		slangs.add("병신");
		slangs.add("새끼");
		boolean result = false;
		for (int i = 0; i < slangs.size(); i++) {
			if(findStringtarget(content, slangs.get(i))) {
				result = true;
			}
		}
		return result;
	}

	private static boolean findStringtarget(String content, String target) {
		boolean result = false;
		final int MOD = 100000007;
		int parentSize = content.length();

		int targetSize = target.length();

		long parentHash = 0, targetHash = 0, power = 1;

		for (int i = 0; i <= parentSize - targetSize; i++) {
			if (i == 0) {
				for (int j = 0; j < targetSize; j++) {
					parentHash = (parentHash + (content.charAt(targetSize - 1 - j)) * power) % MOD;
					targetHash = (targetHash + (target.charAt(targetSize - 1 - j)) * power) % MOD;

					if (j < targetSize - 1) {
						power = (power % MOD * 31) % MOD;
					}
				}

			} else {
				parentHash = 31 * parentHash % MOD - 31 * content.charAt(i - 1) * power % MOD
						+ content.charAt(i + targetSize - 1);
				parentHash %= MOD;
			}

			System.out.println(i + " >> 해시값 비교: " + parentHash + ", " + targetHash);

			if (parentHash == targetHash) {
				// 같은거 처리
				result = true;
			}
		}
		return result;
	}
}
