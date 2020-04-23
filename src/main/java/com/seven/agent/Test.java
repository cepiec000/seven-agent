package com.seven.agent;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description: TODO
 * @Author chendongdong
 * @Date 2019/10/30 16:48
 * @Version V1.0
 **/
public class Test {
    List<List<Integer>> lists = new ArrayList<>();

    public static void main(String[] args) {
        int[] num = {1,2,0,0};
        int k = 12;
        System.out.println(JSON.toJSONString(backsub(num, k)));
    }

    private static List<Integer> backsub(int[] A, int K) {
        List<Integer> result = new ArrayList<>();
        int n = A.length;
        int cur = K;
        for (int i=n-1;i>=0 || cur>0;i--){
                if(i>=0){
                    cur+=A[i];
                }
                result.add(cur % 10);
                cur /= 10;
        }

        Collections.reverse(result);
        return result;
    }

}
