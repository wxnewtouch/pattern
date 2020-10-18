package org.wally.inteview;

import java.util.*;

public class HashCode {
    /**
     * 根据不同的multiplier返回不同的hashcode
     *
     * @param str
     * @param multiplier
     * @return
     */
    public static Integer hashCode(String str, Integer multiplier) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = multiplier * hash + str.charAt(i);
        }
        return hash;
    }

    /**
     * 计算hash碰撞率
     *
     * @param multiplier
     * @param hashCodeList
     * @return
     */
    public static RateInfo hashCollisionRate(Integer multiplier, List<Integer> hashCodeList) {
        Integer maxHash = hashCodeList.stream().max(Integer::compareTo).get();
        Integer minHash = hashCodeList.stream().min(Integer::compareTo).get();
        int collisionCount = (int) (hashCodeList.size() - hashCodeList.stream().distinct().count());
        double collisionRate = (collisionCount * 1.0) / hashCodeList.size();
        return RateInfo.builder()
                .maxHash(maxHash)
                .minHash(minHash)
                .collisionCount(collisionCount)
                .collisionRate(collisionRate)
                .multiplier(multiplier)
                .build();
    }

    public static List<RateInfo> collisionRateList(Set<String> strList, Integer... multiplier) {
        List<RateInfo> infoList = new ArrayList<>();
        for (Integer integer : multiplier) {
            List<Integer> hashCodeList = new ArrayList<>();
            for (String s : strList) {
                Integer hashCode = hashCode(s, integer);
                hashCodeList.add(hashCode);
            }
            infoList.add(hashCollisionRate(integer, hashCodeList));
        }
        return infoList;
    }

    public static Map<Integer, Integer> hashArea(List<Integer> hashCodeList) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        int start = 0;
        for (long i = 0x80000000; i <= 0x7fffffff; i += 67108864) {
            long min = i;
            long max = min + 67108864;
            int num = (int) hashCodeList.parallelStream().filter(x -> x > min && x < max).count();
            map.put(start++, num);
        }
        return map;
    }

    public static Map<Integer, Integer> hashArea(Set<String> strList, Integer multiplier) {
        List<Integer> hashCodeList = new ArrayList<>();
        for (String s : strList) {
            Integer integer = hashCode(s, multiplier);
            hashCodeList.add(integer);
        }
        return hashArea(hashCodeList);
    }

}
