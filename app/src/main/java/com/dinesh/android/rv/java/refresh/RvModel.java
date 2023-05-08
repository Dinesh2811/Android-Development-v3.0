package com.dinesh.android.rv.java.refresh;

/**
 * RvModel
 */


public class RvModel {
    int profilePic;
    String name;
    Boolean isExpanded;
    boolean isChecked;

    public RvModel(int profilePic, String name, Boolean isExpanded, boolean isChecked) {
        this.profilePic = profilePic;
        this.name = name;
        this.isExpanded = isExpanded;
        this.isChecked = isChecked;
    }

    public RvModel(int profilePic, String name, Boolean isExpanded) {
        this.profilePic = profilePic;
        this.name = name;
        this.isExpanded = isExpanded;
    }

    @Override
    public String toString() {
        return "RvModel{" +
                "profilePic=" + profilePic +
                ", name='" + name + '\'' +
                ", isExpanded=" + isExpanded +
                ", isChecked=" + isChecked +
                '}';
    }
}
