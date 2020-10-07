package com.mk.po;
//具体的配置
public class ModelData {
    private int data_id;
    private int model_id;
    private String data_value;
    private boolean selected;

    public int getData_id() {
        return data_id;
    }

    public void setData_id(int data_id) {
        this.data_id = data_id;
    }

    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public String getData_value() {
        return data_value;
    }

    public void setData_value(String data_value) {
        this.data_value = data_value;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "ModelData{" +
                "data_id=" + data_id +
                ", model_id=" + model_id +
                ", data_value='" + data_value + '\'' +
                ", selected=" + selected +
                '}';
    }
}
