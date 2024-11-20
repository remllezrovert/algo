package wk7;

import java.util.Hashtable;

public class HuffmanDecoder {

	public static String decode(String encodedMsg, Hashtable<String, Character> encodingToCharMapping) { // complete this method
		String encode = "";
		String decodedMsg = "";
		int n = encodedMsg.length();

		for (int i = 0; i < n; i++){
			encode = encode + encodedMsg.charAt(i);
			if (encodingToCharMapping.containsKey(encode)){
				char c = encodingToCharMapping.get(encode);
				encodingToCharMapping.replace(encode,c);
				decodedMsg += c;
				encode = "";
			}
		}
		return decodedMsg;

	}
}
