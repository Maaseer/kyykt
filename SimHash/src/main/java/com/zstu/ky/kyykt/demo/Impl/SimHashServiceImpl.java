package com.zstu.ky.kyykt.demo.Impl;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.zstu.ky.kyykt.demo.Service.SimHashService;
import lombok.experimental.var;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

@Service
public class SimHashServiceImpl implements SimHashService {
    HashSet<String> dict = new HashSet<>();
    private final ResourceLoader resourceLoader;
    public SimHashServiceImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        Resource resource = resourceLoader.getResource("classpath:OutDict.txt");
        File file = null;
        try {
            file = resource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = fileReader.readLine()) != null) {
                dict.add(tempStr);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int CacuSimHash(String str1, String str2) {
        var SimHash1 = GetSimHarsh(str1);
        var SimHash2 = GetSimHarsh(str2);
        int Harming = CaculatHarmin(SimHash1, SimHash2);
        return Math.min(Harming, 10);
    }

    public int[] GetSimHarsh(String str) {
        //分词+计算词频
        var seg = new JiebaSegmenter();
        HashMap<String, Integer> wordsMap = new HashMap<>();
        var words = seg.sentenceProcess(str);
        words.forEach(s -> {
            if (!dict.contains(s)) {
                if (wordsMap.putIfAbsent(s, 1) != null) {
                    Integer a = wordsMap.get(s);
                    wordsMap.replace(s, ++a);
                }
            }
        });
        int[] SimHash = new int[32];//存放每个单词的加权hash

        wordsMap.forEach((k, v) -> {
            int hash = Times33(k);

            var w = Integer.toUnsignedString(hash, 2).getBytes();
            byte[] b = new byte[32];
            //byte[] c = new byte[8];
            System.arraycopy(w, 0, b, 32 - w.length, w.length);

            for (int i = 32 - w.length; i < 32; i++) {
                b[i] -= '0';
            }
            int temp = 0;
            //System.arraycopy(b, 0, c, 0,8);
            //加权
            for (int i = 0; i < 32; i++) {

                if (b[i] == 0) {
                    SimHash[i] += -1 * v;
                } else {
                    SimHash[i] += v;
                }
            }
        });

        //降维

        for (int i = 0; i < SimHash.length; i++) {
            if (SimHash[i] >= 0) {
                SimHash[i] = 1;
            } else {
                SimHash[i] = 0;
            }
        }
        for (int i = 0; i < SimHash.length; i++) {
            System.out.print(SimHash[i]);
        }
        System.out.println();


        return SimHash;
    }


    public int Times33(String value) {
        int hash = 0;
        for (int i = 0; i < value.length(); i++) {
            hash = 33 * hash + value.charAt(i);
        }
        return hash;
    }

    public int CaculatHarmin(int[] SimHash1, int[] SimHash2) {
        int Harmin = 0;
        for (int i = 0; i < 32; i++) {
            if (SimHash1[i] != SimHash2[i]) {
                Harmin++;
            }
        }
        return Harmin;
    }
}
