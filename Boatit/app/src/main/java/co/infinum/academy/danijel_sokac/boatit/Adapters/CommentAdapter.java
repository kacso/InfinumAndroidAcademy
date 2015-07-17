package co.infinum.academy.danijel_sokac.boatit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.Models.Comment;
import co.infinum.academy.danijel_sokac.boatit.R;

/**
 * Created by Danijel on 17.7.2015..
 */
public class CommentAdapter extends ArrayAdapter<Comment>{
    public CommentAdapter(Context context, List<Comment> objects) {
        super(context, R.layout.comment_list, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.comment_list, parent, false);
            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        holder.autorName.setText((getItem(position).()));
        holder.commentContent.setText((getItem(position).getContent()));
        holder.commentTime.setText((getItem(position).getCreateAt()));
        return convertView;
    }

    static class ViewHolder {
//        @Bind(R.id.author_name)
        public TextView autorName;
//        @Bind(R.id.comment_time)
        public TextView commentTime;
//        @Bind(R.id.comment_content)
        public TextView commentContent;

        public ViewHolder(View view) {
//            ButterKnife.bind(view);

            autorName = (TextView) view.findViewById(R.id.author_name);
            commentTime = (TextView) view.findViewById(R.id.comment_time);
            commentContent = (TextView) view.findViewById(R.id.comment_content);
            view.setTag(this);
        }
    }
}
