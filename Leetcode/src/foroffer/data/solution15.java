package foroffer.data;

public class solution15 {
    public static void main(String[] args) {
        String s = "  good  i am";
        System.out.println(reverseWords(s));
    }

    public static String reverseWords(String s) {
        s = s.strip();
        String[] strings = s.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = strings.length - 1; i > 0; i--) {
            if (!strings[i].equals(""))
                stringBuilder.append(strings[i].strip()).append(' ');
        }
        stringBuilder.append(strings[0].strip());
        return new String(stringBuilder);
    }
}
