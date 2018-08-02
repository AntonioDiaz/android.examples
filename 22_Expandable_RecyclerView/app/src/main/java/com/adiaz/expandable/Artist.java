package com.adiaz.expandable;


import android.os.Parcelable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Artist implements Parcelable {

    public abstract String name();
    public abstract Boolean isFavorite();


    public static Builder builder() {
        return new AutoValue_Artist.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder name(String name);
        public abstract Builder isFavorite(Boolean isFavorite);
        public abstract Artist build();
    }

}

