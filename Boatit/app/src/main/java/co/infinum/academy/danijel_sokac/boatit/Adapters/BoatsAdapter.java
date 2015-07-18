package co.infinum.academy.danijel_sokac.boatit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import co.infinum.academy.danijel_sokac.boatit.Activities.DetailsActivity;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.Models.Comment;
import co.infinum.academy.danijel_sokac.boatit.R;

/**
 * Created by Danijel on 13.7.2015..
 */
public class BoatsAdapter extends ArrayAdapter<Boat>{

    public BoatsAdapter(Context context, List<Boat> objects) {
        super(context, R.layout.boats_list, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.boats_list, parent, false);
            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.boatName.setText((getItem(position).getTitle()));
        Glide.with(convertView.getContext()).load(getItem(position).getImageURL()).into(holder.boatImage);
        List<Comment> comments = getItem(position).getComments();
        if (!comments.isEmpty())
            holder.boatDetails.setText(comments.get(0).getContent());
        else {
            holder.boatDetails.setText("");
        }
        return convertView;
    }

    static class ViewHolder {
        private ImageView boatImage;
        private TextView boatName;
        private TextView boatDetails;

        public ViewHolder(View view) {
            boatImage = (ImageView) view.findViewById(R.id.boat_image);
            boatName = (TextView) view.findViewById(R.id.boat_name);
            boatDetails = (TextView) view.findViewById(R.id.boat_details);
            view.setTag(this);
        }
    }
}
