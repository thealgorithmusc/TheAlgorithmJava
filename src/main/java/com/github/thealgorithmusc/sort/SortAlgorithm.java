package com.github.thealgorithmusc.sort;

public class SortAlgorithm {

    // region Insertion sort

    /**
     * Insertion sort works the way many people sort a hand of playing cards. We
     * start with an empty left hand and the cards face down on the table.
     * We then remove one card at a time from the table and insert it into
     * the correct position in the left hand.
     *
     * @param list
     * @param <T>
     */
    public static <T extends Comparable<T>> void insertionSort(T[] list) {
        for (int i = 1; i < list.length; i++) {
            T key = list[i];
            int j = i - 1;
            while (j >= 0 && list[j].compareTo(key) > 0) {
                list[j + 1] = list[j];
                j--;
            }
            list[j + 1] = key;
        }
    }

    // endregion

    // region Selection sort

    /**
     * Sorting n numbers stored in array list by first finding the smallest element of
     * ls and exchanging it with the element list[0]. Then find the second smallest
     * element of ls, and exchange it with list[1]. Continue in this manner for the
     * first n-1 elements of the list.
     *
     * @param list
     * @param <T>
     */
    public static <T extends Comparable<T>> void selectionSort(T[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            int minindex = i;
            for (int j = i + 1; j < list.length; j++) {
                if (list[j].compareTo(list[minindex]) < 0) {
                    minindex = j;
                }
            }
            T temp = list[i];
            list[i] = list[minindex];
            list[minindex] = temp;
        }
    }

    // endregion

    // region Bubble sort

    /**
     * Repeatedly swapping adjacent elements that are out of order.
     *
     * @param list
     * @param <T>
     */
    public static <T extends Comparable<T>> void bubbleSort(T[] list) {
        for (int i = 0; i < list.length - 1; i++) { // need (list.length -1) bubble rounds
            for (int j = list.length - 1; j > i; j--) { // need (list.length - i - 1) bubble times in each round
                if (list[j].compareTo(list[j - 1]) < 0) {
                    T temp = list[j];
                    list[j] = list[j - 1];
                    list[j - 1] = temp;
                }
            }
        }
    }

    // endregion

    // region Merging sort

    /**
     * Divide the n-element sequence to be sorted into two subsequences of n/2 elements each.
     * Sort the two subsequence recursively using merge sort. Merge the two sorted subsequences
     * to produce the sorted answer.
     *
     * @param list
     * @param <T>
     */
    public static <T extends Comparable<T>> void mergeSort(T[] list) {
        if (list.length > 1) {
            int splitor = list.length / 2;
            T[] firstHalf = (T[]) new Comparable[splitor];
            System.arraycopy(list, 0, firstHalf, 0, firstHalf.length);
            mergeSort(firstHalf);

            T[] secondHalf = (T[]) new Comparable[list.length - splitor];
            System.arraycopy(list, splitor, secondHalf, 0, secondHalf.length);
            mergeSort(secondHalf);

            T[] temp = merge(firstHalf, secondHalf);
            System.arraycopy(temp, 0, list, 0, temp.length);
        }
    }

    /**
     * Merge sorted subsequences to produce a sorted sequence. Suppose we have two
     * piles of cards face up on a table. Each pile is sorted, with the smallest
     * cards on top. We wish to merge the two piles into a single sorted output pile.
     * Our basic step consists of choosing the smaller of the two cards on top of the
     * face-up piles, removing it from its pile, and placing this card to the output pile.
     *
     * @param list1
     * @param list2
     * @param <T>
     * @return
     */
    private static <T extends Comparable<T>> T[] merge(T[] list1, T[] list2) {
        T[] temp = (T[]) new Comparable[list1.length + list2.length];

        int current1 = 0, current2 = 0, current3 = 0;

        while (current1 < list1.length && current2 < list2.length) {
            if (list1[current1].compareTo(list2[current2]) < 0) {
                temp[current3++] = list1[current1++];
            } else {
                temp[current3++] = list2[current2++];
            }
        }

        while (current1 < list1.length)
            temp[current3++] = list1[current1++];

        while (current2 < list2.length)
            temp[current3++] = list2[current2++];

        return temp;
    }

    // endregion

    // region Quick sort

