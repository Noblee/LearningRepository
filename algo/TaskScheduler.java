import java.util.Arrays;
import java.util.Comparator;

public class TaskScheduler {
    //    实现一个任务调度问题（课本 P241） ： 在单处理器上具有期限和惩罚的单位时间任务调度。
//
//     1. 实现这个问题的贪心算法， 并写出流程图或者伪代码。
//
//     2.将每个Wi替换为max{W1,W2,...,Wn}-wi 运行算法、 比较并分析结果。
    static class Task {
        int n, d, w;

        public Task(int n, int d, int w) {
            this.n = n;
            this.d = d;
            this.w = w;
        }

        @Override
        public String toString() {
            return n + "";
        }
    }

    public static void main(String[] args) {
        int n = 7;
        int[] ds = new int[]{4, 2, 4, 3, 1, 4, 6};
        int[] ws = new int[]{70, 60, 50, 40, 30, 20, 10};
        Task[] tasks = new Task[n];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new Task(i + 1, ds[i], ws[i]);
        }
        func(tasks);
    }

    public static void func(Task[] tasks) {
        Task[] scheduled_task = new Task[tasks.length];
        Arrays.sort(tasks, Comparator.comparingInt(a -> -a.w));
        int punish = 0;
        for (Task task : tasks) {
            int j = task.d - 1;
            for (; j >= 0; j--) {
                if (scheduled_task[j] == null) {
                    scheduled_task[j] = task;
                    break;
                }
            }
            if (j == -1) {
                punish += task.w;
                for (int k = scheduled_task.length - 1; k >= 0; k--) {
                    if (scheduled_task[k] == null) {
                        scheduled_task[k] = task;
                        break;
                    }
                }
            }
        }
        System.out.println("sequence: " + Arrays.toString(scheduled_task));
        System.out.println("punish: " + punish);
    }
}

