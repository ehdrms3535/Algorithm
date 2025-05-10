// 22112353 김동근

import java.io.File;
import java.io.IOException;
import java.util.*;


public class HW1 {

    static class Node<K,V>{
        K key;
        V value;
        int N;
        int count=0;
        int scount=0;
        int aux;
        Node<K,V> left,right;
        Node<K,V> parent;

        public Node(K key, V value){
            this.key=key;
            this.value=value;
            this.N=1;
        }
    }

    static class BST<K extends Comparable<K>, V> {
        public Node<K, V> root;
        public int size() {return(root!=null)? root.N:0;}
        protected Node<K, V> treeSearch(K key) {
            Node<K, V> x = root;
            while (true) {
                int cmp = key.compareTo(x.key);
                if (cmp == 0) return x;
                else if (cmp < 0) {
                    if (x.left == null) return x;
                    else x = x.left;
                } else {
                    if (x.right == null) return x;
                    else x = x.right;
                }

            }
        }
        public V get(K key){
            if(root == null) return null;
            Node<K,V> x = treeSearch(key);
            if (key.equals(x.key))
                return x.value;
            else return null;

        }

        public void put(K key, V val, Boolean check){
            if (root == null) { root = new Node<K,V>(key, val);  return; }
            Node<K,V> x = treeSearch(key);
            int cmp= key.compareTo(x.key);
            if (cmp== 0) {
                x.value= val;
                if(check) x.count+=1;
                else x.scount+=1;
            }
            else {
                Node<K,V> newNode= new Node<K,V>(key, val);
                if (cmp< 0) x.left= newNode;
                else x.right= newNode;
                newNode.parent= x;
                rebalanceInsert(newNode);
                if(check) newNode.count=1;
                else newNode.scount=1;
            }
        }
        protected  void rebalanceInsert(Node<K,V> x){
            resetSize(x.parent,1);
        }
        private void resetSize(Node<K,V> x,int value){
            for(;x!=null;x=x.parent){
                x.N+=value;
            }
        }

        public Iterable<Integer> keys() {
            if (root == null) return null;
            ArrayList<Integer> keyList = new ArrayList(size());
            inorder(root, keyList);
            return keyList;
        }

        private void inorder(Node<K,V> x, ArrayList<Integer> keyList) {
            if (x != null) {
                inorder(x.left, keyList);
                if(x.count>0 && x.scount>0) {
                    keyList.add(Math.min(x.count,x.scount));
                }
                inorder(x.right, keyList);
            }
        }
        public boolean contains(K key) {return get(key) != null; }
    }
    static class MakeList {
        int size;
        int capacity;
        String arr[];
        int makelistcap;

        public MakeList() {
            this.size = 10;
            this.capacity=0;
            this.arr = new String[size];
            this.makelistcap=0;
        }
        public void resize(int capacity1){
            String[] tmp = new String[capacity1];
            for(int i=0; i<this.capacity; i++){
                tmp[i]=arr[i];
            }
            this.size = capacity;
            arr=tmp;
        }

        public void input(String a){
            if(capacity>=size){
                resize(size*2);
            }
            arr[capacity++]=a;
        }


    }

    static void run(Scanner sc1, Scanner sc2, String ffname, String Sfname){
        MakeList FF = new MakeList();
        MakeList SF = new MakeList();
        BST FBST = new BST();
        String str = "";
        int subcount =0;

        while(sc1.hasNext()) {
            str = sc1.nextLine();
            StringTokenizer st = new StringTokenizer(str, " \t\n=;,<>()");
            while (st.hasMoreTokens()) {
                String a =st.nextToken();
                FF.input(a);
            }
        }

        for(int i=0; i<=FF.capacity-5;i++){
            String s;
            s=FF.arr[i];
            for(int j=i+1;j<i+5;j++){
              s=s+" "+ FF.arr[j];
            }
            FF.makelistcap++;
            if(!FBST.contains(s)) FBST.put(s,1,true);
            else FBST.put(s,0,true);
        }

        while(sc2.hasNext()) {
            str = sc2.nextLine();
            StringTokenizer st = new StringTokenizer(str, " \t\n=;,<>()");
            while (st.hasMoreTokens()) {
                String a =st.nextToken();
                SF.input(a);
            }
        }

        for(int i=0; i<=SF.capacity-5;i++){
            String s;
            s=SF.arr[i];
            for(int j=i+1;j<i+5;j++){
                s=s+" "+ SF.arr[j];
            }
            SF.makelistcap++;
            if(!FBST.contains(s)) FBST.put(s,1,false);
            else FBST.put(s,0,false);
        }

        Iterable KeyValue = FBST.keys();
        for(Object a : KeyValue){
            subcount+=(int)a;
        }

        float similar =(float)subcount/(FF.makelistcap+ SF.makelistcap-subcount);
        System.out.println("파일 " + ffname +"의 Shingle의 수 = " + FF.makelistcap);
        System.out.println("파일 "+ Sfname + "의 Shingle의 수 = " + SF.makelistcap);
        System.out.println("두 파일에서 공통된 shingle의 수 = " + subcount);
        System.out.println(ffname+"과 "+Sfname + "의 유사도 = "+ similar);
    }

    public static void main(String[] args) {

        System.out.print("첫번째 파일 이름? ");
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        String ffname = sc1. nextLine();
        System.out.print("두번째 파일 이름? ");
        String sfname = sc2. nextLine();

        sc1.close();
        sc2.close();

        try{
            sc1 = new Scanner(new File(ffname));
            sc2 = new Scanner(new File(sfname));
            run(sc1,sc2,ffname,sfname);
        }
        catch (IOException e) {System.out.println(e); return;}
    }
}