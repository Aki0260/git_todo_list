//import java.time.LocalDateTime;
//
//public class Todo {
//    //フィールド
//    private int id; //タスクIDL
//    private String title; //タスクタイトル
//    private LocalDateTime createdAt; //作成日時
//    private boolean completed; //完了状態
//    private LocalDateTime completedAt; // 完了日時
//
//
//    //コンストラクタ
//    public Todo(int id, String title){
//        this.id = id;
//        this.title = title;
//        this.createdAt = LocalDateTime.now();
//        this.completed = false;
//    }
//
//    //getter/setterメソッド
//    public int getId(){
//        return id;
//    }
//
//    public String getTitle(){
//        return title;
//    }
//
//    public boolean isCompleted(){
//        return completed;
//    }
//
//    public void setCompleted(boolean completed){
//        this.completed = completed;
//    }
//
//    public LocalDateTime setCompletedAt() {
//        return completedAt;
//    }
//
//    //タスクの情報を文字列で返すメソッド
//    @Override
//    public String toString(){
//        String status = completed ? "[完了]" : "[未完了]";
//        return String.format("%d: %s %s", id, title,status);
//    }
//}

import java.time.LocalDateTime;

public class Todo {
    private int id;
    private String title;
    private LocalDateTime createdAt;
    private boolean completed;
    private LocalDateTime completedAt;

    public Todo(int id, String title){
        this.id = id;
        this.title = title;
        this.createdAt = LocalDateTime.now();
        this.completed = false;
        this.completedAt = null;
    }

    public int getId(){
        return id;
    }

    public boolean isCompleted(){
        return completed;
    }

    public LocalDateTime getCompletedAt(){
        return completedAt;
    }

    // ★ ここが重要
    public void setCompleted(boolean completed){
        this.completed = completed;
        if(completed){
            this.completedAt = LocalDateTime.now();
        } else {
            this.completedAt = null;
        }
    }

    @Override
    public String toString(){
        String status = completed ? "[完了]" : "[未完了]";
        return String.format("%d: %s %s", id, title, status);
    }
}
