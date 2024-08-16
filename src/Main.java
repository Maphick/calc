import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        input_output();
    }


    public static void input_output() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.printf("Добро пожаловать в приложение \"Калькулятор\"!");
        System.out.printf("\nДля сброса нажмите 'c'/'C'");
        System.out.printf("\nДля выхода 's'/'s'");
        int operand_0 = 0;
        System.out.printf("\nВведите первый операнд:\n");
        // вводим первый операнд и проверям его валидность
            Calculate.clear = false;
            operand_0 = operand_validation(reader, false);
            if (Calculate.exit)
                return;
        while (true) {
            // если очистили результат
            if (Calculate.clear)
            {
                Calculate.clear = false;
                // проверка валидности
                System.out.printf("\nВведите первый операнд:\n");
                // пока не нажали 's'/'S'
                // вводим первый операнд и проверям его валидность
                operand_0 = operand_validation (reader, false);
                if (Calculate.exit)
                    return;
                if (Calculate.clear)
                    continue;
            }
            // вводим операцию и проверям ее валидность
            char operation = operation_validation(reader);
            if (Calculate.exit)
                break;
            if (Calculate.clear) {
                continue;
            }
            System.out.printf("Введите второй операнд:\n");
            // вводим второй операнд и проверям его валидность
            boolean div = (operation == '/');
            int operand_1 = operand_validation(reader, div);
            if (Calculate.exit)
                break;
            if (Calculate.clear)
                continue;
            // вычисления
            operand_0 = calculation(operand_0, operation, operand_1);
            System.out.printf("Результат: \n" + operand_0 + "\n");
        }
    }


    // вычисления
    private static int calculation(int operand_0, char operation, int operand_1)
    {
        int result = 0;
        switch (operation) {
            case ('+'):
                result = Calculate.addition(operand_0, operand_1);
                break;
            case ('-'):
                result = Calculate.subtraction(operand_0, operand_1);
                break;
            case ('*'):
                result = Calculate.multiplication(operand_0, operand_1);
                break;
            case ('/'):
                result = Calculate.division(operand_0, operand_1);
                break;
        }
        return result;
    }


    // проверка на нажатие s/S для выхода из приложения
    public static boolean exit(String symb)
    {
        if (symb.toLowerCase().equals("s"))
        {
            Calculate.exit = true;
            return true;
        }
        return false;
    }

    // проверка на нажатие c/C для обнуления вычислений
    public static boolean clear(String symb)
    {
        if (symb.toLowerCase().equals("c"))
        {
            Calculate.clear = true;
            return true;
        }
        return false;
    }


    // проверка валидности операнда
    // div - проверка деления на 0
    public static int operand_validation (BufferedReader reader, boolean div) throws IOException {
        // правильный ввод данных с клавиатуры
        boolean success = false;
        // операнд
        int operand = 1;
        while (!success) {
            try {
                String line = reader.readLine();
                if (exit(line) || clear((line)))
                    return -1;
                operand = Integer.parseInt(line);
                if ((div) && (operand == 0)) {
                    success = false;
                    System.out.printf("\nНа 0 делить нельзя! Введите натуральное число!:\n");
                }
                else {
                    success = true;
                }
            } catch (NumberFormatException e) {
                success = false;
                System.out.printf("Не верное значение! Введите целое число!:\n");
            }
        }
        return operand;
    }


    // проверка валидности операции
    public static char operation_validation(BufferedReader reader) throws IOException {
        // прочитанная строка
        // правильный ввод данных с клавиатуры
        boolean success = false;
        // операция
        String operation = "+";
        // пока не ввели правильно
        while (!success) {
            System.out.printf("Введите операцию из списка:\n");
            System.out.printf("  +\n");
            System.out.printf("  -\n");
            System.out.printf("  *\n");
            System.out.printf("  /\n");
            String line = reader.readLine();
            if (exit(line) || clear((line)))
                return ' ';
            operation = line;
            if ((operation.equals("+")) || (operation.equals("-")) || (operation.equals("*"))|| (operation.equals("/")))
                success = true;
            else {
                System.out.printf("Не верное значение!\n");
            }
        }
        return operation.charAt(0);
    }


}