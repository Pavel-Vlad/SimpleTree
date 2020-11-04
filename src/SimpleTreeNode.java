import java.util.*;

public class SimpleTreeNode<T> {
    public T NodeValue; // значение в узле
    public SimpleTreeNode<T> Parent; // родитель или null для корня
    public List<SimpleTreeNode<T>> Children; // список дочерних узлов или null

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
        NodeValue = val;
        Parent = parent;
        Children = new ArrayList<>();
    }
}

class SimpleTree<T> {

    public SimpleTreeNode<T> Root; // корень, может быть null

    public SimpleTree(SimpleTreeNode<T> root) {
        Root = root;
    }

    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild) {
        // ваш код добавления нового дочернего узла существующему ParentNode
        NewChild.Parent = ParentNode;
        ParentNode.Children.add(NewChild);
    }

    public void DeleteNode(SimpleTreeNode<T> NodeToDelete) {
        // ваш код удаления существующего узла NodeToDelete
        SimpleTreeNode<T> parent = NodeToDelete.Parent;
        parent.Children.remove(NodeToDelete);
    }

    public List<SimpleTreeNode<T>> GetAllNodes() {
        // ваш код выдачи всех узлов дерева в определённом порядке
        SimpleTreeNode<T> simpleTreeNode;
        List<SimpleTreeNode<T>> listResult = new ArrayList<>();
        List<SimpleTreeNode<T>> listTemp = new ArrayList<>();
        listTemp.add(Root);

        while (!listTemp.isEmpty()) {
            simpleTreeNode = listTemp.remove(0);
            if (!simpleTreeNode.Children.isEmpty()) {
                listTemp.addAll(simpleTreeNode.Children);
            }
            listResult.add(simpleTreeNode);
        }
        return listResult;
    }

    public List<SimpleTreeNode<T>> FindNodesByValue(T val) {
        // ваш код поиска узлов по значению
        List<SimpleTreeNode<T>> listResult = new ArrayList<>();
        for (SimpleTreeNode<T> i :
                GetAllNodes()) {
            if (i.NodeValue == val) listResult.add(i);
        }
        return listResult;
    }

    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent) {
        // ваш код перемещения узла вместе с его поддеревом --
        // в качестве дочернего для узла NewParent
        DeleteNode(OriginalNode);
        AddChild(NewParent, OriginalNode);
    }

    public int Count() {
        // количество всех узлов в дереве
        return GetAllNodes().size();
    }

    public int LeafCount() {
        // количество листьев в дереве
        int count = 0;
        for (SimpleTreeNode<T> i :
                GetAllNodes()) {
            if (i.Children.isEmpty()) count++;
        }
        return count;
    }

    // найти лес, состоящий из чётных деревьев
    public ArrayList<T> EvenTrees() {
        ArrayList<T> resultList = new ArrayList<>();
        SimpleTreeNode<T> node = Root;
        List<SimpleTreeNode<T>> listTemp = new ArrayList<>();
        listTemp.add(node);
        while (!listTemp.isEmpty()) {
            node = listTemp.remove(0);
            SimpleTree<T> tempTree = new SimpleTree<>(node);
            if (tempTree.Count() % 2 == 0) {
                if (node.Parent != null) {
                    resultList.add(node.Parent.NodeValue);
                    resultList.add(node.NodeValue);
                }
                listTemp.addAll(node.Children);
            }
        }
        return resultList;
    }


}