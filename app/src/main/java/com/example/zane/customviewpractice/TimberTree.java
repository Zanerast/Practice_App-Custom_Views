package com.example.zane.customviewpractice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import timber.log.Timber;

public class TimberTree extends Timber.DebugTree {
    @Override
    protected void log(int priority, @Nullable String tag, @NotNull String message, @Nullable Throwable t) {
        String newMessage = "Timber: " + message;
        super.log(priority, tag, newMessage, t);
    }
}
