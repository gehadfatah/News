package com.goda.newstk.features.news.data.model

interface ApiRequest {
    val q: String?
    val sources: String?
    val page: Int

    fun nextPage(): ApiRequest

    fun firstPage(): ApiRequest

    fun clone(): ApiRequest

    fun toMap(): Map<String, String?>
}



data class ApiRequestEverything(
    override val q: String? = null,
    /** This should be in ISO 8601 format (e.g. 2020-03-28 or 2020-03-28T14:21:35) */
    val from: String? = null,
    /** This should be in ISO 8601 format (e.g. 2020-03-28 or 2020-03-28T14:21:35) */
    val to: String? = null,
    override val sources: String? = null,
    /** @Default: publishedAt */
    val sortBy: SortBy? = null,
    /** @Default: all languages returned. */
    val language: Language? = null,
    override val page: Int = 1
): ApiRequest {
    override fun nextPage(): ApiRequestEverything {
        return ApiRequestEverything(
            q,
            from,
            to,
            sources,
            sortBy,
            language,
            page + 1
        )
    }

    override fun firstPage(): ApiRequestEverything {
        return ApiRequestEverything(
            q,
            from,
            to,
            sources,
            sortBy,
            language,
            1
        )
    }

    override fun clone(): ApiRequestEverything {
        return ApiRequestEverything(
            q,
            from,
            to,
            sources,
            sortBy,
            language,
            page
        )
    }

    override fun toMap(): Map<String, String?> {
        val map = mutableMapOf<String, String?>()
        if (q != null) map["q"] = q
        if (sources != null) map["sources"] = sources
        if (from != null) map["from"] = from
        if (to != null) map["to"] = to
        if (sortBy != null) map["sortBy"] = sortBy.value
        if (language != null) map["language"] = language.value
        map["page"] = page.toString()

        return map
    }
}