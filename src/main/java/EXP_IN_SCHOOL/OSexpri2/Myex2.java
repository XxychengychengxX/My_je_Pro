package EXP_IN_SCHOOL.OSexpri2;

import java.util.Arrays;

public class Myex2 implements Runnable {
    public int status;
    public int[] a;

    public Myex2(int status, int[] a) {
        this.status = status;
        this.a = a;
    }

    public Myex2(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int[] getA() {
        return a;
    }

    public void setA(int[] a) {
        this.a = a;
    }

    @Override
    public void run() {
        int status = getStatus();
        int[] a = getA();
        switch (status) {
            case 0 -> getmax(a);
            case 1 -> getaverage(a);
            case 2 -> getmin(a);
        }
    }

    public void getmax(int[] a) {
        if (a.length != 0)
            System.out.println(Arrays.stream(a).max().getAsInt());
    }

    public void getaverage(int[] a) {
        if (a.length != 0) {
            if (Arrays.stream(a).average().isPresent()
            ) {
                System.out.println(Arrays.stream(a).average().getAsDouble());
            }
        }
    }

    public void getmin(int[] a) {
        if (a.length != 0)
            System.out.println(Arrays.stream(a).min().getAsInt());
    }
}
