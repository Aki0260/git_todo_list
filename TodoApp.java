import java.util.*;
import java.time.LocalDateTime;

public class TodoApp {
    //タスクを保存するリスト
    private static final ArrayList<Todo> todos = new ArrayList<>();
    //ユーザー入力を受け付けるためのScanner
    private static final Scanner sc = new Scanner(System.in);
    //次のタスクID用のカウンター
    private static int nextId = 1;

    public static void main(String[] args){
        while(true){
            showMenu();
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    addTodo();
                    break;
                case 2:
                    listTodos();
                    break;
                case 3:
                    completeTodo();
                    break;
                case 4:
                    deleteTodo();
                    break;
                case 5:
                    System.out.println("アプリケーションを終了します。");
                    System.exit(0);
                default:
                    System.out.println("無効な選択です。");
            }
        }
    }
    //メニューを表示するメソッド
    private static void showMenu() {
        System.out.println("/n=== Todoアプリケーション ===");
        System.out.println("1: 新規タスクの追加");
        System.out.println("2: タスク一覧の表示");
        System.out.println("3: タスクの完了");
        System.out.println("4: タスクの削除");
        System.out.println("5: アプリケーションの終了");
        System.out.println("選択してください (1 - 5):");
    }

    //新規タスクを追加するメソッド
    private static void addTodo() {
        System.out.print("タスクのタイトルを入力してください: ");
        String title = sc.nextLine();

        Todo todo = new Todo(nextId++,title);
        todos.add(todo);
        System.out.println("タスクが追加されました: " + todo);


    }

    //タスク一覧を表示するメソッド
    private static void listTodos() {
        sortTodos(todos);
        if(todos.isEmpty()){
            System.out.println("タスクはありません。");
            return;
        }
        System.out.println("/n=== タスク一覧 ===");
        for(Todo todo : todos){
            System.out.println(todo);
        }
    }

    //未完了タスクを上に、完了タスクを完了時刻順に並び替えるメソッド
    private static void sortTodos(List<Todo> todos){
        todos.sort(
                Comparator
                        .comparing(Todo::isCompleted) //false → true
                        .thenComparing(
                                Todo::getCompletedAt,
                                Comparator.nullsLast(Comparator.naturalOrder())
                        )
        );
    }

    //タスクを完了させるメソッド
    private static void completeTodo() {
        listTodos();
        if(todos.isEmpty()) return;

        System.out.println("完了するタスクのIDを入力してください:");
        int id = getIntInput();

        for(Todo todo : todos){
            if(todo.getId() == id){
                if(todo.isCompleted()){
                    System.out.println("このタスクは既に完了しています。");
                    return;
                }
                todo.setCompleted(true);

                System.out.println("タスクを完了としてマークしました");
                return;
            }
        }
        System.out.println("指定されたIDのタスクは見つかりませんでした。");
    }

    //タスクを削除するメソッド
    private static void deleteTodo() {
        listTodos();
        if(todos.isEmpty()) return;

        System.out.print("削除するタスクのIDを入力してください:");
        int id = getIntInput();

        for(int i = 0;i<todos.size();i++){
            if(todos.get(i).getId() == id){
                Todo removed = todos.remove(i);
                System.out.println("タスクを削除しました: " + removed);
                return;
            }
        }
        System.out.println("指定されたIDのタスクは見つかりませんでした。");
    }

    //整数入力を処理するヘルパーメソッド
    private static int getIntInput() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1; //無効な入力の場合
        }
    }
}
