package com.xcy.fzb.all.persente;

import com.nanchen.wavesidebar.FirstLetterUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * 联系人model实体类
 *
 * @author nanchen
 * @fileName WaveSideBarView
 * @packageName com.nanchen.wavesidebarview
 * @date 2016/12/27  15:35
 * @github https://github.com/nanchen2251
 */

public class ContactModel {
    private String index;
    private String name;

    public ContactModel() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContactModel(String name){
        this.index = FirstLetterUtil.getFirstLetter(name);
        this.name = name;
    }

    public String getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }
}
