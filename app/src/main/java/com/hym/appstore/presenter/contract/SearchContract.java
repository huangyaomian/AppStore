package com.hym.appstore.presenter.contract;

import com.hym.appstore.bean.BaseBean;
import com.hym.appstore.bean.HomeBean;
import com.hym.appstore.bean.SearchResult;
import com.hym.appstore.ui.BaseView;

import java.util.List;

import io.reactivex.Observable;

public interface SearchContract {

    public  interface  SearchView extends BaseView {

        void showSearchHistory(List<String> list);
        void showSuggestions(List<String> list);
        void showSearchResult(SearchResult result);

    }


    public interface ISearchModel{

        Observable<BaseBean<List<String>>> getSuggestion(String keyword);

        Observable<BaseBean<SearchResult>> search(String keyword);

    }


}
