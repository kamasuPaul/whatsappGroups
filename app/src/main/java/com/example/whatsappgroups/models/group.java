package com.example.whatsappgroups.models;

public class group {
    String grpName;
    String grpDscp;
    String Image;

    public group(String grpName, String grpDscp, String image) {
        this.grpName = grpName;
        this.grpDscp = grpDscp;
        Image = image;
    }

    public String getGrpName() {
        return grpName;
    }

    public void setGrpName(String grpName) {
        this.grpName = grpName;
    }

    public String getGrpDscp() {
        return grpDscp;
    }

    public void setGrpDscp(String grpDscp) {
        this.grpDscp = grpDscp;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
