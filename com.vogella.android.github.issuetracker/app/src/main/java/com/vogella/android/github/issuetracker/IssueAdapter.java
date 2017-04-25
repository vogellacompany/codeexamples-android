package com.vogella.android.github.issuetracker;

import java.util.List;

public class IssueAdapter extends MyBaseAdapter {
    private List<Issue> issues;

    public IssueAdapter(List<Issue> issues) {
        this.issues = issues;
    }

    @Override
    public Object getDataAtPosition(int position) {
        return issues.get(position);
    }

    @Override
    public int getLayoutIdForType(int viewType) {
        return R.layout.rowlayout;
    }

    @Override
    public int getItemCount() {
        return issues.size();
    }
}
