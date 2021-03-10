#include <cstdio>
#include <cstdint>
#include <cstdlib>
#include <chrono>
#include <assert.h>

#define MUL_TIME 25

uint32_t i;
uint32_t k;
uint32_t max=10000;
uint16_t liczba=1;

int main(){

int wynik=0;
auto start = std::chrono::high_resolution_clock::now();
auto end = std::chrono::high_resolution_clock::now();

for(int j=0; j<3; j++){
    start = std::chrono::high_resolution_clock::now();
    #pragma omp parallel for schedule(static) shared(liczba) private(i,k)
    for(i = 0;i<max;i++){
        for(k = 0;k<max;k++){
            liczba*=2; 
        }        
    }
    end = std::chrono::high_resolution_clock::now();
    printf("Calculated parallel static way in %llu miliseconds\n", 
    std::chrono::duration_cast<std::chrono::milliseconds>(end - start).count());
    wynik=wynik+(end-start).count();
    liczba=1;
}
    printf("Average static way 3 times: %llu miliseconds\n\n", wynik/3000000);
    wynik=0;

//-------------------------------------|||||||||||||||--------------------------------//

for(int j=0; j<3; j++){
    start = std::chrono::high_resolution_clock::now();
    #pragma omp parallel for schedule(dynamic) shared(liczba) private(i,k)
    for(i = 0;i<max;i++){
        for(k = 0;k<max;k++){
            liczba*=2; 
        }        
    }
    end = std::chrono::high_resolution_clock::now();
    printf("Calculated parallel dynamic way in %llu miliseconds\n", 
    std::chrono::duration_cast<std::chrono::milliseconds>(end - start).count());
    wynik=wynik+(end-start).count();
    liczba=1;
}
    printf("Average dynamic way 3 times: %llu miliseconds\n\n", wynik/3000000);
    wynik=0;

//-------------------------------------|||||||||||||||--------------------------------//


for(int j=0; j<3; j++){
    start = std::chrono::high_resolution_clock::now();
    #pragma omp parallel for schedule(guided) shared(liczba) private(i,k)
    for(i = 0;i<max;i++){
        for(k = 0;k<max;k++){
            liczba*=2; 
        }        
    }
    end = std::chrono::high_resolution_clock::now();
    printf("Calculated parallel guided way in %llu miliseconds\n", 
    std::chrono::duration_cast<std::chrono::milliseconds>(end - start).count());
    wynik=wynik+(end-start).count();
    liczba=1;
}
    printf("Average guided way 3 times: %llu miliseconds\n\n", wynik/3000000);
    wynik=0;


//-------------------------------------|||||||||||||||--------------------------------//

for(int j=0; j<3; j++){
    start = std::chrono::high_resolution_clock::now();
    #pragma omp parallel for schedule(runtime) shared(liczba) private(i,k)
    for(i = 0;i<max;i++){
        for(k = 0;k<max;k++){
            liczba*=2; 
        }        
    }
    end = std::chrono::high_resolution_clock::now();
    printf("Calculated parallel runtime way in %llu miliseconds\n", 
    std::chrono::duration_cast<std::chrono::milliseconds>(end - start).count());
    wynik=wynik+(end-start).count();
    liczba=1;
}
    printf("Average runtime way 3 times: %llu miliseconds\n\n", wynik/3000000);
    wynik=0;


    return 0;
}
