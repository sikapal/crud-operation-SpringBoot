package com.skpcorp.crudOperation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class PageModel {

    private String name;
    private String path;
    private boolean active = false;
    private boolean disabled = false;
}
