package solutions.stack;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DoublyLinkedListTest {

	@Test
	public void dummy() {
		int n = 8;
		int k = 2;

		DoublyLinkedList dll = new DoublyLinkedList(n, k);
		dll.moveDown(2); // D 2
		// System.out.println(dll.getCurr());

		dll.delete();    // C
		// System.out.println(dll.getCurr());
		dll.moveUp(3);   // U 3
		System.out.println(dll.getCurr());
		dll.delete();    // C
		dll.moveDown(4); // D 4
		dll.delete();    // C
		dll.moveUp(2);   // U 2
		dll.undo();      // Z
		dll.undo();      // Z
		//
		assertThat(dll.getResult(n)).isEqualTo("OOOOXOOO");

	}
}