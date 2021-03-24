package interview;
//评测题目: 有M个集群，每个集群有X台机器，要分N批发布，做到最平均发布；
//可以使用List<Integer>和N为入参，List<Integer>有多少元素就代表有多少集群
//，每个元素的取值代表每个集群的机器数量，N为发布批次
//，直接打印出每批每个集群发布几台机器，如果当前集群在某一批次没有可发布机器，输出0；
//使用Java编写，要求可编译、可运行

//最平均发布的意思是每次发布的机器是相同的嘛？

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class 阿里云面试集群发布 {

    static class Cluster{
        public int id;
        public int num;
        Cluster(int id, int num){
            this.id = id;
            this.num = num;
        }
    }

    private static void release(List<Integer> machineNums, int N){
        if(N<=0) System.out.println("N 不能小于1");
        int total = 0;
        PriorityQueue<Cluster> machines = new PriorityQueue<>((c1, c2)->{
            return c2.num - c1.num;
        });
        int id = 1;
        for(int machineNum: machineNums){
            machines.offer(new Cluster(id++, machineNum));
            total+=machineNum;
        }
        int machineNumPerRelease = total/N;
        if(total%N>0) machineNumPerRelease++;
        for(int j = 0;j < N; j++){
            int[] res = new int[machines.size()+1];
            for(int i = 0;i < machineNumPerRelease; i++){
                Cluster temp = machines.poll();
                if(temp.num>0){
                    res[temp.id]++;
                    temp.num--;
                    machines.offer(temp);
                } else{
                    machines.offer(temp);
                    break;
                }
            }
            for(int i = 1;i<res.length;i++){
                System.out.println("第"+(j+1)+"次发布: 第"+i+"个集群发布"+res[i]+"台");
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> machineNums = Arrays.asList(0,2,4,5,8);
        release(machineNums,3);
    }
}