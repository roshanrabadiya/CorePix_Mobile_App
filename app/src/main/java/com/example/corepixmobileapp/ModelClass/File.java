package com.example.corepixmobileapp.ModelClass;

public class File {

    private String file_name;
    private String file_date;


    public File(String file_name,String file_date) {
        this.file_name = file_name;
        this.file_date = file_date;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_date() {
        return file_date;
    }

    public void setFile_date(String file_date) {
        this.file_date = file_date;
    }
}
