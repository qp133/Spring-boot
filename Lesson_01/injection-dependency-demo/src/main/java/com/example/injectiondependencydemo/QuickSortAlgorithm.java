package com.example.injectiondependencydemo;

import org.springframework.stereotype.Component;

@Component
public class QuickSortAlgorithm implements SortAlgorithm{
    public int[] sort(int[] numbers){
        qsort(numbers,0,numbers.length-1);
        return numbers;
    }

    int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    void qsort(int arr[], int low, int high) {
        if (low < high) {


            int pi = partition(arr, low, high);


            qsort(arr, low, pi - 1);
            qsort(arr, pi + 1, high);
        }
    }
}
