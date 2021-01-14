package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> queens = new ArrayList<>();

        for(int i = 0; i < 8; i++){
            String s = reader.readLine();
            String x = s.substring(0, 1);
            String y = s.substring(1);
            int xx = Integer.parseInt(x);
            int yy = Integer.parseInt(y);
            int queen = xx + (yy-1)*8;
            queens.add(queen);
        }
        dangerFields(queens);
    }

    public static void dangerFields(ArrayList<Integer> queens){
        Set<Integer> fields = new HashSet<>();
        for(int i = 0; i < queens.size(); i++){
            int min = 1;
            int max = 8;
            while (max <= 64) {
                if (queens.get(i) >= min && queens.get(i) <= max) {
                    for (int xMin = queens.get(i); xMin >= min; xMin--) {
                        if (xMin != min) {
                            fields.add(xMin - 1);
                        }
                    }
                    for (int xMax = queens.get(i); xMax <= max; xMax++) {
                        if (xMax != max) {
                            fields.add(xMax + 1);
                        }
                    }
                }
                min += 8;
                max += 8;
            }
            for(int yMin = queens.get(i); yMin > 0; yMin -= 8){
                if (yMin > 8){
                    fields.add(yMin - 8);
                }
            }
            for(int yMax = queens.get(i); yMax < 65; yMax += 8){
                if (yMax < 57){
                    fields.add(yMax + 8);
                }
            }

            int count = (int) Math.floor(queens.get(i)/8);
            int count2 = 8 - (int) Math.ceil(queens.get(i)/8);

            for(int xyLeftMin = queens.get(i); count > 0; count--){
                if (xyLeftMin == 17 || xyLeftMin == 25 || xyLeftMin == 33 || xyLeftMin == 41 || xyLeftMin == 49 || xyLeftMin == 57){
                    break;
                } else if (xyLeftMin > 9) {
                    fields.add(xyLeftMin - 9);
                    xyLeftMin -= 9;
                }
            }
            for(int xyLeftMax = queens.get(i); count > 0; count--){
                if (xyLeftMax == 9 || xyLeftMax == 17 || xyLeftMax == 25 || xyLeftMax == 33 || xyLeftMax == 41 || xyLeftMax == 49){
                    break;
                } else if (xyLeftMax < 57) {
                    fields.add(xyLeftMax + 7);
                    xyLeftMax += 7;
                }
            }
            for(int xyRightMin = queens.get(i); count2 > 0; count2--){
                if (xyRightMin == 16 || xyRightMin == 24 || xyRightMin == 32 || xyRightMin == 40 || xyRightMin == 48 || xyRightMin == 56 || xyRightMin == 64){
                    break;
                } else if (xyRightMin > 8) {
                    fields.add(xyRightMin - 7);
                    xyRightMin -= 7;
                }
            }
            for(int xyRightMax = queens.get(i); count2 > 0; count2--){
                if (xyRightMax == 8 || xyRightMax == 16 || xyRightMax == 24 || xyRightMax == 32 || xyRightMax == 40 || xyRightMax == 48) {
                    break;
                } else if (xyRightMax < 56){
                    fields.add(xyRightMax + 9);
                    xyRightMax += 9;
                }
            }
        }
        Set<Integer> positions = new HashSet<>(queens);
        Set<Integer> intersect = fields.stream().filter(positions::contains).collect(Collectors.toSet());
        if(intersect.isEmpty()){
            System.out.println("NO");
        } else System.out.println("YES");
    }
}