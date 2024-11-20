
package wk9;

public class PriorityQueueElement {

	public int priority;
	public int item;

	PriorityQueueElement(int item, int priority) {
		this.item = item;
		this.priority = priority;
	}

	public String toString() {
		return "<" + item + ", " + priority + ">";
	}
}