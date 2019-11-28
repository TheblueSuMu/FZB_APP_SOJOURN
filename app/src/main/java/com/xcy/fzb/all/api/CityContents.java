package com.xcy.fzb.all.api;

public class CityContents {
    static String familyId = "";

    static String cityType = "";

    static String ReadRecordStatus = "";

    static String OneKey = "";

    public static String getOneKey() {
        return OneKey;
    }

    public static void setOneKey(String oneKey) {
        OneKey = oneKey;
    }

    public static String getReadRecordStatus() {
        return ReadRecordStatus;
    }

    public static void setReadRecordStatus(String readRecordStatus) {
        ReadRecordStatus = readRecordStatus;
    }

    public static String getCityType() {
        return cityType;
    }

    public static void setCityType(String cityType) {
        CityContents.cityType = cityType;
    }

    public static String getFamilyId() {
        return familyId;
    }

    public static void setFamilyId(String familyId) {
        CityContents.familyId = familyId;
    }
}
