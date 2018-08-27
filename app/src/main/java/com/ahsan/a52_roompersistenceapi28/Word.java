package com.ahsan.a52_roompersistenceapi28;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * A basic class representing an entity that is a row in a one-column database table.
 *
 * @ Entity - You must annotate the class as an entity and supply a table name if not class name.
 * @ PrimaryKey - You must identify the primary key.
 * @ ColumnInfo - You must supply the column name if it is different from the variable name.
 *
 * See the documentation for the full rich set of annotations.
 * https://developer.android.com/topic/libraries/architecture/room.html
 */

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "employee_id")
    private String id;

    @Nullable
    @ColumnInfo(name = "firstname")
    private String first;

    @Nullable
    @ColumnInfo(name = "lastname")
    private String last;

    @Nullable
    @ColumnInfo(name = "title")
    private String title;


    @Nullable
    @ColumnInfo(name = "department")
    private String department;


    public Word(@NonNull String id,String first, String last, String title, String department) {
        this.id = id;
        this.first = first;
        this.last = last;
        this.title = title;
        this.department = department;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @Nullable
    public String getFirst() {
        return first;
    }

    public void setFirst(@Nullable String first) {
        this.first = first;
    }

    @Nullable
    public String getLast() {
        return last;
    }

    public void setLast(@Nullable String last) {
        this.last = last;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    @Nullable
    public String getDepartment() {
        return department;
    }

    public void setDepartment(@Nullable String department) {
        this.department = department;
    }
}
