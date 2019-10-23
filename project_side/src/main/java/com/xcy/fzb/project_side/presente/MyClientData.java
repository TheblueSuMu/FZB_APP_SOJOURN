package com.xcy.fzb.project_side.presente;

public class MyClientData {

    String name;
    String judge;

    public MyClientData() {
    }

    public MyClientData(String name, String judge) {
        this.name = name;
        this.judge = judge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJudge() {
        return judge;
    }

    public void setJudge(String judge) {
        this.judge = judge;
    }
}
