package com.axxess.cavistaassignment.customview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

public class PagingRecyclerView extends RecyclerView {

    public static int INITIAL_PAGE = 1;
    public int page = INITIAL_PAGE;

    public void canCheckNextPage() {
        isLoading = false;
        isLastPage = false;
    }

    public void disableLoadMore() {
        isLastPage = true;
    }

    public void loadingPagination() {
        isLastPage = false;
        isLoading = true;
        page = INITIAL_PAGE;
    }

    public interface OnPageChangeCallback {
        void OnPageChange(int page);
    }

    OnPageChangeCallback pageChangeCallback;

    public void setPageChangeCallback(OnPageChangeCallback pageChangeCallback) {
        this.pageChangeCallback = pageChangeCallback;
    }

    public PagingRecyclerView(@NonNull Context context) {
        super(context);
    }

    boolean isLoading = false;

    boolean isLastPage = false;

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public PagingRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        addOnScrollListener(onScrollListener);
        disableChangeAnimation();
    }

    public PagingRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addOnScrollListener(onScrollListener);
        disableChangeAnimation();
    }

    OnScrollListener onScrollListener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            GridLayoutManager layoutManager = (GridLayoutManager) getLayoutManager();
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
            if (!isLoading() && !isLastPage()) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0) {
                    setLoading(true);
                    if (pageChangeCallback != null) {
                        page++;
                        pageChangeCallback.OnPageChange(page);
                    }

                }
            }
        }

    };

    public void disableChangeAnimation() {
        if (getItemAnimator() instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) getItemAnimator()).setSupportsChangeAnimations(false);
        }
    }
}
