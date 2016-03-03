package com.example.adao1.project1;

import java.util.ArrayList;

/**
 * Created by adao1 on 3/2/2016.
 */
public class ListObject {
    private ArrayList<String> listArray;
    private int listLength;
    private String listTitle;
    private boolean isStrikeThrough;

    public ListObject(String listTitle) {
        this.listTitle = listTitle;
        this.listArray = new ArrayList<>();

    }

    public ListObject(String listTitle,boolean isStrikeThrough){
        this.listTitle = listTitle;
        this.isStrikeThrough = isStrikeThrough;
    }

    public Boolean isStrikeThrough(){
        return isStrikeThrough;
    }

    public void changeStrikeThrough(boolean isStrikeThrough){
        this.isStrikeThrough = isStrikeThrough;
    }

    public ArrayList<String> getListArray() {
        return listArray;
    }

    public void setListArray(ArrayList<String> listArray) {
        this.listArray = listArray;
    }

    public int getListLength() {
        return listArray.size();
    }

    public String getListTitle() {
        return listTitle;
    }

    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
    }
}
