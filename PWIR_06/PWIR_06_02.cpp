#include <cstdio>
#include <cstdint>
#include <cstdlib>
#include <chrono>
#include <assert.h>
#include <omp.h>
#include <iostream>

void somethingLong(uint64_t* result) {
    uint64_t Bufery = 0;
    for (uint64_t i = 0;i < UINT32_MAX;i++)
        Bufery += i;
    *result = Bufery;
}
void somethingLongHalf(uint64_t* result) {
    uint64_t Bufery = 0; uint64_t Buferx = 0;
    uint64_t i; uint64_t a;
#pragma omp parallel for \
        num_threads(2) \
        private(i) \
        reduction(+ : Bufery)
    for (i = 0;i < UINT32_MAX / 2;i++) {
        Bufery += i;
    }
    printf("Pomiar 1 %I64d\n", Bufery);
#pragma omp parallel for \
        num_threads(2) \
        private(i) \
        reduction(+ : Buferx)
    for (a = UINT32_MAX / 2 + 1;a < UINT32_MAX;a++) {
        Buferx += a;
    }
    printf("Pomiar 2 %I64d\n", Buferx);
}
int main() {
    uint64_t result1 = 0;
    uint64_t result2 = 0;

    auto start = std::chrono::high_resolution_clock::now();
    somethingLong(&result1);
    auto end = std::chrono::high_resolution_clock::now();

    printf("Calculated normal way in %llu miliseconds\n",
        std::chrono::duration_cast<std::chrono::milliseconds>(end - start).count());

    start = std::chrono::high_resolution_clock::now();
    somethingLongHalf(&result2);
    end = std::chrono::high_resolution_clock::now();

    printf("Calculated on 2 threads in %llu miliseconds\n",
        std::chrono::duration_cast<std::chrono::milliseconds>(end - start).count());

    return 0;
}