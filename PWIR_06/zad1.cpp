#include <cstdio>
#include <cstdint>
#include <cstdlib>
#include <chrono>
#include <assert.h>
#include <omp.h>
#include <iostream>

void somethingLong(uint64_t* result){
    uint64_t buf = 0;
    for(uint64_t i = 0;i<UINT32_MAX;i++)
        buf+=i;
    *result = buf;
}

int main(){
    uint64_t result = 0;
    #pragma omp parallel sections private(result)
    {
        #pragma omp section
        {
            somethingLong(&result);
            printf("1 czesc kodu -> watek %d\n", omp_get_thread_num());
        }
        #pragma omp section
        {
            somethingLong(&result);
            printf("2 czesc kodu -> watek %d\n", omp_get_thread_num());
        }
        #pragma omp section
        {
            somethingLong(&result);
            printf("3 czesc kodu -> watek %d\n", omp_get_thread_num());
        }
        #pragma omp section
        {
            somethingLong(&result);
            printf("4 czesc kodu -> watek %d\n", omp_get_thread_num());
        }
    }

    system("PAUSE");
    return 0;
}