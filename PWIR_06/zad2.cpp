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

void somethingLong2(uint64_t* result){
    uint64_t buf = 0;
    uint64_t i;
    #pragma omp parallel for \
        num_threads(2) \
        private(i) \
        reduction(+ : buf)
    for(i = 0;i<UINT32_MAX;i++)
        buf+=i;
    *result = buf;
}

int main(){
    uint64_t result1 = 0;
    uint64_t result2 = 0;

    auto start = std::chrono::high_resolution_clock::now();
    somethingLong(&result1);
    auto end = std::chrono::high_resolution_clock::now();

    printf("Calculated normal way in %llu miliseconds\n", 
    std::chrono::duration_cast<std::chrono::milliseconds>(end - start).count());

    start = std::chrono::high_resolution_clock::now();
    somethingLong2(&result2);
    end = std::chrono::high_resolution_clock::now();

    printf("Calculated on 2 threads in %llu miliseconds\n",
    std::chrono::duration_cast<std::chrono::milliseconds>(end - start).count());

    system("PAUSE");
    return 0;
}