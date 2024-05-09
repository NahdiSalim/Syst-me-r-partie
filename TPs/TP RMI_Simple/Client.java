import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Calculatrice calc = (Calculatrice) registry.lookup("Calculatrice");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Entrez l'opération (Addition, Soustraction, Multiplication, Division) : ");
                String operation = scanner.next();
                System.out.print("Entrez le premier opérande : ");
                double a = scanner.nextDouble();
                System.out.print("Entrez le deuxième opérande : ");
                double b = scanner.nextDouble();

                double resultat = 0;
                switch (operation.toLowerCase()) {
                    case "addition":
                        resultat = calc.addition(a, b);
                        break;
                    case "soustraction":
                        resultat = calc.soustraction(a, b);
                        break;
                    case "multiplication":
                        resultat = calc.multiplication(a, b);
                        break;
                    case "division":
                        resultat = calc.division(a, b);
                        break;
                    default:
                        System.out.println("Opération non reconnue.");
                        continue;
                }

                System.out.println("Résultat: " + resultat);
                System.out.print("Voulez-vous continuer ? (Oui/Non) : ");
                if (scanner.next().equalsIgnoreCase("Non")) break;
            }
            scanner.close();
        } catch (Exception e) {
            System.err.println("Exception client: " + e.toString());
            e.printStackTrace();
        }
    }
}
