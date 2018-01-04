package com.adiaz.testingsqllite.db.entities;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

/**
 * Created by adiaz on 2/1/18.
 */

@AutoValue
public abstract class Competition implements Parcelable {
    public abstract Long id();
    public abstract String name();
    public abstract String category();
    public abstract Long idSport();

    public static Builder builder() {
        return new AutoValue_Competition.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(Long id);
        public abstract Builder name(String name);
        public abstract Builder category(String category);
        public abstract Builder idSport (Long idSport);
        public abstract Competition build();
    }
}
