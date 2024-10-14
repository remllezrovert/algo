package wk7;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.PriorityQueue;

public class HuffmanEncoder {

	private HashMap<Character, String> charToEncodingMapping;

	private HashMap<Character, Integer> frequencyMap;

	private int encodingLength, tableSize;

	public HuffmanEncoder(HashMap<Character, Integer> frequencyMap) {
		charToEncodingMapping = new HashMap<>();
		this.frequencyMap = frequencyMap;
		this.encodingLength = this.tableSize = 0;
	}

	public void encode() throws IndexOutOfBoundsException { // complete this method
	}

	private BinaryTreeNode buildTree() throws IndexOutOfBoundsException { // complete this method
	}

	private void createTable(BinaryTreeNode node, String encoding) { // complete this method
	}

	public int getTableSize() {
		return tableSize;
	}

	public int getEncodingLength() {
		return encodingLength;
	}

	public String getEncoding(char c) {
		return charToEncodingMapping.get(c);
	}
}
