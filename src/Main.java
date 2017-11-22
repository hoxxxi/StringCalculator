public class Main {
    public static void main(String[]args) {
        System.out.println(calculate("(((1+3)+(1+6))*(((5-2)+3)*8))"));
    }

    private static int calculate(String inputStr) {
        char[]input = inputStr.toCharArray();
        int posOfLastLeftBracket = -1;
        for(int i = 0;i<input.length; i++) {
            if(input[i]=='(')
                posOfLastLeftBracket = i;
            if(input[i]==')') {
                try {
                    return calculate(inputStr.substring(0, posOfLastLeftBracket)+valueOf(inputStr.substring(posOfLastLeftBracket+1,i))+(i+1>=input.length?"":inputStr.substring(i+1)));
                }
                catch (IndexOutOfBoundsException e) {
                    System.err.println("String does not have as many opened brackets as it has closed!");
                }
            }
        }
        return Integer.parseInt(inputStr);
    }

    private static int valueOf(String statement) {
        String leftNumber = "";
        String rightNumber = "";
        boolean seenSign = false;
        String sign="";
        for(int i = 0;i<statement.length();i++)
        {
            if((statement.charAt(i)+"").matches("[0-9]") && !seenSign)
                leftNumber+=statement.charAt(i);
            else if((statement.charAt(i)+"").matches("[0-9]") && seenSign)
                rightNumber+=statement.charAt(i);
            else if((statement.charAt(i)+"").matches("[\\/\\+\\-\\*]")) {
                sign = statement.charAt(i) + "";
                seenSign=true;
            }
        }
        if(sign.equals("+"))
            return Integer.parseInt(leftNumber)+Integer.parseInt(rightNumber);
        if(sign.equals("-"))
            return Integer.parseInt(leftNumber)-Integer.parseInt(rightNumber);
        if(sign.equals("/"))
            return Integer.parseInt(leftNumber)/Integer.parseInt(rightNumber);
        if(sign.equals("*"))
            return Integer.parseInt(leftNumber)*Integer.parseInt(rightNumber);
        return -1;
    }
}
