package com.adiaz.testingsqllite.db.entities;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

/**
 * Created by adiaz on 2/1/18.
 */

@AutoValue
public abstract class Match implements Parcelable {

    public abstract Long id();
    public abstract String teamLocal();
    public abstract String teamVisitor();

    public static Builder builder() {
        return new AutoValue_Match.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(Long id);
        public abstract Builder teamLocal(String teamLocal);
        public abstract Builder teamVisitor(String teamVisitor);
        public abstract Match build();
    }
}
