using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

// UWAGA: możesz użyć polecenia „Zmień nazwę” w menu „Refaktoryzuj”, aby zmienić nazwę interfejsu „IService” w kodzie i pliku konfiguracji.
[ServiceContract]
public interface IService
{
    [OperationContract]
    void DoWork();

    [OperationContract]
    int fib(int a);

    [OperationContract]
    int silnia(int n);

    [OperationContract]
    double Pole_prost(double a, double b);

    [OperationContract]
    string trojkaty(double a, double b, double c);

}
