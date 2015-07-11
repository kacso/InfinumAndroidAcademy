package co.infinum.academy.danijel_sokac.task3;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FilesActivity extends AppCompatActivity {
    private ListView fileList;
    private FileManager fileManager;
    private File[] allFiles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);

        fileList = (ListView) findViewById(R.id.file_list);


        try {
            fileManager = new FileManager();
        } catch (Exception e) {
            Toast.makeText(FilesActivity.this, "Error while reading files", Toast.LENGTH_LONG).show();
            return;
        }

        allFiles = fileManager.getListOfFiles();

        if (allFiles != null) {
            List<File> fileData = new ArrayList<File>();
            for (File file : allFiles) {
                if (file.isFile()) {
                    fileData.add(file);
                }
            }
            fileList.setAdapter(new NameAdapter(this, fileData));
            fileList.setVisibility(View.VISIBLE);
            findViewById(R.id.no_files).setVisibility(View.GONE);
        } else {
            fileList.setVisibility(View.GONE);
            findViewById(R.id.no_files).setVisibility(View.VISIBLE);
        }

        fileList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(FilesActivity.this, "Clicked item " + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FilesActivity.this, EditorActivity.class);
                intent.putExtra("FILE_PATH", allFiles[position].getAbsolutePath());
                startActivity(intent);
                finish();
            }
        });
    }

    public void newFile(View v) {
        Toast.makeText(FilesActivity.this, "NEW FILE", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(FilesActivity.this, EditorActivity.class);
        intent.putExtra("FILE_PATH", "");
        startActivity(intent);
        finish();
    }



    public class NameAdapter extends BaseAdapter {
        private final Context context;

        private List<File> files;

        public NameAdapter(Context context, List<File> files) {

            this.files = new ArrayList(files);
            this.context = context;
        }

        @Override
        public int getCount() {
            return files.size();
        }

        @Override
        public File getItem(int position) {
            return files.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
                viewHolder = new ViewHolder(convertView);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            File fd = getItem(position);
            viewHolder.fileName.setText(fd.getName());
            //TODO fileinfo
            viewHolder.fileInfo.setText("Last modified: " + new Date(fd.lastModified()).toString());
            return convertView;
        }

        public class ViewHolder {

            TextView fileName;
            TextView fileInfo;

            public ViewHolder(View view) {
                fileName = (TextView) view.findViewById(R.id.file_name);
                fileInfo = (TextView) view.findViewById(R.id.file_info);
                view.setTag(this);
            }

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_files, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
