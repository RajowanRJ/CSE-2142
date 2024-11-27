import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Invalid number of arguments.");
            return;
        }

        switch (args[0].charAt(0)) {
            case 'l':
                loadAndListEmployees();
                break;
            case 's':
                loadAndShowRandomEmployee();
                break;
            case '+':
                addEmployee(args[0].substring(1));
                break;
            case '?':
                searchEmployee(args[0].substring(1));
                break;
            case 'c':
                countWordsAndCharacters();
                break;
            case 'u':
                updateEmployee(args[0].substring(1));
                break;
            case 'd':
                deleteEmployee(args[0].substring(1));
                break;
            default:
                System.out.println("Invalid argument.");
        }
    }

    private static void loadAndListEmployees() {
        System.out.println("Loading data ...");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")))) {
            String line = reader.readLine();
            String[] employees = line.split(",");
            for (String emp : employees) {
                System.out.println(emp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Data Loaded.");
    }

    private static void loadAndShowRandomEmployee() {
        System.out.println("Loading data ...");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")))) {
            String line = reader.readLine();
            String[] employees = line.split(",");
            Random rand = new Random();
            int index = rand.nextInt(employees.length);
            System.out.println(employees[index]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Data Loaded.");
    }

    private static void addEmployee(String name) {
        System.out.println("Loading data ...");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt", true))) {
            writer.write(", " + name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Data Loaded.");
    }

    private static void searchEmployee(String name) {
        System.out.println("Loading data ...");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")))) {
            String line = reader.readLine();
            String[] employees = line.split(",");
            boolean found = false;
            for (String emp : employees) {
                if (emp.equals(name)) {
                    System.out.println("Employee found!");
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Employee not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Data Loaded.");
    }

    private static void countWordsAndCharacters() {
        System.out.println("Loading data ...");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")))) {
            String line = reader.readLine();
            int wordCount = line.split(",").length;
            int charCount = line.length();
            System.out.println(wordCount + " word(s) found " + charCount + " character(s) found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Data Loaded.");
    }

    private static void updateEmployee(String name) {
        System.out.println("Loading data ...");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")))) {
            String line = reader.readLine();
            String[] employees = line.split(",");
            for (int i = 0; i < employees.length; i++) {
                if (employees[i].equals(name)) {
                    employees[i] = "Updated";
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"))) {
                writer.write(String.join(",", employees));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Data Updated.");
    }

    private static void deleteEmployee(String name) {
        System.out.println("Loading data ...");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")))) {
            String line = reader.readLine();
            String[] employees = line.split(",");
            List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
            employeeList.remove(name);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"))) {
                writer.write(String.join(",", employeeList));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Data Deleted.");
    }
}
