package com.shaynecomptondev.hebimageapi.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GoogleImageDetectionResponseDto {
    public final Response responses[];

    @JsonCreator
    public GoogleImageDetectionResponseDto(@JsonProperty("responses") Response[] responses){
        this.responses = responses;
    }

    public static final class Response {
        public final LabelAnnotation labelAnnotations[];

        @JsonCreator
        public Response(@JsonProperty("labelAnnotations") LabelAnnotation[] labelAnnotations){
            this.labelAnnotations = labelAnnotations;
        }

        public static final class LabelAnnotation {
            public final String mid;
            public final String description;
            public final String score;
            public final String topicality;

            @JsonCreator
            public LabelAnnotation(@JsonProperty("mid") String mid, @JsonProperty("description") String description, @JsonProperty("score") String score, @JsonProperty("topicality") String topicality){
                this.mid = mid;
                this.description = description;
                this.score = score;
                this.topicality = topicality;
            }
        }
    }
}
