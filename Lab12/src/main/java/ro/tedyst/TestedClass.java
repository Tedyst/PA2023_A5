package ro.tedyst;

public class TestedClass {
    @Test
    public static void printTest(){
        System.out.println("TestedClass.printTest() called");
    }

    @Test
    public static void failTest() throws Exception {
        System.out.println("TestedClass.failTest() called");
        throw new Exception("TestedClass.failTest() failed");
    }
}
