package com.company.libraryedge.model;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

public class CheckoutViewModel {
    @NotEmpty
    private String day;
    @NotEmpty
    private List<String> isbnList;

    public CheckoutViewModel(@NotEmpty String day, @NotEmpty List<String> isbnList) {
        this.day = day;
        this.isbnList = isbnList;
    }

    public CheckoutViewModel() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<String> getIsbnList() {
        return isbnList;
    }

    public void setIsbnList(List<String> isbnList) {
        this.isbnList = isbnList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckoutViewModel that = (CheckoutViewModel) o;
        return day.equals(that.day) &&
                isbnList.equals(that.isbnList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, isbnList);
    }

    @Override
    public String toString() {
        return "CheckoutViewModel{" +
                "day='" + day + '\'' +
                ", isbnList=" + isbnList +
                '}';
    }
}
