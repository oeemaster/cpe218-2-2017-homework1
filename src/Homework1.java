public class Homework1 {

	public static void main(String[] args) {
        String input = "";
		// Begin of arguments input sample
		if (args.length > 0) {
			input = args[0];
			if (input.equalsIgnoreCase("251-*32*+")) {
				System.out.println("(2*(5-1))+(3*2)=14");
			}
		}
		// End of arguments input sample

		// TODO: Implement your project here
        Node n = new Node();
        Node temp = n;
        for (int i = 0; i < input.length(); i++) {
			temp.setRight(new Node());
			temp = temp.getRight();
			temp.setValue(String.valueOf(input.charAt(i)));
		}
        n = infix(n.getRight());
        String infix = inorder(n);
		System.out.println(infix.substring(1,infix.length()-1)+ "=" + calculater(n));
	}

	public static Node infix(Node n) {
		Node tmp = n;
		while (tmp != null){
			n = tmp;
			tmp = n.getRight();
			if (n.getValue().matches("[0-9]")) {
				n.setRight(null);
			} else {
				if (n.getLeft().getValue().matches("[0-9]")) {
					n.setRight(n.getLeft());
					n.setLeft(n.getRight().getLeft());
					n.getRight().setLeft(null);
				} else {
					n.setRight(n.getLeft());
					n.setLeft(n.getRight().getLeft().getLeft());
					n.getRight().getLeft().setLeft(null);
				}
			}
			if (tmp != null) tmp.setLeft(n);
		}
		return n;
	}

	public static String inorder(Node n) {
	    String left = "";
        String right = "";
		if (n.getLeft() != null) {
			left = "(" + inorder(n.getLeft());
		}
		if (n.getRight() != null) {
			right = inorder(n.getRight()) + ")";
		}

		return (left + n.getValue() + right);
	}

	public static int calculater(Node n) {

        if (n.getValue().matches("[0-9]")) return Integer.valueOf(n.getValue());

        int result = 0;
        int left = calculater(n.getLeft());
        int right = calculater(n.getRight());

	    switch (n.getValue()){
            case "+":result = left + right;break;
            case "-":result = left - right;break;
            case "*":result = left * right;break;
            case "/":result = left / right;break;
        }

        return result;
	}
}
