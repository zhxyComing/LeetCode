package com.dixon.leetcode.core.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Create by: dixon.xu
 * Create on: 2020.07.03
 * Functional desc: MD文件数据
 */
public class MdData implements Parcelable {

    private String dir; // 文件夹路径
    private String name; // 文件名称

    public MdData() {
    }

    public MdData(String dir, String name) {
        this.dir = dir;
        this.name = name;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MdData{" +
                "dir='" + dir + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.dir);
        dest.writeString(this.name);
    }

    protected MdData(Parcel in) {
        this.dir = in.readString();
        this.name = in.readString();
    }

    public static final Creator<MdData> CREATOR = new Creator<MdData>() {
        @Override
        public MdData createFromParcel(Parcel source) {
            return new MdData(source);
        }

        @Override
        public MdData[] newArray(int size) {
            return new MdData[size];
        }
    };
}
