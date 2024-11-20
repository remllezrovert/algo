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
		BinaryTreeNode root = buildTree();
		createTable(root,"");
		frequencyMap.forEach((key,value)->{
			char c = key;
			int f = value;
			String code = getEncoding(c);
			encodingLength = encodingLength + f * code.length();
			tableSize = tableSize + code.length() +8;
		});
	}




	private BinaryTreeNode buildTree() throws IndexOutOfBoundsException { // complete this method
		PriorityQueue<BinaryTreeNode> PQ = new PriorityQueue<>(new BinaryTreeNodeComparator());

		frequencyMap.forEach((key,value)->{
			char c = key;
			int f = value;
			BinaryTreeNode z = new BinaryTreeNode(c,f);
			PQ.add(z);
		});

		while (PQ.size() > 1){
			BinaryTreeNode min = PQ.poll();
			BinaryTreeNode secondMin = PQ.poll();
			BinaryTreeNode z = new BinaryTreeNode('\0',min.value + secondMin.value);
			z.left= min;
			z.right = secondMin;
			PQ.add(z);
		}
		return PQ.peek();
	}

	private void createTable(BinaryTreeNode node, String encoding) { // complete this method
		if (node.left == null && node.right == null){
			charToEncodingMapping.put(node.character, encoding);
		} else {
			if (node.left != null) createTable(node.left, encoding + "0");
			if (node.right != null) createTable(node.right, encoding + "1");
		}
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
