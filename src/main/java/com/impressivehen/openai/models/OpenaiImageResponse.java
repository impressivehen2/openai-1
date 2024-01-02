package com.impressivehen.openai.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OpenaiImageResponse {
    private List<ImageUrl> data;

    @Getter
    @Setter
    public class ImageUrl {
        private String url;
    }
}
