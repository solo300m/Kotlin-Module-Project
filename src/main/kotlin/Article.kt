import android.icu.text.CaseMap.Title

class Article() {
    var title:String = "";
    var text:String = "";
    constructor(title: String, text:String) : this(){
        this.title = title;
        this.text = text
    }
    constructor(title:String):this(){
        this.title = title;
        this.text = "";
    }
    /*fun getTitle():String{
        return this.title;
    }
    fun getText():String{
        return this.text;
    }*/

    override fun toString(): String {
        return "Заголовок - ${this.title}\n ${this.text}";
    }
}
