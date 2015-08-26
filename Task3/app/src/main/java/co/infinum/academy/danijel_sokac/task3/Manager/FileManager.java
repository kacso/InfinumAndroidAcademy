package co.infinum.academy.danijel_sokac.task3.Manager;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

/**
 * Created by Danijel on 8.7.2015..
 */
public class FileManager {
    File externalStorageDirectory = null;

    public FileManager() throws Exception {
        File folder = new File(Environment.getExternalStorageDirectory() + "/NotepadFiles");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdir();
        }
        if (success) {
            externalStorageDirectory = Environment.getExternalStoragePublicDirectory("NotepadFiles");
        } else {
            throw new Exception();
        }
    }

    public void saveFile(String fileName, String content) throws IOException {
        File file = new File(externalStorageDirectory.getPath(), fileName);
        if (file.exists ())
            file.delete();
//        file.mkdirs();

        FileWriter w = new FileWriter(file);
        Writer writer = new BufferedWriter(w);
        writer.write(content);

        writer.close();
    }


    public String readFile(String filePath) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));

        String content = convertStreamToString(fileInputStream);

        fileInputStream.close();
        return content;
    }

    public String getFileTitle(String filePath) {
        return new File(filePath).getName();
    }

    public File[] getListOfFiles() {
        if (externalStorageDirectory != null) {
            return externalStorageDirectory.listFiles();
        }
        return null;
    }


    private String convertStreamToString(InputStream inputStream) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        reader.close();
        return stringBuilder.toString();
    }
}
