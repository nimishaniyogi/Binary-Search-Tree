package com.D289Q359.BinaryTree;

/*

* Name: Nimisha Niyogi
* WSU ID: D289Q359
*Assignment: AAA Assignment - 2
*
*  Binary Search Tree 
* Copyrights @ Nimisha 2017
*
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;


// To Store The Values (Getters and Setters) along with Constructors
class EntityStore {

    private EntityStore leftPlay;
    private EntityStore rootPlay;
    private EntityStore rightPlay;
    private String val;

    public EntityStore(String newValue) {
        this.val = newValue;
        this.leftPlay = null;
        this.rightPlay = null;
        this.rootPlay = null;
    }
    public EntityStore() {
        this.val = "";
        this.leftPlay = null;
        this.rightPlay = null;
        this.rootPlay = null;
    }
    public String getValue() {
        return val;
    }
    public void setValue(String val) {
        this.val = val;
    }
    public EntityStore getRoot() {
        return rootPlay;
    }
    public void setRoot(EntityStore root) {
        this.rootPlay = root;
    }
    public EntityStore getRight() {
        return rightPlay;
    }
    public void setRight(EntityStore right) {
        this.rightPlay = right;
    }
    public EntityStore getLeft() {
        return leftPlay;
    }
    public void setLeft(EntityStore left) {
        this.leftPlay = left;
    }

}

// Start The Program Here, Asking for Options available for user
public class D289Q359_BinaryTree {
    public static void main(String[] args) throws IOException {
        Scanner pullout = new Scanner(System.in);
        StringTokenizer tokenNum;
        String option;
        String typeInNum;

        //int choice;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        BinarySearchTree_logic cascade_logic = new BinarySearchTree_logic();
       // System.out.println("Name: Nimisha Niyogi \n WSU ID: D289Q359 \n\n");
        //System.out.println("**** Start Playing ****\n\n");
        loop:
        do{
            System.out.print(">  ");
            typeInNum = pullout.nextLine();
            tokenNum = new StringTokenizer(typeInNum);
            option = tokenNum.nextToken();
            //	choice = Integer.parseInt(br.readLine());
            switch(option.toLowerCase()){
                case "insert":
                    if(tokenNum.hasMoreElements()){
                        cascade_logic.updateBucket(tokenNum.nextToken());
                    }
                    else{
                    	
                        //System.out.println(" Enter Correct Operation");
                    }
                    break;
                case "delete":
                    if(tokenNum.hasMoreElements()){
                        cascade_logic.removeFromBucket(tokenNum.nextToken());
                    }
                    else{
                    
                      //  System.out.println("Please Enter Correct Operation");
                    }
                    break;
                case "show":
                    cascade_logic.output();
                    break;
                case "search":
                    if(tokenNum.hasMoreElements()){
                        cascade_logic.exploreBucket(tokenNum.nextToken());
                    }
                    else{
                    	
                       // System.out.println(" Enter Correct Operation");
                    }
                    break;
                case "clear":
                    cascade_logic.flush();
                    break;

                case "exit":
                   // System.out.println("*** Good-Bye ***");
                    break loop;
                default:
                	System.out.print(">");
                    break;

            }
        }while(!pullout.equals("exit"));
    }

}


// Main logic for Binary search Tree
class BinarySearchTree_logic {
    private EntityStore count = null;

    public void exploreBucket(String searchVal) {
        int hght = 0;
        if(!(count==null)){
            EntityStore temp = count;
            while(!(temp==null)){
                if(Integer.parseInt(temp.getValue()) > Integer.parseInt(searchVal)){
                    temp = temp.getLeft();
                }
                else if(Integer.parseInt(temp.getValue()) < Integer.parseInt(searchVal)){
                    temp = temp.getRight();
                }
                else{
                    System.out.println("\t"+hght);
                    break;
                }
                hght++;
            }
        }
    }

    public void updateBucket(String scanVal) {
        int tall = 0;
        if(count==null){
            count = new EntityStore(scanVal);
        }
        else{
            EntityStore tempVal = count,parentVal = null;
            while(!(tempVal==null)){
                parentVal = tempVal;
                tall++;
                if(Integer.parseInt(tempVal.getValue()) >= Integer.parseInt(scanVal)){
                    tempVal = tempVal.getLeft();
                }
                else{
                    tempVal = tempVal.getRight();
                }
            }
            EntityStore newRoot = new EntityStore(scanVal);
            newRoot.setRoot(parentVal);
            if(Integer.parseInt(parentVal.getValue()) >= Integer.parseInt(newRoot.getValue())){
                parentVal.setLeft(newRoot);
            }
            else{
                parentVal.setRight(newRoot);
            }
        }
        System.out.println("\t"+tall);
    }

    public void removeFromBucket(String removeVal) {
        try {
            EntityStore newNRoot = count, leastLeaf = null;
            if (!(count == null)) {
                while (!(newNRoot == null)) {
                    if (Integer.parseInt(newNRoot.getValue()) > Integer.parseInt(removeVal)) {
                        newNRoot = newNRoot.getLeft();
                    } else if (Integer.parseInt(newNRoot.getValue()) < Integer.parseInt(removeVal)) {
                        newNRoot = newNRoot.getRight();
                    } else {

                        break;
                    }
                }

                if (newNRoot.getLeft() == null) {
                    reStructure(newNRoot, newNRoot.getRight());
                } else if (newNRoot.getRight() == null) {
                    reStructure(newNRoot, newNRoot.getLeft());
                } else {
                    leastLeaf = LeastVal(newNRoot.getRight());
                    if (leastLeaf.getRoot() != newNRoot) {
                        reStructure(leastLeaf, leastLeaf.getRight());
                        leastLeaf.setRight(newNRoot.getRight());
                        leastLeaf.getRight().setRoot(leastLeaf);
                    }
                    reStructure(newNRoot, leastLeaf);
                    leastLeaf.setLeft(newNRoot.getLeft());
                    leastLeaf.getLeft().setRoot(leastLeaf);
                }
            }
        }catch (Exception e){}
    }

    private EntityStore LeastVal(EntityStore leastTreeVal) {
        while(leastTreeVal.getLeft()!=null){
            leastTreeVal = leastTreeVal.getLeft();
        }
        return leastTreeVal;
    }


    public void flush() {
        count = null;
    }


    private void reStructure(EntityStore clrParent, EntityStore swapNode) {
        if(clrParent.getRoot()==null){
            count = swapNode;
        }
        else if(clrParent.getRoot().getLeft()==clrParent){
            clrParent.getRoot().setLeft(swapNode);
        }
        else{
            clrParent.getRoot().setRight(swapNode);
        }
        if(swapNode!=null){
            swapNode.setRoot(clrParent.getRoot());
        }
    }


    private void showUp(EntityStore headVal, int hght) {
        if(!(headVal==null)){
            for (int i = 0; i < hght; i++) {
                System.out.print("	");
            }
            System.out.println(headVal.getValue());
            if(headVal.getLeft()==null){
                for (int j = 0; j < hght+1; j++) {
                    System.out.print("	");
                }
                System.out.println("-");
            }
            showUp(headVal.getLeft(), hght+1);
            if(headVal.getRight()==null){
                for (int k = 0; k < hght+1; k++) {
                    System.out.print("	");
                }
                System.out.println("-");
            }
            showUp(headVal.getRight(), hght+1);
        }
    }

    public void output() {
        showUp(count,0);
    }
}




