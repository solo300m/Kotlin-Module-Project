import java.util.Scanner

class HightMenu() {
    private var first: MutableMap<Char, String> =
        mutableMapOf(Pair('0', "Выход"), Pair('1', "Выбор архива"), Pair('2', "Создание архива"));
    private var chArchive: MutableMap<Char, String> =
        mutableMapOf(Pair('0', "В главное меню"), Pair('1', "Создание архива"));
    private var addArchive: MutableMap<Char, String> =
        mutableMapOf(Pair('0', "В меню выбор архива"), Pair('1', "Новый архив"));
    private var chArticle: MutableMap<Char, String> =
        mutableMapOf(Pair('0', "В меню выбор архива"), Pair('1', "Создание заметки"));
    private var addArticle: MutableMap<Char, String> =
        mutableMapOf(Pair('0', "В меню выбор заметки"), Pair('1', "Новая заметка"));

    val menuFirst: Menu = Menu(menuList = first, "Главное меню");
    val menuChoiceArchive: Menu = Menu(menuList = chArchive, "Выбор архива");
    val menuAddArchive: Menu = Menu(menuList = addArchive, "Создание архива");
    val menuChoiceArticle: Menu = Menu(menuList = chArticle, "Выбор заметки");
    val menuAddArticle: Menu = Menu(menuList = addArticle, "Новая заметка");

}

fun main(args: Array<String>) {
    val scan: Scanner = Scanner(System.`in`);
    println("Приложение Заметки");
    var archive: MutableMap<Char, Archive> = mutableMapOf();
    val menu: HightMenu = HightMenu();

    while (true) {
        menu.menuFirst.disply();
        var a: String = scan.next();
        var arrayChar = a.toCharArray();
        if (arrayChar.size > 1 || !arrayChar[0].isDigit())
            println("Ведено неправильное значение. Попробуйте еще раз...");
        else {
            when (a) {
                "0" -> break;
                "1" -> choiceArchiveFun(menu, archive, scan);
                "2" -> addArchiveFun(menu, archive, scan);
            }
        }
    }
}

fun choiceArchiveFun(menuH: HightMenu, archive: MutableMap<Char, Archive>, scan: Scanner) {
    var menu = menuH.menuChoiceArchive;
    for (i in archive) {
        if (!menu.menuList.containsKey(i.key)) {
            menu.add(i.key, i.value.name);
        }
    }
    menu.disply();
    var set = menu.menuList.keys;
    while (true) {
        var a: String = scan.next();
        var arrayChar = a.toCharArray();
        if (arrayChar.size > 1 || !arrayChar[0].isDigit())
            println("Ведено неправильное значение. Попробуйте еще раз...");
        else {
            if (a == "0") {
                break;
            } else if (a == "1") {
                addArchiveFun(menuH, archive, scan);
            } else if (menu.menuList.contains(a.toCharArray()[0])) {
                val atrib: Char = a.toCharArray()[0];
                getArticleFromArchive(atrib, menuH, archive, scan);
            }
        }
        for (i in archive) {
            if (!menu.menuList.containsKey(i.key)) {
                menu.add(i.key, i.value.name);
            }
        }
        menu.disply();
    }
}

fun getArticleFromArchive(
    atrib: Char,
    menuH: HightMenu,
    archive: MutableMap<Char, Archive>,
    scan: Scanner
) {
    var menu = menuH.menuChoiceArticle;
    var localArchive = archive.get(atrib)?.getAll();
    var str: String;
    while (true) {
        if (localArchive != null) {
            //println("0 - Возврат в меню Выбор архива")
            menu.disply();
            for (i in localArchive) {
                println("${i.key} - ${i.value.title}");

            }
        }
        println("Ввести пункт меню");
        str = scan.next();
        if (str.toCharArray().size > 1 || !str.toCharArray()[0].isDigit()) {
            println("Вы ввели не правильный номер статьи. Повторите ввод...");
        } else {
            var atr: Char = str.toCharArray()[0];
            if (atr == '0') {
                break;
            } else if (atr == '1') {
                addArticleFun(menuH, archive, atrib, scan);
                menu.disply();
            } else if (localArchive != null) {
                if (localArchive.containsKey(atr)) {
                    println(localArchive[atr]?.title);
                    println(localArchive[atr]?.text);
                    println();
                }
            }
        }
        //menu.disply();
    }
}

fun addArchiveFun(menuH: HightMenu, archive: MutableMap<Char, Archive>, scan: Scanner) {
    var menu = menuH.menuAddArchive;
    menu.disply();
    while (true) {
        println("Выберите вариант из меню");
        var a: String = scan.next();
        var arrayChar = a.toCharArray();
        if (arrayChar.size > 1 || !arrayChar[0].isDigit())
            println("Ведено неправильное значение. Попробуйте еще раз...");
        else {
            if (a == "0") {
                break;
            } else if (a == "1") {
                val set = archive.keys;
                var maxKey = '1';
                for (i in set) {
                    if (i > maxKey)
                        maxKey = i
                }
                println("Введите имя нового архива");
                val name: String = scan.next();
                archive[maxKey + 1] = Archive(name);
            }
        }
        menu.disply();
    }
}

fun addArticleFun(
    menuH: HightMenu,
    archive: MutableMap<Char, Archive>,
    atrib: Char,
    scan: Scanner
) {
    var menu = menuH.menuAddArticle;
    val strScan: Scanner = Scanner(System.`in`);
    menu.disply();
    while (true) {
        var a: String = scan.next();
        var arrayChar = a.toCharArray();
        if (arrayChar.size > 1 || !arrayChar[0].isDigit())
            println("Ведено неправильное значение. Попробуйте еще раз...");
        else {
            if (a == "0") {
                break;
            } else if (a == "1") {
                println("Введите заголовок заметки в одно слово");
                val title: String = scan.next();
                println("Введите текст заметки произвольного объема. Enter - завершение ввода.");
                val text: String = strScan.nextLine();
                archive[atrib]?.addArticle(Article(title, text));
            }
        }
        menu.disply();
    }
}
