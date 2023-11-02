class Archive(
    val name:String
) {
    private val articleList: MutableMap<Char, Article> = mutableMapOf();
    fun addArticle(article:Article){
        var idxMax:Char = '2';
        for(i in articleList){
            if(i.key > idxMax)
                idxMax = i.key;
        }
        articleList.put(idxMax+1, article);
    }
    fun getAll():MutableMap<Char, Article>{
        return articleList;
    }
    fun dellArticle(index:Char){
        articleList.remove(index);
    }
}
