

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.awt.*;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Main main = new Main();

        // 000: For testing purpose
//            main.play();

        // 001: Exception in Finally escape remaining code
//        main.TryCatchFinallyComplicatedTest();  // output is given below
            //        Before Throw in try
            //        Before Throw in catch
            //        Before Throw in finally
            //        Exception in TryCatchFinallyComplicated: Finally

        // 002: StringEnumValidatorTest
//        main.StringEnumValidatorTest_Test();
//        base64Test();

//        AuthyClient();

//        main.solution();

//        trickyTest();

//        compiler();
//        compare();
//        decimalFormat();
//            javaBasics();
//        In_Out_Scanner();
//        play();

//        System.out.println(getABC(703));

//        bitwise();

//    bulb();
        pair();
    }
    static void pair(){
        int[] A = {3,5,6,3,3,5};
        int pairs=0;
        for(int i=0; i<A.length; i++){
            for(int j=i+1; j<A.length; j++){
                if(A[i] == A[5]){
                    pairs++;
                }
            }
        }
        System.out.println(pairs);

    }
        static void bitwise(){
        String s = "011100";
        Long l = parseLong(s, 2);
        int i = 0;
        do{
            if(l%2!=0){
                l=l-1;
            }else {
                l = l/2;
            }
            i++;
        }while (l!=0);
        System.out.println(i);
    }
    private static long parseLong(String s, int base) {
        return new BigInteger(s, base).longValue();
    }

        static void bulb(){

        int[] A = {1,3,4,2,5};
        int lastInd=-1,movements=0;
        for(int i=0; i<A.length; i++){
            for(int j=0; j<A.length; j++){
                if(A[j] == i+1){
                    lastInd = j+1;
                    break;
                }
            }
            if(lastInd>=(i+1)){
                movements++;
            }
        }
        System.out.println("Movements "+movements);
    }
    static String getABC(Integer num){

    final String[] arr= {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
//        arr.si
        String result ="";
        Integer rem;

        do{
            rem= num%26;
            num = num / 26;
            if(rem!=0)
            result = arr[rem-1]+result;
            else
                result = arr[25]+result;
        }
        while(num>=26);

        return  result;
    }


    public static class InstructionSequence{
        private Integer count;
        private List<String> inputs = new ArrayList<>();
        private Boolean[] output = new Boolean[32];

        public InstructionSequence() {
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(final Integer count) {
            this.count = count;
        }

        public List<String> getInputs() {
            return inputs;
        }


        public Boolean[] getOutput() {
            return output;
        }


        private void computeOutput(){
         for (int i=0;i<count;i++){
             switch (Instruction.getPossibleMatch(inputs.get(i))){
                 case CLEAR:
                     processClear(inputs.get(i));
                     break;
                 case SET:
                     processSet(inputs.get(i));
                     break;
                 case AND:
                     processAND(inputs.get(i));
                     break;
                 case OR:
                     processOR(inputs.get(i));
                     break;
             }
         }
        }

        private void processClear(final String inp) {
            int secondBit = getLast(inp.replace(Instruction.CLEAR.value(), ""));
            output[secondBit] = false;
        }

        private void processSet(final String inp) {
            int secondBit = getLast(inp.replace(Instruction.SET.value(), ""));
            output[secondBit] = true;
        }

        private void processAND(final String inp) {
            int firstBit = getFirst(inp.replace(Instruction.AND.value(), ""));
            int secondBit = getLast(inp.replace(Instruction.AND.value(), ""));
            output[firstBit] = (output[firstBit] == null
                    || output[secondBit] == null) ?
                    null :
                    output[firstBit] && output[secondBit];
        }

        private void processOR(final String inp) {
            int firstBit = getFirst(inp.replace(Instruction.OR.value(), ""));
            int secondBit = getLast(inp.replace(Instruction.OR.value(), ""));
            output[firstBit] = (output[firstBit] == null
                    || output[secondBit] == null) ?
                    null :
                    output[firstBit] || output[secondBit];
        }

        private int getFirst(final String inp){
            return Integer.parseInt(inp.substring(1,inp.lastIndexOf(" ")));
        }

        private int getLast(final String inp){
            return Integer.parseInt(inp.substring(inp.lastIndexOf(" ")+1));
        }

        private String getOutputCalculated(){
            final StringBuilder  result= new StringBuilder("");
            for(int i = 31; i>=0; i--){
                result.append(output[i] == null ? "?" : output[i] ? "1" : "0");
            }
            return result.toString();
        }

        public String getResult(){
            computeOutput();
            return getOutputCalculated();
        }
    }

    public enum Instruction {
        CLEAR,
        SET,
        OR,
        AND;
        private final String value = name();

        public String value() {
            return value;
        }

        public static Instruction fromValue(final String value) {
            for (final Instruction property : Instruction.values()) {
                if (property.value().equals(value)) {
                    return property;
                }
            }
            throw new IllegalArgumentException("Invalid Instruction");
        }

        public static boolean isValid(final String value) {
            try {
                fromValue(value);
            } catch (final Exception ex) {
                return false;
            }
            return true;
        }

        public static Instruction getPossibleMatch(final String input) {
            if (input.startsWith(CLEAR.value)) {
                return CLEAR;
            } else if (input.startsWith(SET.value)) {
                return SET;
            } else if (input.startsWith(OR.value)) {
                return OR;
            } else if (input.startsWith(AND.value)) {
                return AND;
            } else {
                throw new IllegalArgumentException("Invalid Instruction");
            }
        }
    }

    static void play(){

        final Scanner sc =new Scanner(System.in);
        final Integer in =sc.nextInt();
        InstructionSequence instructionSequence = new InstructionSequence();
        instructionSequence.setCount(in);
        sc.nextLine();
        List<String> inputs = instructionSequence.getInputs();
        for(int i=0; i<in;i++){
            inputs.add(sc.nextLine());
        }
        inputs.stream().forEach(ip-> {
            System.out.println(ip);
        });
        System.out.println("Result "+ instructionSequence.getResult());
    }

    static void In_Out_Scanner() {
        final Scanner sc =new Scanner(System.in);
        final String in =sc.next();
        System.out.println("This was your input : "+in);
    }

    static void linkedIn_java()
    {
        for(int i=0;i<10;i++)
                    System.out.println("");
                    System.out.println("Hi");

        final String[] arr= {"abc","2","10","0"};
            Arrays.sort(arr);
                            System.out.println(Arrays.toString(arr));


        short s1 = 42;
        short s2 = 42;
//        short s3 = s1+s2; // compiler error
        short b1 = 42;
        short b2 = 42;
//        short b3 = s1+s2;// compiler error

        for (int i=0; i<10 ;i++){
            i+=1;
                                        System.out.println("A");
        }

        String a = "\"a\"";
        a.substring(3,4);
        System.out.println(a);

                List lis= new ArrayList();
        lis.add("sa");
        lis.add(123);


    }


    public static void foo(Object o) {
        System.out.println("Object impl");
    }
    public static void foo(String s) {
        System.out.println("String impl");
    }


    static void javaBasics(){
        class A
        {
            int a=5;

            public A(int a) {
                this.a = a;
            }

            @Override public int hashCode() {
                int hash = 7;
                hash = 83 * hash + Objects.hashCode(this.a);
                return hash;
            }

            @Override public boolean equals(Object obj) {
                return super.equals(obj);
            }

            @Override public String toString() {
                return getClass().getName() + "@" + Integer.toHexString(hashCode());
            }

        }
        A a=new A(5);
        A b=new A(5);
        A c= b;
        //        a==b;
        if(a==b){
            System.out.println("a==b");
        }
        if(c==b){
            System.out.println("c==b");
        }
        if(a.equals(b)){
            System.out.println("a.equals(b)");
        }
        if(a.hashCode()==b.hashCode()){
            System.out.println("a.hashCode()==b.hashCode()");
        }
        System.out.println(a);

        a.equals(b);
        //        System.out.println(c);

    }


    public static void decimalFormat()
    {
        String pattern = "0";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String format = decimalFormat.format(11546);
        System.out.println(format);


    }

    public static void compare()
    {
        List<Point> people = Arrays.asList(
                new Point(1, 24),
                new Point(1, 18),
                new Point(2, 21),
                new Point(1, 21)
        );
        Collections.sort(people,
                new Comparator<Point>() {
                    @Override
                    public int compare(final Point a, final Point b) {
                        int ret = a.y< b.y ? -1 : a.y == b.y ? 0 : 1;
                        return ret;
                    }
                });
        for (Point p: people
             ) {
            System.out.println(p.x+"-"+p.y);
        }
    }
    public static void compiler(){

//        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//        String code = "if(true) System.out.println(\"success\");";
//        int compilationResult = compiler.run(null,null,null,code);
//        if(compilationResult == 0){
//            System.out.println("Compilation is successful");
//        }else{
//            System.out.println("Compilation Failed: "+compilationResult);
//        }
//        Binding binding = new Binding();
//        GroovyShell shell = new GroovyShell(binding);
//        Object value = shell.evaluate("for (x=0; x<5; x++){println "Hello"}; return x");

    }
    public static void tricky(Point arg1, Point arg2)
    {
        arg1.x = 100;
        arg1.y = 100;
        Point temp = arg1;
        arg1 = arg2;
        arg2 = temp;
    }
    public static void trickyTest()
    {
        Point pnt1 = new Point(0,0);
        Point pnt2 = new Point(0,0);
        Point c = new Point();
        Point d = c;
        c.x=10;
        d.x=20;

        System.out.println("X: " + pnt1.x + " Y: " +pnt1.y);
        System.out.println("X: " + pnt2.x + " Y: " +pnt2.y);
        System.out.println(" ");
        tricky(pnt1,pnt2);
        System.out.println("X: " + pnt1.x + " Y:" + pnt1.y);
        System.out.println("X: " + pnt2.x + " Y: " +pnt2.y);
    }

    void solution()
    {
        Solution solution=new Solution();
        System.out.println(solution.solution(20,2));
    }

    void StringEnumValidatorTest_Test() // not working yet
    {
        StringEnumValidatorTest stringEnumValidatorTest = new StringEnumValidatorTest();
        stringEnumValidatorTest.setMyStatus("ONLINE");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<StringEnumValidatorTest>> constraintViolations =
                validator.validate(stringEnumValidatorTest);

        if(constraintViolations.size()!=0)
        {
            for (ConstraintViolation c: constraintViolations
                 ) {
                System.out.println(c.getMessage());
            }
        }

    }
    void TryCatchFinallyComplicatedTest()
    {
        try {
            TryCatchFinallyComplicated();
        }
        catch (Exception e)
        {
            System.out.println("Exception in TryCatchFinallyComplicated: "+e.getMessage());
        }
    }
    void TryCatchFinallyComplicated() throws Exception {
        try{
            System.out.println("Before Throw in try");
            if(true)
                throw new Exception("Try");
            System.out.println("After Throw in try");
        }
        catch (Exception e)
        {
            System.out.println("Before Throw in catch");
            if(true)
                throw new Exception("Catch");
            System.out.println("After Throw in catch");
        }
        finally {
            System.out.println("Before Throw in finally");
            if(true)
                throw new Exception("Finally");
            System.out.println("After Throw in finally");
        }


    }
}
