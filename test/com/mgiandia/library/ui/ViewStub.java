package com.mgiandia.library.ui;


public abstract class ViewStub implements View {
    private boolean opened;
    private int errorCount;
    private int infoCount;

    public void open() {
        opened = true;
    }
    
    public void close() {
        opened = false;
    }
    
    public void showError(String message) {
        errorCount++;
    }
    
    public void showInfo(String message) {
        infoCount++;
    }
    
    public boolean isOpened() {
        return opened;
    }
    
    public int getInfoCount() {
        return infoCount;
    }
    
    public int getErrorCount() {
        return errorCount;
    }
    
}
