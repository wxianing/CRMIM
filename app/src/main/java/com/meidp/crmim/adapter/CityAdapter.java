package com.meidp.crmim.adapter;


import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.widget.ContactItemInterface;
import com.meidp.crmim.widget.ContactListAdapter;

import java.util.List;


public class CityAdapter extends ContactListAdapter {

    public CityAdapter(Context _context, int _resource, List<ContactItemInterface> _items) {
        super(_context, _resource, _items);
    }

    public void populateDataForRow(View parentView, ContactItemInterface item, int position) {
        View infoView = parentView.findViewById(R.id.infoRowContainer);
        TextView nicknameView = (TextView) infoView.findViewById(R.id.cityName);

        nicknameView.setText(item.getDisplayInfo());
    }
}