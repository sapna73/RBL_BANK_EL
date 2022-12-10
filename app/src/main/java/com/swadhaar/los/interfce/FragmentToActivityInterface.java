package com.swadhaar.los.interfce;

import com.swadhaar.los.database.entity.DynamicUITable;

import java.util.List;

public interface FragmentToActivityInterface {
    void valueFromFragment(String currentScreenId,String currentScreenName, String fragmentToCall, List<DynamicUITable> dynamicUITableList);
    void oneFragmentToAnotherFragment(String currentScreenName, String fragmentToCall,DynamicUITable dynamicUITable,
                                      List<DynamicUITable> dynamicUITableList);
}
