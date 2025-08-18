package com.example.shoppie.presentation.once_event;

public class OnceEvent<T> {
    // <editor-fold desc="Region:Fields">
    T content;
    boolean isHandled;
    // </editor-fold>

    // <editor-fold desc="Region:Constructor">
    public OnceEvent(T content) {
        this.content = content;
        isHandled = false;
    }

    public OnceEvent(T content, boolean isHandled) {
        this.content = content;
        this.isHandled = isHandled;

    }
    // </editor-fold>

    // <editor-fold desc="Region:Getter & Setter">
    public T getContent()
    {
        return this.content;
    }

    public T getContentIfNotHandle() {
        if(isHandled)
        {
            return null;
        }

        isHandled = true;
        return content;
    }
    // </editor-fold>
}
