package org.example;

public class Calculator {

    int a;
    int b;

    public Calculator(int a, int b) {
        this.a = a;
        this.b = b;
    }


    public Calculator() {
    }

    public String ActionApplier(int a, String act, int b){
        String result = "";
        switch (act) {
            case("+"):
                result = CalcSum(a,b).toString();
                break;
            case("-"):
                result = CalcDiff(a,b).toString();
                break;
            case("*"):
                result = CalcProduct(a,b).toString();
                break;
            case("/"):
                result = CalcDivision(a,b).toString();
                break;
        }return result;
    }

    public Integer CalcSum(int a, int b){
        int Sum = a + b;
        return Sum;
    }

    public Integer CalcProduct(int a, int b){
        int Product = a * b;
        return Product;
    }

    public Integer CalcDiff(int a, int b){
        int Diff = a - b;
        return Diff;
    }

    public Double CalcDivision (int a, int b){
        Double Division = (double) a / b;
        return Division;
    }


}
