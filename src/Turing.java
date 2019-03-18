import java.util.Scanner;

/*
* Alphabet: [1, ..., n, a, R, L, #, q]
* Inverse works :)
* */

public class Turing {
  private String word;
  private String instructions[];
  private String result;
  private char pointer;
  private int pointerIndex;

  public Turing (int instructionsAmount, String word)
  {
    Scanner scanner = new Scanner(System.in);
    this.instructions = new String[instructionsAmount];
    System.out.println("Input instructions:");
    for (int i = 0; i < instructionsAmount; i++) {
      this.instructions[i] = scanner.nextLine();
    }
    this.word = "##" + word + "##";
    this.pointer = this.word.charAt(1);
    this.pointerIndex = 1;
    this.work();
    System.out.println("Result: " + this.result);
  }

  private void work ()
  {
    String currentOperation = "q1";
    char newChar;
    String buffer;
    char[] temp = this.word.toCharArray();
    do {
      for (int i = this.pointerIndex; i < temp.length - 1; i++) {
        for (String instruction: this.instructions) {
          if (instruction.substring(0, 2).equals(currentOperation)) {
            if (temp[i] == instruction.charAt(3)) {
              currentOperation = instruction.substring(8, 10);
              newChar = instruction.charAt(11);
              if (temp[i] == '#') {
                if (!(i <= 1)) {
                  temp[i] = newChar;
                  buffer = String.valueOf(temp) + "#";
                  temp = buffer.toCharArray();
                } else if (newChar == '#') {
                  temp[i] = newChar;
                }
              } else {
                temp[i] = newChar;
              }
              this.result = String.valueOf(temp);
              if (instruction.charAt(instruction.length() - 1) == 'R') {
                this.pointer = temp[i + 1];
                this.pointerIndex = i + 1;
                break;
              }
              if (instruction.charAt(instruction.length() - 1) == 'L') {
                this.pointer = temp[i - 1];
                this.pointerIndex = i - 1;
                i -= 2;
                break;
              }
              if (currentOperation.equals("q0")) {
                if (this.pointer == '#') {
                  this.result = "0";
                  return;
                }
                return;
              }
              break;
            }
          }
        }
      }
    } while (!currentOperation.equals("q0"));
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input amount of instructions and word");
    Turing turing = new Turing(sc.nextInt(), sc.next());
  }
}
