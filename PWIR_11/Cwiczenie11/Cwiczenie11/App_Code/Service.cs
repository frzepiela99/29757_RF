using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

// UWAGA: możesz użyć polecenia „Zmień nazwę” w menu „Refaktoryzuj”, aby zmienić nazwę klasy „Service” w kodzie, usłudze i pliku konfiguracji.
public class Service : IService
{
    public void DoWork()
    {
    }
    public int fib(int a)
    {

        if (a == 0 || a == 1)
        {
            return a;
        }

        else
        {
            return fib(a - 1) + fib(a - 2);
        }

    }
    public int silnia(int n)
    {

        if (n == 0 || n == 1)
        {
            return 1;
        }

        else
        {
            return n * silnia(n - 1);
        }

    }



    public double Pole_prost(double a, double b)
    {
        if (a > 0 && b > 0)
        {

            return a * b;
        }


        else
        {
            return 0;
        }
    }

    public string trojkaty(double a, double b, double c)
    {

        if (a > 0 && b > 0 && c > 0)
        {
            if (a + b > c && a + c > b && b + c > a)
            {
                return "Jest trójkąt";
            }
            else
            {
                return "Nie ma trójąta";
            }

        }
        else
        {
            return "Boki nie mogą być miejsze lub równe 0";
        }

    }

}
