package com.kgb.news

import com.kgb.news.model.Article

/**
 * @author Krzysztof Betlej <labiod@wp.pl>.
 * @date 5/23/18
 */
class NewsStore {

    companion object {
        @JvmStatic
        var newsArticles : List<Article> = mutableListOf()
    }
}