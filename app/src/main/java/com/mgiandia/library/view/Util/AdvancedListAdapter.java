package com.mgiandia.library.view.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mgiandia.library.R;
import com.mgiandia.library.util.Quadruple;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class AdvancedListAdapter extends BaseAdapter implements Filterable
{
    private Context context;
    private LayoutInflater inflater;
    private List<Quadruple> dataSource, rawDataSource;
    private ItemFilter mFilter = new ItemFilter();

    public AdvancedListAdapter(Context context)
    {
        this.context = context;
        dataSource = rawDataSource = new ArrayList<>();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position)
    {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Quadruple triplet = (Quadruple) getItem(position);

        View rowView = inflater.inflate(triplet.getSecond() == null ? R.layout.simple_adapter_item : R.layout.adapter_item, parent, false);

        ((TextView) rowView.findViewById(R.id.item_name)).setText(triplet.getFirst());

        if(triplet.getSecond() != null)
            ((TextView) rowView.findViewById(R.id.item_lastname)).setText(triplet.getSecond());

        ((TextView) rowView.findViewById(R.id.item_details)).setText(triplet.getThird());
        ((TextView) rowView.findViewById(R.id.item_view_details)).setText(">");

        PixelUtils.drawInitialsImage(context, (ImageView) rowView.findViewById(R.id.item_image), triplet.getFirst(), new int[]{R.color.colorSmallBlue, R.color.colorSmallGreen, R.color.colorSmallOrange});

        String initials = "";

        if(triplet.getFirst().length() > 0 && triplet.getSecond() != null && triplet.getSecond().length() > 0)
            initials = (triplet.getFirst().charAt(0)+""+triplet.getSecond().charAt(0)).toUpperCase();
        else if(triplet.getFirst().length() > 1)
            initials = (triplet.getFirst().charAt(0)+""+triplet.getFirst().charAt(1)).toUpperCase();

        ((TextView) rowView.findViewById(R.id.image_text)).setText(initials);

        return rowView;
    }

    public void loadSource(List<Quadruple> dataSource)
    {
        this.dataSource = dataSource;
        Collections.reverse(this.dataSource);

        this.rawDataSource = dataSource.subList(0, dataSource.size());//shallow copy
        notifyDataSetChanged();
    }

    public Filter getFilter()
    {
        return mFilter;
    }

    private class ItemFilter extends Filter
    {
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results)
        {
            dataSource = (List<Quadruple>)results.values;
            notifyDataSetChanged();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();
            FilterResults results = new FilterResults();
            List<Quadruple> matches = new ArrayList<>();

            for(Quadruple triplet : rawDataSource)
                if (triplet.getFirst().toLowerCase().contains(filterString) || (triplet.getSecond() != null && triplet.getSecond().toLowerCase().contains(filterString)) || triplet.getThird().toLowerCase().contains(filterString))
                    matches.add(triplet);

            results.values = matches;
            results.count = matches.size();

            return results;
        }
    }
}
