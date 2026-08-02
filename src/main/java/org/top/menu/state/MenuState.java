package org.top.menu.state;

import org.top.menu.common.Result;

public interface MenuState {
    Result<Boolean> handle();
    String getDescription();
}
