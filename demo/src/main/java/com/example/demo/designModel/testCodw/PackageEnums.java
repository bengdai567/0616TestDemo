package com.example.demo.designModel.testCodw;

public enum PackageEnums {
    ONELEVEL("1","EA"), //小包
    TWOLEVEL("2","IP"),//中包
    THREELEVEL("3","CS");//大包
    private String value;
    private String name;

    public static PackageEnums parse(String value){
        for(PackageEnums status:PackageEnums.values()){
            if(value.equals(status.getValue())){
                return status;
            }
        }
        return null;
    }

    PackageEnums(String value, String name) {
        this.value = value;
        this.name = name;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
