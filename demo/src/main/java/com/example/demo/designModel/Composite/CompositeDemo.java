package com.example.demo.designModel.Composite;


import java.util.ArrayList;
import java.util.List;

//组合模式 将结构进行树形化操作，一个文件包含多个子文件，子文件也也可能只有一个对象


//定义一个抽象类
abstract class Node{
  abstract void p();
}

 class LeftNode extends Node{
  String name;
  public LeftNode(String name){
    this.name = name;
  }

  @Override
  void p() {
    System.out.println(name);
  }
}

//定义一个树形准备类
 class BranchNode extends Node{
  String content;
  //定义一个node集合进行树形准备
  List<Node> nodes = new ArrayList();

  public BranchNode(String content){
    this.content = content;
  }
  @Override
  void p() {
    System.out.println(content);
  }

  public void add(Node node){
    nodes.add(node);
  }
}

public class CompositeDemo {
  public static void main(String[] args) {
    BranchNode root = new BranchNode("root");
    BranchNode branch1 = new BranchNode("branch1");
    BranchNode branch2 = new BranchNode("branch2");
    LeftNode left1 = new LeftNode("left1");
    LeftNode left2 = new LeftNode("left2");

    root.add(branch1);
    root.add(branch2);
    branch1.add(left1);
    branch1.add(left2);
    tree(root,0);

  }

  public static void tree(Node node,int num){
    for (int i = 0; i < num; i++) System.out.print("--");
    node.p();
    if (node instanceof BranchNode){
      for(Node nodes : ((BranchNode) node).nodes){
        tree(nodes,num+1);
      }
    }
  }
}


