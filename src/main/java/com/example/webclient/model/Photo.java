package com.example.webclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Photo {
    private long albumId;
    private long id;
    private String title;
    private String url;
    private String thumbnailUrl;
}
