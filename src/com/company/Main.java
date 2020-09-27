package com.company;


import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = 0;

        System.out.println("Нажмите 1, если хотите проверить эвкивалентность двух многочленов");
        System.out.println("Нажмите 2, если хотите найти конкретное значение многочлена");
        System.out.println("Нажмите 3, если хотите сложить два многочлена");
        do {
            while (!in.hasNextInt()) {
                System.out.println("Неверный ввод");
                in.next();
            }

            n = in.nextInt();
            if (n < 1 || n > 3) System.out.println("Некорректный ввод, попробуйте еще раз");
        } while (n < 1 || n > 3);
        if (n == 1) {
            System.out.println("Введите степень первого многочлена");
            List l1 = vvod(in);

            System.out.println("Введите степень второго многочлена");
            List l2 = vvod(in);
            System.out.println(Equality(l1, l2));
        }
        if (n == 2) {
            System.out.println("Введите степень многочлена");
            List l3 = vvod(in);
            System.out.println("Введите x");
            while (!in.hasNextInt()) {
                System.out.println("Неверный ввод");
                in.next();
            }

            n = in.nextInt();
            System.out.println(Meaning(n, l3));
        }
        if (n == 3) {
            System.out.println("Введите степень первого многочлена");
            List l4 = vvod(in);
            System.out.println("Введите степень второго многочлена");
            List l5 = vvod(in);
            List p = new List();
            add(p, l4, l5);
            p.vyvod();
        }

    }

    static int Meaning(int x, List list) {
        int sum = 0;
        for (int i = 0; i < list.size; i++) {
            sum += list.getEl(i).a * Math.pow(x, list.getEl(i).n);
        }
        return sum;

    }

    static List vvod(Scanner in) {
        int step;


        step = in.nextInt();

        int a;
        List list = new List();
        for (int i = step; i >= 0; i--) {
            System.out.printf("Введите коэффициент a для многочлена х^%d\t", i);

            while (!in.hasNextInt()) {
                System.out.println("Неверный ввод");
                in.next();



            }
            a = in.nextInt();
            if (a != 0)
                list.addEl(i, a);
        }
        return list;
    }

    static boolean Equality(List p, List q) {
        if (p.size == q.size) {
            for (int i = 0; i < p.size; i++) {
                if (p.getEl(i).n == q.getEl(i).n && p.getEl(i).a == q.getEl(i).a) continue;
                else return false;
            }
            return true;
        }
        return false;
    }

    static void add(List p, List q, List r) {


        int i = 0;
        int j = 0;


        while (true) {
            if (i != q.size || j != r.size) {
                if (q.getEl(i).n > r.getEl(j).n) {
                    p.addEl(q.getEl(i).n, q.getEl(i).a);
                    i++;

                } else if (q.getEl(i).n < r.getEl(j).n) {
                    p.addEl(r.getEl(j).n, r.getEl(j).a);
                    j++;
                } else if (q.getEl(i).n == r.getEl(j).n) {
                    int A = q.getEl(i).a + r.getEl(j).a;
                    p.addEl(q.getEl(i).n, A);
                    i++;
                    j++;
                }
            }
            if (i == q.size && j != r.size) {
                while (j != r.size) {
                    p.addEl(r.getEl(j).n, r.getEl(j).a);
                    j++;
                }


            }
            if (j == r.size && i != q.size) {

                while (i != q.size) {
                    p.addEl(q.getEl(i).n, q.getEl(i).a);
                    i++;
                }
            }
            if ((i == q.size) && (j == r.size))
                break;
        }
    }


}


class Link {
    public Link next;
    int n;
    int a;

    Link(int n, int a) {
        this.n = n;
        this.a = a;

    }


    public int getA() {
        return a;
    }

    public int getN() {
        return n;
    }


    public void setA(int a) {
        this.a = a;
    }

    public void setN(int n) {
        this.n = n;
    }
}

class List {
    Link first;
    int size;

    List() {

    }

    public void addEl(int n, int a) {
        if (first == null) {
            first = new Link(n, a);
            size++;
        } else {
            Link current = first;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Link(n, a);
            size++;
        }


    }

    public Link getEl(int index) {
        Link current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    public void vyvod() {
        Link current = first;
        for (int i = 0; i < size; i++) {
            if (current.next != null) {
                System.out.printf("%d*x^%d+", current.a, current.n);
                current = current.next;
            } else {
                System.out.printf("%d*x^%d", current.a, current.n);
                break;
            }
        }

    }
}


