import java.rmi.Naming;
import java.util.Scanner;

public class CalculatorClient {
    public static void main(String[] args) {
        try {
            Calculator calculator = (Calculator) Naming.lookup("rmi://localhost/CalculatorService");

            Scanner scanner = new Scanner(System.in);

            do {
                System.out.println("Enter operation code (1: Add, 2: Subtract, 3: Multiply, 4: Divide): ");
                int operationCode = scanner.nextInt();

                System.out.println("Enter operand 1: ");
                double operand1 = scanner.nextDouble();

                System.out.println("Enter operand 2: ");
                double operand2 = scanner.nextDouble();

                double result = 0;

                switch (operationCode) {
                    case 1:
                        result = calculator.add(operand1, operand2);
                        break;
                    case 2:
                        result = calculator.subtract(operand1, operand2);
                        break;
                    case 3:
                        result = calculator.multiply(operand1, operand2);
                        break;
                    case 4:
                        result = calculator.divide(operand1, operand2);
                        break;
                    default:
                        System.out.println("Invalid operation code");
                        continue;
                }

                System.out.println("Result: " + result);

                System.out.println("Do you want to continue? (y/n): ");
            } while (scanner.next().equalsIgnoreCase("y"));

            System.out.println("Client is closing...");

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
