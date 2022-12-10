package com.bfil.uilibrary.widgets.customSpinner;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.bfil.uilibrary.R;

import java.io.Serializable;
import java.util.List;

import androidx.appcompat.app.AlertDialog;

public class SearchableListDialog extends DialogFragment implements
        SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    private static final String ITEMS = "items";

    private CustomSpinnerAdapter listAdapter;

    private ListView _listViewItems;

    private SearchableItem _searchableItem;

    private OnSearchTextChanged _onSearchTextChanged;

    private SearchView _searchView;

    private String _strTitle;

    private String _strPositiveButtonText;

    private DialogInterface.OnClickListener _onClickListener;

    public SearchableListDialog() {

    }

    public static SearchableListDialog newInstance(List items) {
        SearchableListDialog multiSelectExpandableFragment = new SearchableListDialog();

        Bundle args = new Bundle();
        args.putSerializable(ITEMS, (Serializable) items);

        multiSelectExpandableFragment.setArguments(args);

        return multiSelectExpandableFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams
                .SOFT_INPUT_STATE_HIDDEN);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Getting the layout inflater to inflate the view in an alert dialog.
        LayoutInflater inflater = LayoutInflater.from(getActivity());

        // Crash on orientation change #7
        // Change Start
        // Description: As the instance was re initializing to null on rotating the device,
        // getting the instance from the saved instance
        if (null != savedInstanceState) {
            _searchableItem = (SearchableItem) savedInstanceState.getSerializable("item");
        }
        // Change End

        View rootView = inflater.inflate(R.layout.searchable_list_dialog, null);
        setData(rootView);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setView(rootView);

        String strPositiveButton = _strPositiveButtonText == null ? "CLOSE" : _strPositiveButtonText;
        alertDialog.setPositiveButton(strPositiveButton, _onClickListener);

        /*String strTitle = _strTitle == null ? "Select Item" : _strTitle;
        View view = setCustomView(getActivity(), strTitle);
        alertDialog.setCustomTitle(view);*/
//        alertDialog.setTitle(_strTitle);

//        View titleView = inflater.inflate(R.layout.searchable_list_title, null);
//        TextView textView = (TextView) titleView.findViewById(R.id.txt_spinner_title);
//        textView.setText(_strTitle);
//        alertDialog.setCustomTitle(titleView);

        final AlertDialog dialog = alertDialog.create();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams
                .SOFT_INPUT_STATE_HIDDEN);
        return dialog;
    }

    // Crash on orientation change #7
    // Change Start
    // Description: Saving the instance of searchable item instance.
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("item", _searchableItem);
        super.onSaveInstanceState(outState);
    }
    // Change End

    public void setTitle(String strTitle) {
        try {
            _strTitle = strTitle;
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setPositiveButton(String strPositiveButtonText) {
        try {
            _strPositiveButtonText = strPositiveButtonText;
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setPositiveButton(String strPositiveButtonText, DialogInterface.OnClickListener onClickListener) {
        try {
            _strPositiveButtonText = strPositiveButtonText;
            _onClickListener = onClickListener;
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setOnSearchableItemClickListener(SearchableItem searchableItem) {
        try {
            this._searchableItem = searchableItem;
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setOnSearchTextChangedListener(OnSearchTextChanged onSearchTextChanged) {
        try {
            this._onSearchTextChanged = onSearchTextChanged;
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setData(View rootView) {
        try {
            SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context
                    .SEARCH_SERVICE);

            _searchView = (SearchView) rootView.findViewById(R.id.search);
            _searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            _searchView.setIconifiedByDefault(false);
            _searchView.setOnQueryTextListener(this);
            _searchView.setOnCloseListener(this);
            _searchView.clearFocus();
            InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context
                    .INPUT_METHOD_SERVICE);
            mgr.hideSoftInputFromWindow(_searchView.getWindowToken(), 0);


            List items = (List) getArguments().getSerializable(ITEMS);
            if (items != null){
                _strTitle = items.get(0).toString();
//                items.remove(0);
            }

            _listViewItems = (ListView) rootView.findViewById(R.id.listItems);

            //create the adapter by passing your ArrayList data
            listAdapter = new CustomSpinnerAdapter(getActivity(), android.R.layout.simple_list_item_1, items);
//            try {
//                listAdapter.sort(new Comparator<String>() {
//                    @Override
//                    public int compare(String lhs, String rhs) {
//                        return lhs.compareTo(rhs);
//                    }
//                });
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            //attach the adapter to the list
            _listViewItems.setAdapter(listAdapter);

            _listViewItems.setTextFilterEnabled(true);

            _listViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    _searchableItem.onSearchableItemClicked(listAdapter.getItem(position), position);
                    getDialog().dismiss();
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onClose() {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        _searchView.clearFocus();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        try {

//        listAdapter.filterData(s);
            if (TextUtils.isEmpty(s)) {
//                _listViewItems.clearTextFilter();
                ((CustomSpinnerAdapter) _listViewItems.getAdapter()).getFilter().filter(null);
            } else {
                ((CustomSpinnerAdapter) _listViewItems.getAdapter()).getFilter().filter(s);
            }
            if (null != _onSearchTextChanged) {
                _onSearchTextChanged.onSearchTextChanged(s);
            }
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public interface SearchableItem<T> extends Serializable {
        void onSearchableItemClicked(T item, int position);
    }

    public interface OnSearchTextChanged {
        void onSearchTextChanged(String strText);
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            if (getDialog() != null){
                listAdapter.clearItems();
                getDialog().dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
