package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int size;
    private int openSiteNum;
    private boolean[][] sites;
    private WeightedQuickUnionUF sitesTracker;
    private final int virtualTopIndex;
    private final int virtualBottomIndex;

    // 创建 N x N 的网格，最初所有位置都被阻塞
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N should not be smaller than 1!");
        }
        size = N;
        openSiteNum = 0;
        sites = new boolean[N][N];
        virtualTopIndex = N * N;
        virtualBottomIndex = N * N + 1;
        sitesTracker = new WeightedQuickUnionUF(N * N + 2);

        for (int i = 0; i < N; i++) {
            sitesTracker.union(virtualTopIndex, xyTo1D(0, i));
        }
    }

    private int xyTo1D(int row, int col) {
        return row * size + col;
    }

    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            openSiteNum++;
            sites[row][col] = true;

            if (row == 0) {
                sitesTracker.union(xyTo1D(row, col), virtualTopIndex);
            }
            if (row == size - 1) {
                sitesTracker.union(xyTo1D(row, col), virtualBottomIndex);
            }
            connectAdjacentSites(row, col);
        }
    }

    private void connectAdjacentSites(int row, int col) {
        int currentSite = xyTo1D(row, col);
        if (row > 0 && isOpen(row - 1, col)) {
            sitesTracker.union(currentSite, xyTo1D(row - 1, col));
        }
        if (row < size - 1 && isOpen(row + 1, col)) {
            sitesTracker.union(currentSite, xyTo1D(row + 1, col));
        }
        if (col > 0 && isOpen(row, col - 1)) {
            sitesTracker.union(currentSite, xyTo1D(row, col - 1));
        }
        if (col < size - 1 && isOpen(row, col + 1)) {
            sitesTracker.union(currentSite, xyTo1D(row, col + 1));
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return sites[row][col];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return sitesTracker.connected(xyTo1D(row, col), virtualTopIndex) && isOpen(row, col);
    }

    public int numberOfOpenSites() {
        return openSiteNum;
    }

    public boolean percolates() {
        return sitesTracker.connected(virtualTopIndex, virtualBottomIndex);
    }

    private void validate(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException("Index outbounded!");
        }
    }

    public static void main(String[] args) {
        int N = 5; // 设置网格的大小为 5x5
        Percolation percolation = new Percolation(N);

        // 打开一些位置
        percolation.open(0, 0); // 打开左上角
        percolation.open(1, 1); // 打开中间位置
        percolation.open(2, 2); // 继续打开中间位置
        percolation.open(3, 3); // 继续
        percolation.open(4, 4); // 打开右下角

        // 测试是否开放
        System.out.println("Is (0,0) open? " + percolation.isOpen(0, 0)); // 应该返回 true
        System.out.println("Is (1,0) open? " + percolation.isOpen(1, 0)); // 应该返回 false

        // 测试是否渗透
        System.out.println("Does the system percolate? " + percolation.percolates()); // 应该返回 false

        // 打开更多位置以形成一条渗透路径
        percolation.open(1, 0);
        percolation.open(2, 1);
        percolation.open(3, 2);
        percolation.open(4, 3);

        // 再次测试是否渗透
        System.out.println("Does the system percolate after more openings? " + percolation.percolates()); // 现在应该返回 true
    }
}



