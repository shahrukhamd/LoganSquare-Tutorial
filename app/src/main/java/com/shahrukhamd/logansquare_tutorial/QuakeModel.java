package com.shahrukhamd.logansquare_tutorial;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

/**
 * Created by shahrukhamd on 17/08/16.
 */
@JsonObject
public class QuakeModel {

    @JsonField
    public List<Features> features;

    @JsonObject
    public static class Features {

        @JsonField(name="id")
        public String quake_id;
        @JsonField
        public Properties properties;
        @JsonField
        public Geometry geometry;

        @JsonObject
        public static class Properties {

            @JsonField
            public float mag;
            @JsonField
            public String place;
            @JsonField
            public String url;
        }

        @JsonObject
        public static class Geometry {
            @JsonField
            float[] coordinates;
        }
    }
}
