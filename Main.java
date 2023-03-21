import java.util.HashMap;
import java.util.Scanner;

class Main {
	private static final String[] opts = {
			"add an item", "delete an item", "update an item", "show all the items", "quit the program"
	};

	public static void main(String[] args) {
		try (var scn = new Scanner(System.in)) {
			int choice = 0;

			while (choice != 5) {
				printOpts();
				choice = scn.nextInt();

				if (choice == 1) {
					Inventory.addFrom(scn);
				} else if (choice == 2) {
					Inventory.removeFrom(scn);
				} else if (choice == 3) {
					Inventory.editFrom(scn);
				} else if (choice == 4) {
					Inventory.print();
				}
			}
		}
	}

	private static void printOpts() {
		for (int i = 0; i < opts.length; i++) {
			System.out.println("Press " + (i + 1) + " to " + opts[i]);
		}
	}
}

class Inventory {
	private static final HashMap<String, Item> ITEMS_MAP = new HashMap<>();

	private static class Item {
		public final String name;
		public final int cost;

		Item(String name, int cost) {
			this.name = name;
			this.cost = cost;
		}
	}

	public static void addFrom(Scanner scn) {
		scn.nextLine(); // consume newline from previous inputs

		System.out.println("Enter the name:");
		var name = scn.nextLine();

		System.out.println("Enter the serial number:");
		var sn = scn.nextLine();

		System.out.println("Enter the value in dollars (whole number):");
		var cost = scn.nextInt();

		ITEMS_MAP.put(sn, new Item(name, cost));
	}

	public static void removeFrom(Scanner scn) {
		System.out.println("Enter the serial number of the item to delete:");
		ITEMS_MAP.remove(scn.next());
	}

	public static void editFrom(Scanner scn) {
		System.out.println("Enter the serial number of the item to change:");
		var serial = scn.next();

		System.out.println("Enter the new name:");
		scn.nextLine(); // consume newline
		var name = scn.nextLine();

		System.out.println("Enter the new value in dollars (whole number):");
		var cost = scn.nextInt();

		ITEMS_MAP.replace(serial, new Item(name, cost));
	}

	public static void print() {
		for (var serial : ITEMS_MAP.keySet()) {
			var item = ITEMS_MAP.get(serial);
			System.out.println(item.name + ',' + serial + ',' + item.cost);
		}
	}
}
