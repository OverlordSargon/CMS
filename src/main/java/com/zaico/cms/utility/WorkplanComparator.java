package com.zaico.cms.utility;

import com.zaico.cms.entities.Workplan;

import java.util.Comparator;

/**
 * Created by nzaitsev on 06.10.2016.
 */
public class WorkplanComparator implements Comparator<Workplan> {
    @Override
    public int compare(Workplan o1, Workplan o2) {
        if ( o1.getDate().after(o2.getDate())) {
            return 1;
        } else return -1;
    }
}
