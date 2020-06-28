import java.util.LinkedList;
public class RPN {
    static boolean isDelim(char c) {
        return c == ' ';
    }
    static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '×' || c == '÷';
    }
    static int priority(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '×':
            case '÷':
                return 2;
            default:
                return -1;
        }
    }
    static void processOperator(LinkedList<Double> st, char op) {
        double r = st.removeLast();
        double l = st.removeLast();
        switch (op) {
            case '+':
                st.add(l + r);
                break;
            case '-':
                st.add(l - r);
                break;
            case '×':
                st.add(l * r);
                break;
            case '÷':
                st.add(l / r);
                break;
        }
    }
    public static double eval(String s) {
        LinkedList<Double> st = new LinkedList<Double>();
        LinkedList<Character> op = new LinkedList<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isDelim(c))
                continue;
            if (c == '(')
                op.add('(');
            else if (c == ')') {
                while (op.getLast() != '(')
                    processOperator(st,op.removeLast());
                op.removeLast();
            } else if (isOperator(c)) {
                while (!op.isEmpty() && priority(op.getLast()) >= priority(c))
                    processOperator(st, op.removeLast());
                op.add(c);
            } else {
                String operand = "";

                while (i < s.length() && ((s.charAt(i)== '$' || s.charAt(i)=='.') || Character.isDigit(s.charAt(i))))
                    operand += s.charAt(i++);
                --i;
                operand = operand.replace('$', '-');
                st.add(Double.parseDouble(operand));
            }
        }
        while (!op.isEmpty())
            processOperator(st, op.removeLast());
        return st.get(0);
    }

    /**
     * Символ $ заменяет отрицательный "-" ($5 == -5)
     *
     */
    public double count(String primer) throws Exception {
        return eval(primer);
    }

}