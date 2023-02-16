import java.util.Random;
import java.util.ArrayList;
import java.io.*;

/**
 * Task_1
 */
public class Task_1 {

    public static void main(String[] args) {
        int k = 2000;
        int i;
        byte n;
        ArrayList<Integer> m1 = new ArrayList<Integer>();
        ArrayList<Integer> m2 = new ArrayList<Integer>();
        i = random_i(k);
        n = find_number_bit(i);
        m1 = find_multiple(n, i);
        m2 = find_non_multiple(n, i);
        write_to_file(i, n, m1, m2);

    }

    public static int random_i(int args) {
        int i = new Random().nextInt(args);
        return i;
    }

    public static byte find_number_bit(Integer i) {
        String tmp_str = toBinaryString(i);
        byte n = Bin_search(tmp_str);
        return n;
    }

    public static String toBinaryString(int value) {
        StringBuilder result = new StringBuilder(32);
        for (int i = 0; i < 32; ++i) { // 32 бита у int
            result.append(value & 1);
            value >>>= 1;
        }
        return result.reverse().toString();
    }

    public static byte Bin_search(String tmp_str) {
        char[] result = tmp_str.toCharArray();
        byte i = 0;
        while (true) {
            if (result[i] == '1') {
                return ++i;
            } else {
                i++;
            }
        }
    }

    public static ArrayList<Integer> find_multiple(byte n, int i) {
        ArrayList<Integer> m1 = new ArrayList<Integer>();
        for (; i < Short.MAX_VALUE; i++) {
            if (i % n == 0) {
                m1.add(i);
            }
        }
        return m1;
    }

    public static ArrayList<Integer> find_non_multiple(byte n, int i) {
        ArrayList<Integer> m2 = new ArrayList<Integer>();
        int j = Short.MIN_VALUE;
        for (; j <= i; j++) {
            if (i % n != 0) {
                m2.add(j);
            }
        }
        return m2;
    }

    public static void write_to_file(int i, byte n, ArrayList<Integer> m1, ArrayList<Integer> m2) {
        try (FileWriter writer = new FileWriter("result.txt", false)) {
            // запись всей строки

            String text_1 = "число i =" + i + '\n';
            String text_2 = "номер старшего значащего бита n =" + n + '\n';
            String text_3 = "массив чисел,кратных n, в диапазоне от  i до Short.Max_value, m1 =" + m1 + '\n';
            String text_4 = "массив чисел,некратных n, в диапазоне от  Short.min_value до i, m2 =" + m2 + '\n';
            writer.write(text_1);
            writer.write(text_2);
            writer.write(text_3);
            writer.write(text_4);
            writer.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

}
