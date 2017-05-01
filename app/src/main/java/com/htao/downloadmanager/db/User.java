package com.htao.downloadmanager.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by 10607 on 2017/5/1.
 */

@Entity
public class User {
    @Id
    public int id;
    public String name;
    public int age;
}
