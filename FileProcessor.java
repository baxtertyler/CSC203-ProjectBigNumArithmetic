import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class FileProcessor {

    /**
     * Processes arithmetic expressions line-by-line in the given file.
     *
     * @param filePath Path to a file containing arithmetic expressions.
     */
    public static void processFile(String filePath) {
        File infile = new File(filePath);
        try (Scanner scan = new Scanner(infile)) {
            while (scan.hasNext()) {
                // TODO: Process each line of the input file here.
                String num1 = scan.next();
                LinkedList n1 = new LinkedList();
                String opp = scan.next();
                String num2 = scan.next();
                LinkedList n2 = new LinkedList();
                for (int h = num1.length() - 1; h >= 0; h--)
                {
                    n1.addNode((int) num1.charAt(h) - 48);
                }
                for (int k = num2.length() - 1; k >= 0; k--)
                {
                    n2.addNode((int) num2.charAt(k) - 48);
                }
                String s = "";
                if (opp.charAt(0) == '+'){
                    s = add(n1, n2);
                }
                if (opp.charAt(0) == '*') {
                    s = mult(n1, n2);
                }
                if (opp.charAt(0) == '^')
                    s = exp(n1, n2);
                System.out.println(num1 + " " + opp + " " + num2 + " = " + s);

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + infile.getPath());
        }
    }

    public static String add(LinkedList n1, LinkedList n2)
    {
        if (n1.getHead() == null && n2.getHead() == null) return "0";
        if (n1.getSize() > n2.getSize()){
            while (n2.getSize() != n1.getSize()){
                n2.addNode(0);
            }
        } else if (n2.getSize() > n1.getSize()) {
            while (n1.getSize() != n2.getSize()){
                n1.addNode(0);
            }
        }

        String returnString = "";
        Node n1Curr = n1.getHead();
        Node n2Curr = n2.getHead();
        boolean carry = false;

        for (int i = 0; i <= n1.getSize()  - 1; i++){
            int total = n1Curr.getValue() + n2Curr.getValue();

            if (carry){
                total += 1;
                carry = false;
            }

            if (total > 9){
                total = total % 10;
                carry = true;
            }

            String s = String.valueOf(total);
            returnString = s + returnString;

            if (carry && (n1.getSize() == returnString.length())){
                returnString = "1" + returnString;
            }
            n1Curr = n1Curr.getNext();
            n2Curr = n2Curr.getNext();
        }
        return returnString;
    }

    public static String mult(LinkedList l1, LinkedList l2){
        int repNum = 0;
        Node n1 = l1.getHead();
        Node n2 = l2.getHead();
        LinkedList resultList = new LinkedList();
        int carry = 0;
        while (n1 != null)
        {
            while (n2 != null)
            {
                int x = (n1.getValue() * n2.getValue()) + carry;
                carry = 0;
                if (x>9)
                {
                    carry = x/10;
                    x = x%10;
                }
                resultList.addNode(x);
                if (n1.getNext() == null && carry != 0)
                {
                    resultList.addNode(carry);
                }
                n1 = n1.getNext();
                if (n1 == null)
                {
                    break;
                }
            }
            carry = 0;
            n1 = l1.getHead();
            n2 = n2.getNext();
            if (n2 == null)
            {
                break;
            }
            resultList.addNode(-1);
            repNum += 1;
            int i = 0;
            while(i < repNum)
            {
                resultList.addNode(0);
                i++;
            }
        }

        Node c = resultList.getHead();
        LinkedList t1 = new LinkedList();
        String s;
        while(c != null && c.getValue() != -1)
        {
            t1.addNode(c.getValue());
            c = c.getNext();
        }
        if (c != null) c = c.getNext();
        LinkedList t2 = new LinkedList();
        while(c != null)
        {
            if (c.getValue() != -1)
            {
                t2.addNode(c.getValue());
            }
            else {
                s = add(t1, t2);
                t1 = new LinkedList();
                t2 = new LinkedList();
                for (int h = s.length() - 1; h >= 0; h--)
                {
                    t1.addNode((int) s.charAt(h) - 48);
                }
            }
            c = c.getNext();
        }
        return add(t1, t2);
    }

    public static String exp(LinkedList n1, LinkedList n2){
        String strExp = "";
        Node curr = n2.getHead();
        for (int i = 0; i < n2.getSize(); i ++){
            strExp = curr.getValue() + strExp;
            curr = curr.getNext();
        }
        int exp = Integer.parseInt(strExp);

        LinkedList one = new LinkedList();
        one.addNode(1);

        String val = mult(one, n1);
        String squaredVal = mult(stringConverter(val), stringConverter(val));

        if (exp == 1){
            return val;
        }
        if (exp % 2 == 0){
            exp = exp / 2;
            return expHelper(squaredVal, exp);
        }
        else{
            exp = (exp - 1) / 2;
            return mult(stringConverter(expHelper(squaredVal, exp)), stringConverter(val));

        }
    }


    public static String expHelper(String squaredVal, int exp){
        String total = squaredVal;
        for (int i = 0; i < exp - 1; i ++){
            total = mult(stringConverter(total), stringConverter(squaredVal));
        }
        return total;
    }

    public static LinkedList stringConverter(String s1){
        LinkedList t1 = new LinkedList();

        for (int h = s1.length() - 1; h >= 0; h--)
        {
            t1.addNode((int) s1.charAt(h) - 48);
        }
        return t1;
    }
}





