package com.balius.filimo.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.balius.filimo.model.LikedVideos;
import com.balius.filimo.model.Watched;
import com.balius.filimo.model.lastesvideo.Video;
import com.balius.filimo.model.login.Login;
import com.balius.filimo.model.Save;

@Database(entities = {Video.class, Login.class, Save.class, LikedVideos.class, Watched.class},version = 1,exportSchema = false)
public abstract class Db extends RoomDatabase {

    public abstract IDao iDao();

    public static Db instance = null;

    public static synchronized Db getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context, Db.class, "video.db").allowMainThreadQueries().build();
        }
        return instance;

    }

}
