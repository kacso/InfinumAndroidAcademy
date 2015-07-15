package co.infinum.academy.danijel_sokac.task3.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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

import co.infinum.academy.danijel_sokac.task3.Manager.FileManager;
import co.infinum.academy.danijel_sokac.task3.R;
import co.infinum.academy.danijel_sokac.task3.interfaces.FileListener;

/**
 * Created by Danijel on 11.7.2015..
 */
public class FilesFragment extends Fragment {
    private View onNewFileClicked;

    private FileListener fileListener;
    private ListView fileList;
    private FileManager fileManager;
    private File[] allFiles;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof FileListener) {
            fileListener = (FileListener) activity;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_files, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        onNewFileClicked = view.findViewById(R.id.new_file);
        onNewFileClicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fileListener != null) {
                    newFile(v);
                }
            }
        });

        fileList = (ListView) getActivity().findViewById(R.id.file_list);


        try {
            fileManager = new FileManager();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error while reading files", Toast.LENGTH_LONG).show();
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
            fileList.setAdapter(new FileListAdapter(getActivity(), fileData));
            fileList.setVisibility(View.VISIBLE);
            getActivity().findViewById(R.id.no_files).setVisibility(View.GONE);
        } else {
            fileList.setVisibility(View.GONE);
            getActivity().findViewById(R.id.no_files).setVisibility(View.VISIBLE);
        }

        fileList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fileListener.onOpenFileClicked(allFiles[position].getAbsoluteFile().toString());
                Toast.makeText(getActivity(), "Clicked item " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void newFile(View v) {
        Toast.makeText(getActivity(), "NEW FILE", Toast.LENGTH_SHORT).show();
        fileListener.onNewFileClicked();
    }

    public class FileListAdapter extends BaseAdapter {
        private final Context context;

        private List<File> files;

        public FileListAdapter(Context context, List<File> files) {

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

}
