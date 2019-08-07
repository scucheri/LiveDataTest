package com.example.android.databinding.basicsample.data;

import java.util.ArrayList;

/**
 * Created by xiaoxiaoyu on 2019-08-06.
 */
public class XyTestData {
    private String name;
    private int age;
    private String address;
    public XyInnerData innerData = new XyInnerData();

    public static class XyInnerData{
        public String homeTown = "湖南";
        public ArrayList<String> infos;
    }

}
