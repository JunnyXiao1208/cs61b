import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> testArray = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> stdArray = new ArrayDequeSolution<>();
        StringBuilder log = new StringBuilder(); // 使用 StringBuilder 优化字符串拼接

        for (int i = 0; i < 1000; i++) {
            int operation = StdRandom.uniform(4);
            int number;

            // 优化：合并 addFirst 和 addLast 操作
            if (operation < 2) {
                number = StdRandom.uniform(1000);
                if (operation == 0) {
                    addToDeque(testArray, stdArray, log, "addFirst", number);
                } else {
                    addToDeque(testArray, stdArray, log, "addLast", number);
                }
            } else if (stdArray.size() > 0) { // 确保数组不为空
                if (operation == 2) {
                    removeFromDeque(testArray, stdArray, log, "removeFirst");
                } else {
                    removeFromDeque(testArray, stdArray, log, "removeLast");
                }
            }
        }
    }

    private void addToDeque(StudentArrayDeque<Integer> testArray, ArrayDequeSolution<Integer> stdArray, StringBuilder log, String method, int number) {
        log.append(method).append("(").append(number).append(")\n");
        if (method.equals("addFirst")) {
            testArray.addFirst(number);
            stdArray.addFirst(number);
        } else {
            testArray.addLast(number);
            stdArray.addLast(number);
        }
    }

    private void removeFromDeque(StudentArrayDeque<Integer> testArray, ArrayDequeSolution<Integer> stdArray, StringBuilder log, String method) {
        log.append(method).append("()\n");
        Integer testNumber = method.equals("removeFirst") ? testArray.removeFirst() : testArray.removeLast();
        Integer stdNumber = method.equals("removeFirst") ? stdArray.removeFirst() : stdArray.removeLast();
        assertEquals(log.toString(), stdNumber, testNumber);
    }
}