    public static <T extends Comparable<T>> void quickSort(T[] list) {
        quickSort(list, 0, list.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] list, int first, int last) {
        if (first < last) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] list, int first, int last) {
        T pivot = list[first];  // chose the first naiveMatch as pivot
        int low = first + 1;
        int high = last;
        while (low < high) {
            while (low <= high && list[low].compareTo(pivot) <= 0)
                low++;

            while (low <= high && list[high].compareTo(pivot) > 0)
                high--;

            if (high > low) {
                T temp = list[low];
                list[low] = list[high];
                list[high] = temp;
            }
        }

        while (high > first && list[high].compareTo(pivot) == 0)
            high--;

        if (pivot.compareTo(list[high]) > 0) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        } else {
            return first;
        }
    }

    // endregion

    // region Heap sort

    /**
     * 堆排序（采用上浮方式堆化）
     *
     * @param list 待排序的列表
     * @param <T>  泛型参数（可接受任意实现Comparable接口的类型）
     */
    public static <T extends Comparable<T>> void heapSortBySiftUp(T[] list) {
        for (int i = list.length - 1; i > 0; i--) {
            // 构建最大堆
            buildMaxHeapBySiftUp(list, i);

            // 将当前堆的第一个元素和当前堆的最后一个元素交换
            T temp = list[0];
            list[0] = list[i];
            list[i] = temp;
        }
    }

    /**
     * 堆排序（采用下沉方式堆化）
     *
     * @param list 待排序的列表
     * @param <T>  泛型参数（可接受任意实现Comparable接口的类型）
     */
    public static <T extends Comparable<T>> void heapSortBySiftDown(T[] list) {
        for (int i = list.length - 1; i > 0; i--) {
            // 构建最大堆
            buildMaxHeapBySiftDown(list, i);

            // 将当前堆的第一个元素和当前堆的最后一个元素交换
            T temp = list[0];
            list[0] = list[i];
            list[i] = temp;
        }
    }

    /**
     * 对当前堆调整为最大堆（采用上浮方式堆化）
     *
     * @param list 待排序的列表
     * @param last 用于指示堆边界
     * @param <T>  泛型参数（可接受任意实现Comparable接口的类型）
     */
    private static <T extends Comparable<T>> void buildMaxHeapBySiftUp(T[] list, int last) {
        for (int i = 0; i <= last; i++) {
            siftUp(list, i);
        }
    }

    /**
     * 对当前堆调整为最大堆（采用下沉方式堆化）
     *
     * @param list 待排序的列表
     * @param last 用于指示堆边界
     * @param <T>  泛型参数（可接受任意实现Comparable接口的类型）
     */
    private static <T extends Comparable<T>> void buildMaxHeapBySiftDown(T[] list, int last) {
        for (int i = (last - 1) / 2; i >= 0; i--) {
            siftDown(list, i, last);
        }
    }

    /**
     * 利用大元素上浮的操作进行堆化（Heapify）
     *
     * @param list       待排序的列表
     * @param entryIndex 堆入口在list中的索引
     * @see <a href="https://juejin.im/post/5bea6af051882548161b0f02">掘金 - 堆的动作 - 上浮</a>
     */
    private static <T extends Comparable<T>> void siftUp(T[] list, int entryIndex) {
        // entryIndex == 0 时表明上浮到根节点，结束上浮操作
        while (entryIndex > 0) {
            // 获取父节点索引
            int parent = (entryIndex - 1) / 2;

            // 小于父节点时退出，结束上浮操作
            if (list[entryIndex].compareTo(list[parent]) < 0) {
                break;
            }

            // 与父节点交换数据
            T temp = list[entryIndex];
            list[entryIndex] = list[parent];
            list[parent] = temp;

            // 改变 entryIndex 的指向 继续上浮
            entryIndex = parent;
        }
    }

    /**
     * 利用小元素下沉的操作进行堆化（Heapify）
     *
     * @param list       待排序的列表
     * @param entryIndex 堆入口在list中的索引
     * @param last       用于指示堆边界，即list的[0:last]为堆（由于下沉方式是从堆入口【根】开始往下方【子树】
     *                   沉降小的元素，因此需要利用last来分隔开list中已经排好序的元素.
     * @see <a href="https://juejin.im/post/5bea6af051882548161b0f02">掘金 - 堆的动作 - 下沉</a>
     * @see <a href="https://www.cnblogs.com/chengxiao/p/6129630.html">图解排序算法之堆排序</a>
     * @see <a href="https://blog.csdn.net/sunnylinner/article/details/52585225">
     * 堆排序Heap Sort——浅显易懂+Java实现</a>
     */
    private static <T extends Comparable<T>> void siftDown(T[] list, int entryIndex, int last) {
        int lcIndex = 2 * entryIndex + 1;
        int rcIndex = 2 * entryIndex + 2;

        int largest = entryIndex;
        if (lcIndex <= last && list[lcIndex].compareTo(list[entryIndex]) > 0) {
            largest = lcIndex;
        }
        if (rcIndex <= last && list[rcIndex].compareTo(list[largest]) > 0) {
            largest = rcIndex;
        }

        if (largest == entryIndex) {
            return;
        } else {
            T temp = list[entryIndex];
            list[entryIndex] = list[largest];
            list[largest] = temp;

            // 继续把小的元素往下沉，直到该元素的孩子均小于该元素为止
            siftDown(list, largest, last);
        }
    }

    // endregion

}
