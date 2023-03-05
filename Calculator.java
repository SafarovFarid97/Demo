import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.in;

public class Calculator {




    public static void main(String[] args) throws Exception {
        System.out.println("Введите выражение");
        Scanner enter = new Scanner(in);
        String input = enter.nextLine();
        enter.close();
        Count count = new Count();
        String [] massiv = input.split("\\"+count.sing(input));
        String number1 =  count.transmissionWithoutEscape(massiv[0]);
        String number2 =  count.transmissionWithoutEscape(massiv[1]);




        if(count.way(number1,number2) == 1) {
            try {
                Rome romeNumber1 = Rome.valueOf(Rome.class, number1);
                Rome romeNumber2 = Rome.valueOf(Rome.class, number2);
                int answer = count.answer(count.sing(input), romeNumber1.getTranslator(), romeNumber2.getTranslator());
                System.out.println(count.roman(answer));
            }
            catch (IllegalArgumentException e){
                throw new Exception("throws Exception //т.к. калькулятор должен принимать на вход целые числа от 1 до 10 включительно");
            }
        }

        else{
                int num1 = count.transmission(number1);//1 число
                int num2 = count.transmission(number2);//2 число
                int num3 = count.answer(count.sing(input), num1, num2);//ответ
                System.out.print(num3);
            }




    }

}
class Count {

    private int answer, way;

    private String rome = "";
    private char result;

    int transmission(String number) throws Exception {

        double number2 = 0;


        for (int i=0; i<number.length(); i++) {
            switch (number.charAt(i)) {
                case '1':
                    number2 = number2 + 1 * Math.pow(10, number.length()- i - 1);
                    break;
                case '2':
                    number2 = number2 + 2 * Math.pow(10, number.length() - i - 1);
                    break;
                case '3':
                    number2 = number2 + 3 * Math.pow(10, number.length() - i - 1);
                    break;
                case '4':
                    number2 = number2 + 4 * Math.pow(10, number.length() - i - 1);
                    break;
                case '5':
                    number2 = number2 + 5 * Math.pow(10, number.length() - i - 1);
                    break;
                case '6':
                    number2 = number2 + 6 * Math.pow(10, number.length() - i - 1);
                    break;
                case '7':
                    number2 = number2 + 7 * Math.pow(10, number.length() - i - 1);
                    break;
                case '8':
                    number2 = number2 + 8 * Math.pow(10, number.length() - i - 1);
                    break;
                case '9':
                    number2 = number2 + 9 * Math.pow(10, number.length() - i - 1);
                    break;
                case'0':
                    number2 = number2;
                    break;
                default:
                        throw new Exception("throws Exception //т.к. калькулятор должен принимать на вход целые числа от 1 до 10 включительно");
            }
            ;
        }
        int number1 = (int) number2;

        if (number1>10 || number1<1) {
            throw new Exception ("throws Exception //т.к. калькулятор должен принимать на вход целые числа от 1 до 10 включительно");
            }

        return number1;

    }//изменение символов на целые числа

    String transmissionWithoutEscape(String number){

        int first = -1;
        int end = -1;
        int q=0;

        do{
            if(number.charAt(q) != ' '){
                first = q;
            }
            q+=1;
        } while (first == -1);
        q-=1;

        do{
            q+=1;
            if(number.charAt(q-1) == ' '){
                end = q-1;
            }
        } while((number.length() != q) && (number.charAt(q-1) != ' '));


        if(end == -1){
            end = number.length();
        }
        return number.substring(first,end);
    }//Возвращает строку без пробелов


    int answer(char sign, int number1, int number2) {
        switch (sign) {
            case '-':
                answer = number1 - number2;
                break;
            case '+':
                answer = number1 + number2;
                break;
            case '/':
                answer = number1 / number2;
                break;
            case '*':
                answer = number1 * number2;
                break;
        }
        return answer;
    }//блок расчета

    int way(String number1, String number2) throws Exception{
        if ((number1.charAt(0) == 'I' || number1.charAt(0) == 'V' || number1.charAt(0) == 'X')&&(number2.charAt(0) == 'I' || number2.charAt(0) == 'V' || number2.charAt(0) == 'X')) {
            way = 1;
        }
        else if ((number1.charAt(0) == '1' || number1.charAt(0) == '2' || number1.charAt(0) == '3' || number1.charAt(0) == '4'
                || number1.charAt(0) == '5' || number1.charAt(0) == '6' || number1.charAt(0) == '7' || number1.charAt(0) == '8'
                || number1.charAt(0) == '9') && (number2.charAt(0) == '1' || number2.charAt(0) == '2' || number2.charAt(0) == '3'
                || number2.charAt(0) == '4' || number2.charAt(0) == '5' || number2.charAt(0) == '6' || number2.charAt(0) == '7'
                || number2.charAt(0) == '8' || number2.charAt(0) == '9')){
            way = 0;
        }
        else{
            throw new Exception("throws Exception //т.к. используются одновременно разные системы счисления");
        }

        return way;
    }//тип чисел

    String roman (int answer) throws Exception {
        if (answer < 0) {
            throw new Exception("throws Exception //т.к результат работы калькулятора с римскими числами отрицательный");
        }
        do {
            if (answer - 1 < 0) {
                rome += "0";
            } else {
                if (answer - 5 < 0) {
                    rome += "I";
                    answer -= 1;
                } else {
                    if (answer - 10 < 0) {
                        rome += "V";
                        answer -= 5;
                    } else {
                        if (answer - 50 < 0) {
                            rome += "X";
                            answer -= 10;
                        } else {
                            if (answer - 100 < 0) {
                                rome += "L";
                                answer -= 50;
                            } else {
                                if (answer - 500 < 0) {
                                    rome += "C";
                                    answer -= 100;
                                } else {
                                    rome += "500>";
                                    answer = 0;
                                }
                            }

                        }
                    }
                }
            }
        }while(answer !=0);
        return rome;
    }//Перевод в римские цифры

    char sing (String insert) throws Exception{
        int count=0;
        for(int i=0; i<insert.length();i++){
            switch (insert.charAt(i)){
                case '+':
                    result = '+';
                    count+=1;
                    break;
                case '-':
                    result = '-';
                    count+=1;
                    break;
                case '*':
                    result = '*';
                    count+=1;
                    break;
                case '/':
                    result='/';
                    count+=1;
                    break;

            }
        }
        if (count != 1){
            throw new Exception("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        return result;
    }//знак расчета
}
