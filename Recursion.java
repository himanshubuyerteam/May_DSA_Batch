class Recursion
{

    // public static void pd(int n)
    // {
    //     for(int i=n;i>=1;i--)
    //     {
    //         System.out.println(i);
    //     }
    // }
    //SC-o(n)
    //Tc-o(n)
    public static void pd(int n)
    {
        if(n==0)
            return;
        System.out.println(n);
        pd(n-1);
    }

    
    //SC-o(n)
    //Tc-o(n)
    public static void pi(int n)
    {
        if(n==0)
            return;
        pi(n-1);
        System.out.println(n);
    }

    //SC-o(n)
    //Tc-o(n)
    public static void pdi(int n)
    {
        if(n==0)
            return;
        System.out.println(n);
        pdi(n-1);
        System.out.println(n);
    }
    public static void f2()
    {
        System.err.println("F2 L2");
    }

    //SC-o(n)
    //Tc-o(n)
    public static int fact(int n)
    {
        if(n==0)
            return 1;
        int rans = fact(n-1);
        int mans = rans*n;
        return mans;
    }
    //SC-o(n)
    //Tc-o(n)
    public static int product(int a,int b)
    {
        if(b==1)
            return a;
        int rans = product(a, b-1);
        int mans = rans + a;
        return mans;
    }


    public static int product_line(int a,int b)
    {
        if(b==1)
            return a;
        return product_line(a, b-1)+a;
    }
    //SC-o(n)
    //Tc-o(n)
    public static int pow(int base, int power)
    {
        if(power==1)
            return base;
        int rans = pow(base, power-1);
        int mans = rans*base;
        return mans;
    }
    //SC-o(log n)
    //Tc-o(log n)
    public static int pow_opt(int base,int power)
    {
        if(power == 1)
            return base;
        int rans = pow_opt(base, power/2);
        if(power%2!=0)
            rans = rans * rans * base;
        else
            rans = rans * rans;
        return rans;
    }

    public static int fib(int n)
    {
        if(n<=1)
            return n;
        int ra1 = fib(n-1);
        int ra2 = fib(n-2);
        int mans = ra1+ra2;
        return mans;
    }
    public static int climbStair(int n)
    {
        if(n<=1)
            return 1;
        int ra1 = climbStair(n-1);
        int ra2= climbStair(n-2);
        return ra1+ra2;
    }
    public static int mazePath(int sr,int sc,int dr,int dc)
    {
        if(sr==dr && sc ==dc)
            return 1;
        if(sr>dr || sc>dc)
            return 0;
        int ra1 = mazePath(sr, sc+1, dr, dc);
        int ra2 = mazePath(sr+1, sc, dr, dc);
        return ra1+ra2;
    }
    public static int mazePath_path(int sr,int sc,int dr,int dc,String path)
    {
        if(sr==dr && sc ==dc)
        {
            System.out.println(path);
            return 1;
        }
        if(sr>dr || sc>dc)
            return 0;
        int ra1 = mazePath_path(sr, sc+1, dr, dc,path+"R");
        int ra2 = mazePath_path(sr+1, sc, dr, dc,path+"D");
        return ra1+ra2;
    }
    public static void f1()
    {
        System.out.println("F1 L1");
        f2();
    }
    public static void print()
    {
        System.out.println("Hello");
        f1();
        System.out.println("Bye");
    }
    public static void main(String[] args) {
        // print();
        // pd(5);
        // pi(5);
        // pdi(5);
        int ans = mazePath_path(0,0,2,2,"");
        // System.out.println(ans);
        System.out.println("Number of Ways is -->"+ans);
        
    }
}