class Menu(
    var menuList:MutableMap<Char, String>,
    val header:String
) {
    fun add(char:Char, str:String){
        menuList.put(char, str);
    }
    fun disply(){
        println(header)
        for(i in menuList){
            println("${i.key}. - ${i.value}");
        }
    }
}
