package org.initial.heart.utils.result;

import java.io.Serializable;

public interface IResultCode extends Serializable {
    String getMessage();
    int getCode();
}
