package interview;

import java.util.ArrayList;

public class 还原IP地址 {


    public ArrayList<String> restoreIpAddresses(String s) throws Exception {
        ArrayList<String> ans = new ArrayList<>();
        StringBuilder cur = new StringBuilder("");
        recur(ans, cur, s, 0 , 0);
        return ans;
    }
    public void recur(ArrayList<String> ans, StringBuilder cur ,String s, int index, int count){
        if(count==4){
            if(cur.length()-3 == s.length()) {
                ans.add(cur.toString());
            }
            return;
        }
        if(((s.length()-index)/(double)(4-count))>3d){
            return;
        }
        for(int i = index;i<s.length();i++){
            for(int j =1;j<=3&&(i+j)<=s.length();j++){
                String temp = s.substring(i,i + j);
                if(j>1&&temp.charAt(0)=='0') break;
                if(Integer.parseInt(temp)<=255){
                    int cur_size = cur.length();
                    cur.append(temp);
                    if(count != 3) cur.append(".");
                    recur(ans, cur, s, i+j, count+1);
                    cur.setLength(cur_size);
                }
            }
        }

    }
}