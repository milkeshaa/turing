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
    this.work();
    System.out.println("Result: " + this.result);
  }

  private void work ()
  {
    String currentOperation = "q1";
    char newChar;
    char[] temp = this.word.toCharArray();
    for (int i = 1; i < this.word.length() - 1; i++) {
      for (String instruction: this.instructions) {
        if (instruction.substring(0, 2).equals(currentOperation)) {
          if (this.word.charAt(i) == instruction.charAt(3)) {
            currentOperation = instruction.substring(8, 10);
            newChar = instruction.charAt(11);
            temp[i] = newChar;
            this.result = String.valueOf(temp);
            if (instruction.charAt(instruction.length() - 1) == 'R') {
              this.pointer = this.word.charAt(i + 1);
              break;
            }
            if (instruction.charAt(instruction.length() - 1) == 'L') {
              this.pointer = this.word.charAt(i - 1);
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
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input amount of instructions and word");
    Turing turing = new Turing(sc.nextInt(), sc.next());
  }
}
